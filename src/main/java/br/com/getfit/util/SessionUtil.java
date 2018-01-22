package br.com.getfit.util;

import br.com.getfit.model.Usuario;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Francisco
 */
public class SessionUtil {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static String getNomeUsuario() {
        return ((Usuario) getSession().getAttribute("usuario")).getNome();
    }
    
     public static int getIdUsuario() {
        return ((Usuario) getSession().getAttribute("usuario")).getIdUsuario();
    }

    public static Usuario getUsuario() {
        return ((Usuario) getSession().getAttribute("usuario"));
    }

    public static void setSessionAttribute(String attrName, Object attrValue) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        request.getSession().setAttribute(attrName, attrValue);
    }
}
