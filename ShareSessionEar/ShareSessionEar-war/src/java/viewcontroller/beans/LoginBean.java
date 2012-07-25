/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viewcontroller.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import model.dto.UserInfo;
import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public class LoginBean implements Serializable {

    private static Logger logger = Logger.getLogger(LoginBean.class);
    private String usuario;
    private String clave;
    private static final String DEFAULT_URL = "index";
    private static final String DEFAULT_SUCCESS_URL = "/app/principal?faces-redirect=true";

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public String doLogin() {
        String outcome = DEFAULT_URL;
        if (getUsuario().equals(UserInfo.DEFAULT_USER) && getClave().equals(UserInfo.DEFAULT_PASS)) {

            UserInfo info = (UserInfo) getFacesBean("userInfo");

            List<Cookie> cookies = new ArrayList<Cookie>(0);

            cookies.add(new Cookie("usuario", info.getUsuario()));
            cookies.add(new Cookie("clave", info.getClave()));
            cookies.add(new Cookie("nombres", info.getNombres()));
            cookies.add(new Cookie("apellidos", info.getApellidos()));

            addCookies(cookies);

            outcome = DEFAULT_SUCCESS_URL;

        } else {
            addMessage("Error Autenticación:", "Usuario/Clave invalidos");
        }
        return encodeURL(outcome);
    }

    public String doCancel() {
        return "index?faces-redirect=true";
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Object getFacesBean(String elExp) {

        FacesContext context = FacesContext.getCurrentInstance();
        return context.getELContext().getELResolver().getValue(context.getELContext(), null, elExp);
    }

    public void addMessage(String summary, String details) {

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary, details));
    }

    public void addCookies(List<Cookie> cookies) {

        for (Cookie cookie : cookies) {
            addCookie(cookie);
        }
    }

    public void addCookie(Cookie cookie) {

        ((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse()).
                addCookie(cookie);
    }

    public String encodeURL(String url) {

        return ((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse()).encodeURL(url);
    }

    public String getInitParameter(String key) {

        return FacesContext.getCurrentInstance().getExternalContext().getInitParameter(key);
    }

    public String getBroApp() {

        String bro = getInitParameter(UserInfo.KEY_BRO_APP);
        logger.info("app2: " + bro);
        return bro;
    }

    public String obtenerUrl() {

        String url = "/" + getBroApp() + "/" + getInitParameter(UserInfo.KEY_BRO_APP_SERVLET) ;
        logger.info("URL2: " + url);
        return encodeURL(url);
    }
}
