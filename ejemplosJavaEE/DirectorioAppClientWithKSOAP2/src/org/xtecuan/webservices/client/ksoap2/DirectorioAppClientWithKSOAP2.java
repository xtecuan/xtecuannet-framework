/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.webservices.client.ksoap2;

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import org.xtecuan.modelo.dto.AgendaDTO;

/**
 *
 * @author xtecuan
 */
public class DirectorioAppClientWithKSOAP2 {

    private static final String Method = "encontrarPorEjemploJson";
    private static final String namespace = "http://ejb.modelo.xtecuan.org/";
    private static final String accionSoap = namespace + Method;
    private static final String host = "192.168.1.5";
    private static final String port = "37550";
    private static final String url = "http://" + host + ":" + port + "/AgendaFacadeService/AgendaFacade?WSDL";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SoapObject request = new SoapObject(namespace, Method);
        // TODO code application logic here
        request.addProperty("institucionLike1", "SAMPLE");
        request.addProperty("telefonoLike1", new String());
        request.addProperty("correoLike1", new String());
        request.addProperty("estadoLike1", Integer.valueOf(1));

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

//        envelope.dotNet = false;
        envelope.avoidExceptionForUnknownProperty = true;

        envelope.addMapping(namespace, "agendaDTO", AgendaDTO.class);

        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(url);

        try {
            androidHttpTransport.call(accionSoap, envelope);

            if (androidHttpTransport != null) {

                System.out.println("Entro!!!");

                Object resultsRequestSOAP = envelope.getResponse();


                if (resultsRequestSOAP != null) {

                    String salidaJson = resultsRequestSOAP.toString();

                    System.out.println(salidaJson);

                    List<AgendaDTO> dtos = parseJsonAnswerAgendaDTO(salidaJson);

                    for (AgendaDTO dto : dtos) {

                        System.out.println(dto);
                    }
                } else {
                    System.out.println("NULL");

                }
            }

        } catch (IOException ex) {
            Logger.getLogger(DirectorioAppClientWithKSOAP2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XmlPullParserException ex) {
            Logger.getLogger(DirectorioAppClientWithKSOAP2.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public static List<AgendaDTO> parseJsonAnswerAgendaDTO(String jsonArray) {

        List<AgendaDTO> salida = new ArrayList<AgendaDTO>(0);


        try {
            JsonReader reader = new JsonReader(new StringReader(jsonArray));

            reader.beginArray();



            while (reader.hasNext()) {

                AgendaDTO dto = new AgendaDTO();

                reader.beginObject();

                String name = reader.nextName();

                Long id = reader.nextLong();
                reader.nextName();
                String insti = reader.nextString();
                reader.nextName();
                String tel = reader.nextString();
                reader.nextName();
                String corr = reader.nextString();
                reader.nextName();
                Integer est = reader.nextInt();

                boolean haveClave = false;
                String nextName = reader.nextName();

                System.out.println(nextName);

                if (nextName.equals("idcat")) {

                    Integer idcat = reader.nextInt();

                    System.out.println(idcat);

                    dto.setIdcat(idcat);

                } else if (nextName.equals("clave")) {




                    String clave = reader.nextString();
                    dto.setClave(clave);
                    reader.nextName();
                    Integer idcat = reader.nextInt();
                    dto.setIdcat(idcat);
                }


                if (id != null) {
                    dto.setId(id);
                }

                if (insti != null) {
                    dto.setInstitucion(insti);
                }

                if (tel != null) {
                    dto.setTelefono(tel);
                }

                if (corr != null) {
                    dto.setCorreo(corr);
                }

                if (est != null) {
                    dto.setEstado(est);
                }





                System.out.println(dto);
                salida.add(dto);


                reader.endObject();

            }

            reader.endArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return salida;


    }
}
