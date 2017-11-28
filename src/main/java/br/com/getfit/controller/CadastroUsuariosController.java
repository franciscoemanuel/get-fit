package br.com.getfit.controller;

import br.com.getfit.dao.UsuarioDAO;
import br.com.getfit.model.Usuario;
import javax.faces.bean.ManagedBean;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCauseMessage;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Francisco
 */
@ManagedBean(name = "cadastroUsuariosMB")
public class CadastroUsuariosController extends AbstractController {

    private Usuario usuario;

    public CadastroUsuariosController() {
        inicializar();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void cadastrarUsuario() {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            String senhaCriptografada = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
            usuario.setSenha(senhaCriptografada);
            dao.salvar(usuario);
            limparCampos();
            redirecionarPara("/login");
        } catch (PersistenceException e) {
            this.sendErrorMessage(getRootCauseMessage(e));
        } catch (ConstraintViolationException e) {
            String mensagem = e.getConstraintViolations().iterator().next().getMessage();
            this.sendErrorMessage(mensagem);
        } catch (Exception e) {
            e.printStackTrace();
            this.sendErrorMessage(e.getMessage());
        }
    }

    public void limparCampos() {
        this.usuario = null;
    }

    public void inicializar() {
        this.usuario = new Usuario();
    }

}
