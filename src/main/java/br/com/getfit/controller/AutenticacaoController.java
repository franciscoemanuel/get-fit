package br.com.getfit.controller;

import br.com.getfit.model.Usuario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Francisco
 */

@SessionScoped
@ManagedBean(name = "autenticacaoController")
public class AutenticacaoController extends ControllerBase implements Serializable{
    
    public static final String INJECTION_NAME = "#{autenticacaoController}";
    private Usuario user;

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    public String logOut(){
        getRequest().getSession().invalidate();
        return "login";
    }
    
}
