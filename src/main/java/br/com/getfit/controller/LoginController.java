package br.com.getfit.controller;

import br.com.getfit.dao.UsuarioDAO;
import br.com.getfit.model.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Francisco
 */
@RequestScoped
@ManagedBean(name = "loginController")
public class LoginController extends ControllerBase {

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

        if (usuario == null || !senha.equals(usuario.getSenha())) {
            throw new Exception("Credenciais inválidas");
        }

        return usuario;
    }

    public void login() {
        try {
            Usuario usuario = isCredenciaisValidas(email, senha);
            System.out.println(usuario.getNome() + " autenticado com sucesso!");
        } catch (Exception e) {
            sendErrorMessage(e.getMessage());
        }
    }

}
