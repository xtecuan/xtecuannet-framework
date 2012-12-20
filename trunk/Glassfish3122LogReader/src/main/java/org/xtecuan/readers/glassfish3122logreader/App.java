package org.xtecuan.readers.glassfish3122logreader;

import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.xtecuan.readers.glassfish3122logreader.entities.LogGlassfish;
import org.xtecuan.readers.glassfish3122logreader.enums.ProcLogEnum;

/**
 * Hello world!
 *
 */
public class App {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.xtecuan.readers_Glassfish3122LogReader_jar_1.0-SNAPSHOTPU");
    private static Logger logger = Logger.getLogger(App.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    private static final String DOMAINS_FOLDER = "/glassfish/domains";
    private static final String LOGS_FOLDER = "/logs";
    private static final String END = "|#]";
    private static final String[] LOG_EXT = {".log", ".log_"};
    public static final int RESULT_FAILURE = -1;
    public static final int RESULT_SUCCESS = 0;
    private static final String FLAG_CONFIG = "--config";
    private static final String FLAG_LIST_CONFIGS = "--listconfigs";
    private static final String FLAG_ALL = "all";
    private static final String SEVERE = "SEVERE";
    private static final String WARNING = "WARNING";
    private static FileFilter filter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return pathname.getName().endsWith(LOG_EXT[0]) || pathname.getName().contains(LOG_EXT[1]);
        }
    };

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private static File[] getLogFiles(File domainLogsPath) {
        File[] files = null;
        files = domainLogsPath.listFiles(filter);
        return files;
    }

    private static File getDomainLogFolder(ProcLogEnum which) {

        return new File(which.getGfhome() + DOMAINS_FOLDER + File.separator + which.getDomain() + LOGS_FOLDER);
    }

    public static boolean checkForEnd(String toTest) {

        return toTest.endsWith(END);

    }

    private static void listConfigs() {

        System.out.println("Configuraciones disponibles");
        for (ProcLogEnum c : ProcLogEnum.values()) {

            System.out.println("\t" + c.toString());
        }
    }

    private static LogGlassfish fromStringBuilderToEntity(StringBuilder sb, ProcLogEnum configObj, File log) {

        LogGlassfish lll = new LogGlassfish();

        String text = sb.toString().substring(3);
        String text1 = text.substring(0, text.length() - 3);
        //logger.info(text1);
        String[] des = text1.split("\\|");

        if (des != null) {
            lll.setLdomain(configObj.getDomain());
            Date ldatetime = null;
            try {
                ldatetime = sdf.parse(des[0]);
            } catch (Exception e) {
                logger.error("Error convirtiendo la fecha: ", e);
            }
            if (ldatetime != null) {
                lll.setLdatetime(ldatetime);
            }
            lll.setLoglevel(des[1]);
            lll.setLprodnameversion(des[2]);
            lll.setLoggername(des[3]);
            lll.setLkeyvaluepairs(des[4]);
            String msg = null;
            if (des[5].length() >= 4000) {
                msg = des[5].substring(0, 4000);
                String msg2 = des[5].substring(4000);
                if (msg2.length() >= 4000) {

                    lll.setLmessageNext(msg2.substring(0, 4000));
                } else {
                    lll.setLmessageNext(msg2);
                }
            } else {
                msg = des[5];
            }
            lll.setLmessage(msg);
            lll.setLserverip(configObj.getIp());
            lll.setLlogfile(log.getName());

        } else {
            logger.error("Nada que parsear para esta entrada de log");
        }

        return lll;
    }

    private static void processLogFile(File log, ProcLogEnum configObj) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            List lines = FileUtils.readLines(log);
            StringBuilder sb = new StringBuilder();
            boolean finalizo = false;
            for (Object current : lines) {
                if (current != null) {
                    String c = (String) current;
                    if (c.length() > 0) {
                        if (checkForEnd(c)) {
                            sb.append(c);
                            finalizo = true;
                        } else {
                            sb.append(c);
                            finalizo = false;
                        }
                        if (finalizo) {
                            LogGlassfish lll = fromStringBuilderToEntity(sb, configObj, log);

                            if (lll.getLoglevel().equals(SEVERE) || lll.getLoglevel().equals(WARNING)) {
                                try {
                                    logger.info("Guardando entidad!!!");
                                    em.persist(lll);

                                } catch (Exception ex1) {
                                    logger.error("Error al guardar entrada de log", ex1);
                                    em.getTransaction().rollback();
                                }
                                logger.info(lll);
                            }else{
                                
                                logger.info("LOGLEVEL: "+lll.getLoglevel()+" Skipping!!!");
                            }
                            sb = new StringBuilder();
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error en el metodo processLogFile archivo: " + log.getName(), e);
        } finally {
            em.getTransaction().commit();
            em.close();
        }
    }

    private static void processLogFilesForConfig(String config) {

        ProcLogEnum configObj = ProcLogEnum.valueOf(config);
        File domainLogFolder = getDomainLogFolder(configObj);

        logger.info("Procesando los logs del dominio: " + domainLogFolder.getPath());

        File[] logs = getLogFiles(domainLogFolder);

        if (logs != null) {
            logger.info("Procesando un total de : " + logs.length + " archivos de logs");

            for (int i = 0; i < logs.length; i++) {
                File log = logs[i];
                try {
                    logger.info("Procesando archivo: " + log.getName());
                    long startTime = System.currentTimeMillis();
                    processLogFile(log, configObj);
                    long finishTime = System.currentTimeMillis();

                    long seconds = (finishTime - startTime) / 1000;

                    logger.info("Archivo: " + log.getName() + " procesado en : " + seconds + " segundos");

                } catch (Exception ex) {
                    logger.error("Error procesando archivo: " + log.getName(), ex);
                }

            }

        } else {
            logger.info("Ningun archivo por procesar para esta configuracion!!!!");
        }
    }

    public static void main(String[] args) {

        if (args != null) {

            if (args.length == 2 && args[0].equals(FLAG_CONFIG)) {

                processLogFilesForConfig(args[1]);

            } else if (args.length == 2 && args[0].equals(FLAG_LIST_CONFIGS)) {

                listConfigs();
            }

        } else {

            System.out.println("Forma de uso: ");
        }
    }

    public static void main1(String[] args) {

        File f = new File("./sample-log/server.log");


        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {


            List lines = FileUtils.readLines(f);

            StringBuilder sb = new StringBuilder();

            boolean finalizo = false;

            for (Object current : lines) {



                if (current != null) {

                    String c = (String) current;

                    if (c.length() > 0) {

                        if (checkForEnd(c)) {

                            sb.append(c);
                            finalizo = true;
                            //sb = new StringBuilder();

                        } else {

                            sb.append(c);
                            finalizo = false;
                        }

                        if (finalizo) {

                            String text = sb.toString().substring(3);
                            String text1 = text.substring(0, text.length() - 3);


                            logger.info(text1);


                            String[] des = text1.split("\\|");

//                            for (int i = 0; i < des.length; i++) {
//                                String item = des[i];

                            LogGlassfish lll = new LogGlassfish();
                            lll.setLdomain("domain1");
                            Date ldatetime = null;

                            try {
                                ldatetime = sdf.parse(des[0]);
                            } catch (Exception e) {
                                logger.error("Error convirtiendo la fecha: ", e);
                            }

                            if (ldatetime != null) {
                                lll.setLdatetime(ldatetime);
                            }

                            lll.setLoglevel(des[1]);
                            lll.setLprodnameversion(des[2]);
                            lll.setLoggername(des[3]);
                            lll.setLkeyvaluepairs(des[4]);

                            String msg = null;

                            if (des[5].length() >= 4000) {
                                msg = des[5].substring(0, 4000);
                            } else {
                                msg = des[5];
                            }

                            lll.setLmessage(msg);


                            try {
                                logger.info("Guardando entidad!!!");
                                em.persist(lll);

                            } catch (Exception ex1) {
                                logger.error("Error al guardar entrada de log", ex1);
                                em.getTransaction().rollback();
                            }

//                            }


                            sb = new StringBuilder();
                        }
                    }
                }



            }


        } catch (Exception e) {

            logger.error("Error: ", e);

        } finally {
            em.getTransaction().commit();
            em.close();

        }
    }
}
