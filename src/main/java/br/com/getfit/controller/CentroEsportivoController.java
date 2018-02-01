package br.com.getfit.controller;

import br.com.getfit.dao.CentroEsportivoDAO;
import br.com.getfit.dao.PessoaDAO;
import br.com.getfit.model.CentroEsportivo;
import br.com.getfit.model.Pessoa;
import br.com.getfit.model.Turma;
import br.com.getfit.model.Usuario;
import br.com.getfit.util.SessionUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Francisco
 */
@ManagedBean
public class CentroEsportivoController {
    
    private CentroEsportivo getCentroEsportivoFromSession() {
        CentroEsportivo centroEsportivo = new CentroEsportivo();
        try {
            int idUsuario = SessionUtil.getIdUsuario();
            CentroEsportivoDAO centroEsportivoDAO = new CentroEsportivoDAO();
            centroEsportivo = centroEsportivoDAO.buscarPorId(idUsuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return centroEsportivo;
    }
    
    public Collection<Turma> getTurmas() {
        CentroEsportivo centroEsportivo = this.getCentroEsportivoFromSession();
        return centroEsportivo.getTurmasCentroCollection();      
    }
    
    public Collection<Usuario> getAlunos(){
        Set<Usuario> alunos = new HashSet<Usuario>();
        Collection<Turma> turmas = this.getTurmas();
        for (Turma turma : turmas) {
            for (Usuario usuario : turma.getUsuarioCollection()) {
                alunos.add(usuario);
            }
        }
        return alunos;
    }
    
    public Collection<Turma> getTurmasAluno(Usuario usuario) {
        Collection<Turma> turmas = this.getTurmas();
        Collection<Turma> turmasDoAluno = new ArrayList<Turma>();
        for (Turma turma : turmas) {
            if (turma.getUsuarioCollection().contains(usuario)) {
                turmasDoAluno.add(turma);
            }
        }
        return turmasDoAluno;
    }
    
    public static void main(String[] args) {
        CentroEsportivoDAO centroEsportivoDAO = new CentroEsportivoDAO();
        PessoaDAO pessoaDAO = new PessoaDAO();
        CentroEsportivo centroEsportivo = centroEsportivoDAO.buscarPorId(8);
        Collection<Turma> turmas = centroEsportivo.getTurmasCentroCollection();
        Pessoa pessoa = pessoaDAO.buscarPorId(9);
        for (Turma turma : turmas) {
            System.out.println(turma.getInstrutor());
        }
    }
}
