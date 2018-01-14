package br.com.getfit.controller;

import br.com.getfit.dao.UsuarioDAO;
import br.com.getfit.model.Usuario;
import br.com.getfit.util.SessionUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Francisco
 */
@RequestScoped
@ManagedBean(name = "loginController")
public class LoginController extends AbstractController {

    @ManagedProperty(value = AutenticacaoController.INJECTION_NAME)
    private AutenticacaoController autenticacaoController;

    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setAutenticacaoController(AutenticacaoController autenticacaoController) {
        this.autenticacaoController = autenticacaoController;
    }

    private Usuario isCredenciaisValidas(String email, String senha) throws Exception {
        Usuario usuario = new UsuarioDAO().findByEmail(email);

        if (usuario == null || !BCrypt.checkpw(senha, usuario.getSenha())) {
            throw new Exception("E-mail ou senha incorretos");
        }

        return usuario;
    }

    public void login() {
        try {
            Usuario usuario = isCredenciaisValidas(email, senha);
            autenticacaoController.setUsuario(usuario);
            SessionUtil.setSessionAttribute("usuario", usuario);
            String linkHomePage = usuario.getTipoUsuario().equals("pessoa") ? "/pessoa" : "/centroEsportivo";
            redirecionarPara(linkHomePage);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            sendErrorMessage(e.getMessage());
        }
    }

}
