/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clientedirectorioapp;

import org.xtecuan.modelo.ejb.AgendaDTO;

/**
 *
 * @author xtecuan
 */
public class ClienteDirectorioApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        AgendaDTO dto = crearAgenda("Ejemplo Institucion", "22999989", "foca@foca.com.sv", null);

        System.out.println("id: " + dto.getId());

        Long result = actualizarAgenda(Long.valueOf("3"), "csj.gob.sv", null, null, Integer.valueOf("1"));

        System.out.println("resultado act: " + result);

    }

    private static AgendaDTO crearAgenda(java.lang.String institucion, java.lang.String telefono, java.lang.String correo, java.lang.Integer estado) {
        org.xtecuan.modelo.ejb.AgendaFacadeService service = new org.xtecuan.modelo.ejb.AgendaFacadeService();
        org.xtecuan.modelo.ejb.AgendaFacade port = service.getAgendaFacadePort();
        return port.crearAgenda(institucion, telefono, correo, estado);
    }

    private static Long actualizarAgenda(java.lang.Long idUp, java.lang.String institucionUp, java.lang.String telefonoUp, java.lang.String correoUp, java.lang.Integer estadoUp) {
        org.xtecuan.modelo.ejb.AgendaFacadeService service = new org.xtecuan.modelo.ejb.AgendaFacadeService();
        org.xtecuan.modelo.ejb.AgendaFacade port = service.getAgendaFacadePort();
        return port.actualizarAgenda(idUp, institucionUp, telefonoUp, correoUp, estadoUp);
    }
}
