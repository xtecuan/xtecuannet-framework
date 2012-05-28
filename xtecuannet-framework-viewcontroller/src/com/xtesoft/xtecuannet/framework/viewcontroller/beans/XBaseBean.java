/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.viewcontroller.beans;

//import com.xtesoft.xtecuannet.framework.model.entities.SecUsers;
import com.xtesoft.xtecuannet.framework.model.services.GenericService;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 *
 * @author xtecuan
 */
public class XBaseBean {

    private static Logger logger = Logger.getLogger(XBaseBean.class);
    protected FacesContext facesContext;
    public static final String OPTIONS_PER_USER = "options_per_user";
    public static final String USER_OBJECT_IN_SESSION = "siaarafia_log_user";
    public static final String USER_INFO = "user_info";
    private static ResourceBundle props;
    @Autowired
    private ResourceBundleMessageSource bundle;

    public String getMessage(String key, Object[] params, Locale locale) {

        return this.getBundle().getMessage(key, params, locale);
    }

    @PostConstruct
    private void init_base() {
        //props = ResourceBundle.getBundle("/Bundle");
//        URL url = Thread.currentThread().getContextClassLoader().getResource("Bundle.properties");
////        
////        logger.info(url.getFile());
////        
////        props = ResourceBundle.getBundle(url.getFile());
//        try {
//            props.load(url.openStream());
//        } catch (IOException ex) {
//            logger.error("Error al abrir el archivo Bundle", ex);
//        }
    }

    public HttpSession getSession() {

        return getRequest().getSession();
    }

    public HttpServletRequest getRequest() {

        return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
    }

    public FacesContext getFacesContext() {

        return facesContext.getCurrentInstance();
    }

    public String getOPTIONS_PER_USER() {
        return OPTIONS_PER_USER;
    }

    public String getUSER_OBJECT_IN_SESSION() {
        return USER_OBJECT_IN_SESSION;
    }

    public Logger getLogger() {
        return logger;
    }

    /**
     * @param istr Byte Array Input Stream
     * @param fileName the File Name
     *
     */
    public void downloadFile(InputStream istr, String fileName) {
        try {
            int contentLength = istr.available();


            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response =
                    (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/x-download");
            response.setContentLength(contentLength);
            response.setHeader("Content-disposition",
                    "attachment; filename=\"" + fileName + "\"");


            while (contentLength-- > 0) {
                response.getOutputStream().write(istr.read());
            }

            istr.close();
            context.responseComplete();
        } catch (Exception e) {
            this.getLogger().error("Error en descarga de archivo: ", e);
        }
    }

    /**
     * @param istr Byte Array Input Stream
     * @param fileName the File Name
     *
     */
    public void downloadFile(byte[] file, String fileName) {
        try {

            ByteArrayInputStream istr = new ByteArrayInputStream(file);
            int contentLength = istr.available();


            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response =
                    (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/x-download");
            response.setContentLength(contentLength);
            response.setHeader("Content-disposition",
                    "attachment; filename=\"" + fileName + "\"");


            while (contentLength-- > 0) {
                response.getOutputStream().write(istr.read());
            }

            istr.close();
            context.responseComplete();
        } catch (Exception e) {
            this.getLogger().error("Error en descarga de archivo: ", e);
        }
    }

//    public SecUsersDTO obtenerUsuarioSesion() {
//
//        return (SecUsersDTO) this.getSession().getAttribute(USER_INFO);
//    }
    public void addMessage(String summary) {


        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void writeByteArrayToFile(File file, byte[] stream) {

        try {
            FileUtils.writeByteArrayToFile(file, stream);
        } catch (IOException ex) {
            this.getLogger().error("Error escribiendo: ", ex);
        }

    }

    /**
     *
     * @param key
     * @return
     */
    public String obtenerMensajeBundle(String key) {

        //return config.getString(key);
        return props.getString(key);

    }

    /**
     *
     * @param key
     * @param params
     * @return
     */
    public String obtenerMensajeBundle(String key, Object[] params) {



        String msgOut = null;

        String msgNo = props.getString(key);

        if (params != null) {
            if (params.length > 0) {

                msgOut = msgNo;
                for (int i = 0; i < params.length; i++) {

                    String myValue = null;

                    if (params[i] != null) {

                        if (params[i] instanceof String) {
                            myValue = (String) params[i];
                        } else if (params[i] instanceof Long) {
                            myValue = String.valueOf(params[i]);
                        } else if (params[i] instanceof java.util.Date) {
                            SimpleDateFormat sdf =
                                    new SimpleDateFormat("dd-MM-yyyy");

                            myValue = sdf.format((java.util.Date) params[i]);
                        } else if (params[i] instanceof BigDecimal) {
                            myValue = String.valueOf((BigDecimal) params[i]);
                        } else if (params[i] instanceof Integer) {
                            myValue = String.valueOf((Integer) params[i]);
                        } else if (params[i] instanceof Double) {
                            myValue = String.valueOf((Double) params[i]);
                        } else {
                            myValue = String.valueOf(params[i]);
                        }
                    } else {

                        myValue = " ";
                    }

                    String tmp = msgOut.replace("{" + i + "}", myValue);
                    msgOut = tmp;

                }

            }
        } else {

            if (msgNo != null) {
                msgOut = msgNo;
            } else {
                msgOut = "null";
            }

        }

        return msgOut;
    }

//    public SecUsers getLoggedUser() {
//
//        return (SecUsers) this.getSession().getAttribute(USER_OBJECT_IN_SESSION);
//    }
    // Returns the contents of the file in a byte array.
    public byte[] getBytesFromFile(File file) {
        byte[] bytes = new byte[1000];
        try {
            InputStream is = new FileInputStream(file);

            // Get the size of the file
            long length = file.length();

            // You cannot create an array using a long type.
            // It needs to be an int type.
            // Before converting to an int type, check
            // to ensure that file is not larger than Integer.MAX_VALUE.
            if (length > Integer.MAX_VALUE) {
                // File is too large
            }

            // Create the byte array to hold the data
            bytes = new byte[(int) length];

            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }

            // Ensure all the bytes have been read in
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }

            // Close the input stream and return bytes
            is.close();
        } catch (Exception ex) {

            logger.error("Error al obtener los bytes de un File: ", ex);
        }
        return bytes;
    }

    public <T> String saveEntity(String outcome, Object entity, GenericService<T> service, Map<String, Object> toValidate) {


        Class eclass = entity.getClass();





        return outcome;

    }

    public boolean validateEntity(Object entity) {

        boolean state = false;


        return state;

    }

    public static void setProps(ResourceBundle props) {
        XBaseBean.props = props;
    }

    public static void setProps(String bundle, Locale locale) {

        XBaseBean.props = ResourceBundle.getBundle(bundle, locale);
    }

    public ResourceBundleMessageSource getBundle() {
        return bundle;
    }

    public void setBundle(ResourceBundleMessageSource bundle) {
        this.bundle = bundle;
    }

    public void addMessage(String header, String detail) {
        FacesMessage msg = new FacesMessage(header, detail);
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public String getIpAddress() {

        ServletRequest sr = ((ServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
        sr.getRemoteAddr();
        return sr.getRemoteAddr();
    }
    
    public Object getFacesBean(String elExp) {

        FacesContext context = FacesContext.getCurrentInstance();
        return context.getELContext().getELResolver().getValue(context.getELContext(), null, elExp);
    }
}
