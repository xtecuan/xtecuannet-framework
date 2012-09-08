/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ugb.vistacontrolador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.xml.ws.WebServiceRef;
import sv.edu.ugb.client.webservice.AgendaDTO;
import sv.edu.ugb.client.webservice.EjemploBeanService;

/**
 *
 * @author xtecuan
 */
@Named(value = "agendaBean")
@SessionScoped
public class AgendaBean implements Serializable {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/EjemploBeanService/EjemploBean.wsdl")
    private EjemploBeanService service;
    
    private List<AgendaDTO> listado;

    /**
     * Creates a new instance of AgendaBean
     */
    public AgendaBean() {
    }
    
    @PostConstruct
    private void init(){
        
        this.listado = encontrarlosTodos();
        
        
    }

    private java.util.List<sv.edu.ugb.client.webservice.AgendaDTO> encontrarlosTodos() {
        sv.edu.ugb.client.webservice.EjemploBean port = service.getEjemploBeanPort();
        return port.encontrarlosTodos();
    }

    public List<AgendaDTO> getListado() {
        return listado;
    }

    public void setListado(List<AgendaDTO> listado) {
        this.listado = listado;
    }
    
    
}
