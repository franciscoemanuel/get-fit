package br.com.getfit.controller;

import br.com.getfit.dao.CentroEsportivoDAO;
import br.com.getfit.dao.PessoaDAO;
import br.com.getfit.dao.UsuarioDAO;
import br.com.getfit.model.CentroEsportivo;
import br.com.getfit.model.Pessoa;
import br.com.getfit.model.Turma;
import br.com.getfit.model.Usuario;
import br.com.getfit.util.SessionUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Francisco
 */
@ViewScoped
@ManagedBean
public class UsuarioController {
    
    private String tipoUsuario;
    private Pessoa pessoaEditada;
    private CentroEsportivo centroEsportivoEditado;
    private String novaSenha;

    public Pessoa getPessoaEditada() {
        return pessoaEditada;
    }

    public void setPessoaEditada(Pessoa pessoaEditada) {
        this.pessoaEditada = pessoaEditada;
    }

    public CentroEsportivo getCentroEsportivoEditado() {
        return centroEsportivoEditado;
    }

    public void setCentroEsportivoEditado(CentroEsportivo centroEsportivoEditado) {
        this.centroEsportivoEditado = centroEsportivoEditado;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }
    
    public void carregarUsuarioEditado() {
        try {
            int idUsuario = SessionUtil.getIdUsuario();
            tipoUsuario = SessionUtil.getUsuario().getTipoUsuario();
            PessoaDAO pessoaDAO = new PessoaDAO();
            CentroEsportivoDAO centroEsportivoDAO = new CentroEsportivoDAO();
            if (tipoUsuario.equals("pessoa")) {
                this.pessoaEditada = pessoaDAO.buscarPorId(idUsuario);
            } else {
                this.centroEsportivoEditado = centroEsportivoDAO.buscarPorId(idUsuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void editarUsuario() {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = tipoUsuario.equals("pessoa") ? pessoaEditada : centroEsportivoEditado;
            if (novaSenha != null && !novaSenha.equals("")) {
                String senhaCriptografada = BCrypt.hashpw(novaSenha, BCrypt.gensalt());
                usuario.setSenha(senhaCriptografada);
            }
            usuarioDAO.atualizar(usuario);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
