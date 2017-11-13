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

    public static Usuario getUsuario() {
        return ((Usuario) getSession().getAttribute("usuario"));
    }
}