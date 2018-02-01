package br.com.getfit.controller;

import br.com.getfit.dao.PessoaDAO;
import br.com.getfit.model.Pessoa;
import br.com.getfit.model.Turma;
import br.com.getfit.model.Usuario;
import br.com.getfit.util.SessionUtil;
import java.util.Collection;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Francisco
 */
@ManagedBean
public class PessoaController {
    
    private Pessoa getPessoaFromSession() {
        Pessoa pessoa = new Pessoa();
        try {
            int idUsuario = SessionUtil.getIdUsuario();
            PessoaDAO pessoDAO = new PessoaDAO();
            pessoa = pessoDAO.buscarPorId(idUsuario);
            return pessoa;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pessoa;
    }

    public Collection<Turma> getTurmasUsuario() {
        Usuario usuario = getPessoaFromSession();
        return usuario.getTurmasCentroCollection();
    }
}
