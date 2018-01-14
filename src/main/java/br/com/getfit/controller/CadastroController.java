package br.com.getfit.controller;

import br.com.getfit.dao.UsuarioDAO;
import br.com.getfit.model.CentroEsportivo;
import br.com.getfit.model.Pessoa;
import br.com.getfit.model.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCauseMessage;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Francisco
 */
@ViewScoped
@ManagedBean(name = "cadastroUsuariosMB")
public class CadastroController extends AbstractController {
    
    private CentroEsportivo centroEsportivo;
    private Pessoa pessoa;
    private String tipoCadastro;
    
    public CadastroController() {
        inicializar();
    }
    
    public String getTipoCadastro() {
        return tipoCadastro;
    }
    
    public void setTipoCadastro(String tipoCadastro) {
        this.tipoCadastro = tipoCadastro;
    }
    
    public CentroEsportivo getCentroEsportivo() {
        return centroEsportivo;
    }
    
    public void setCentroEsportivo(CentroEsportivo centroEsportivo) {
        this.centroEsportivo = centroEsportivo;
    }
    
    public Pessoa getPessoa() {
        return pessoa;
    }
    
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public void cadastrarUsuario() {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuario = tipoCadastro.equals("pessoa") ? pessoa : centroEsportivo;
            usuario.setTipoUsuario(tipoCadastro);
            String senhaCriptografada = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
            usuario.setSenha(senhaCriptografada);
            dao.salvar(usuario);
            limparCampos();
            redirecionarPara("/login");
        } catch (PersistenceException e) {
            this.sendErrorMessage(getRootCauseMessage(e));
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            String mensagem = e.getConstraintViolations().iterator().next().getMessage();
            this.sendErrorMessage(mensagem);
        } catch (Exception e) {
            e.printStackTrace();
            this.sendErrorMessage(e.getMessage());
        }
    }
    
    public void limparCampos() {
        this.centroEsportivo = null;
        this.pessoa = null;
    }
    
    public void inicializar() {
        this.centroEsportivo = new CentroEsportivo();
        this.pessoa = new Pessoa();
        this.tipoCadastro = "pessoa";;
    }
    
    public static void main(String[] args) {
        CadastroController cadastroController = new CadastroController();
        Pessoa pessoa = new Pessoa();
        pessoa.setEmail("teste3@teste.com");
        pessoa.setNome("teste");
        pessoa.setBio("teste");
        pessoa.setSenha("123456");
        pessoa.setSexo("M".charAt(0));
        cadastroController.setPessoa(pessoa);
        CentroEsportivo centroEsportivo = new CentroEsportivo();
        centroEsportivo.setEmail("teste4@teste.com");
        centroEsportivo.setNome("teste");
        centroEsportivo.setDescricao("teste");
        centroEsportivo.setSenha("123456");
        centroEsportivo.setLocalizacao("Maceió");
        cadastroController.setTipoCadastro("centroEsportivo");
        cadastroController.setCentroEsportivo(centroEsportivo);
        cadastroController.cadastrarUsuario();
    }
    
}
