package br.com.getfit.controller;

import br.com.getfit.model.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Francisco
 */
@SessionScoped
@ManagedBean(name = "autenticacaoController")
public class AutenticacaoController extends AbstractController implements Serializable {

    public static final String INJECTION_NAME = "#{autenticacaoController}";
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isUsuarioLogado() {
        return this.usuario != null;
    }

    public void logOut() throws IOException {
        getRequest().getSession().invalidate();
        redirecionarPara("login");
    }

}
