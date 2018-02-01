package br.com.getfit.controller;

import br.com.getfit.dao.AtividadeFisicaDAO;
import br.com.getfit.dao.CentroEsportivoDAO;
import br.com.getfit.dao.TurmaDAO;
import br.com.getfit.dao.UsuarioDAO;
import br.com.getfit.model.AtividadeFisica;
import br.com.getfit.model.CentroEsportivo;
import br.com.getfit.model.Turma;
import br.com.getfit.model.Usuario;
import br.com.getfit.util.SessionUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Francisco
 */
@ManagedBean
@ViewScoped
public class TurmaController extends AbstractController {

    private String instrutor;
    private String horario;
    private int idAtividadeSelecionada;
    private BigDecimal mensalidade;
    private Turma turmaSelecionada = new Turma();

    public String getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(String instrutor) {
        this.instrutor = instrutor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getAtividadeSelecionada() {
        return idAtividadeSelecionada;
    }

    public void setAtividadeSelecionada(int atividadeSelecionada) {
        this.idAtividadeSelecionada = atividadeSelecionada;
    }

    public BigDecimal getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(BigDecimal mensalidade) {
        this.mensalidade = mensalidade;
    }

    public void limparCampos() {
        this.horario = null;
        this.idAtividadeSelecionada = 0;
        this.instrutor = null;
        this.mensalidade = null;
    }

    public Turma getTurmaSelecionada() {
        return turmaSelecionada;
    }

    public void setTurmaSelecionada(Turma turmaSelecionada) {
        this.turmaSelecionada = turmaSelecionada;
    }

    public int getIdAtividadeSelecionada() {
        return idAtividadeSelecionada;
    }

    public void setIdAtividadeSelecionada(int idAtividadeSelecionada) {
        this.idAtividadeSelecionada = idAtividadeSelecionada;
    }

    public void cadastrarTurma() {
        try {
            AtividadeFisicaDAO atividadeFisicaDAO = new AtividadeFisicaDAO();
            CentroEsportivoDAO centroEsportivoDAO = new CentroEsportivoDAO();
            int idCentroEsportivo = SessionUtil.getIdUsuario();
            AtividadeFisica atividadeFisica = atividadeFisicaDAO.buscarPorId(idAtividadeSelecionada);
            CentroEsportivo centroEsportivo = centroEsportivoDAO.buscarPorId(idCentroEsportivo);

            Turma turma = new Turma();
            turma.setInstrutor(instrutor);
            turma.setHorario(horario);
            turma.setIdAtividade(atividadeFisica);
            turma.setPreco(mensalidade);
            turma.setIdCentro(centroEsportivo);
            TurmaDAO turmaDAO = new TurmaDAO();

            turmaDAO.salvar(turma);
            this.limparCampos();
            sendInfoMessage("Turma cadastrada com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorMessage("Erro ao cadastrar a turma");
        }
    }

    public List<Turma> getTurmasCadastradas() {
        List<Turma> listaDeTurmas = new ArrayList<>();
        try {
            TurmaDAO turmaDAO = new TurmaDAO();
            listaDeTurmas = turmaDAO.buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorMessage("Erro ao obter lista de turmas");
        }
        return listaDeTurmas;
    }

    public void removerTurma() {
        try {
            int idTurma = turmaSelecionada.getIdTurma();
            TurmaDAO turmaDAO = new TurmaDAO();
            turmaDAO.remover(idTurma);
            turmaSelecionada = null;
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorMessage("Não foi possível remover a turma");
        }
    }

    public void editarTurma() {
        try {
            AtividadeFisicaDAO atividadeFisicaDAO = new AtividadeFisicaDAO();
            AtividadeFisica atividadeFisicaEditada = atividadeFisicaDAO.buscarPorId(turmaSelecionada.getIdAtividade().getIdAtividade());
            turmaSelecionada.setIdAtividade(atividadeFisicaEditada);
            TurmaDAO turmaDAO = new TurmaDAO();
            turmaDAO.atualizar(turmaSelecionada);
            turmaSelecionada = null;
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorMessage("Não foi possível editar a turma");
        }
    }
    
    public void matricularAluno() {
        int idUsuario = SessionUtil.getIdUsuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        TurmaDAO turmaDAO = new TurmaDAO();
        try {
           Usuario usuario = usuarioDAO.buscarPorId(idUsuario);
           turmaSelecionada.getUsuarioCollection().add(usuario);
           turmaDAO.atualizar(turmaSelecionada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cancelarMatricula() {
        int idUsuario = SessionUtil.getIdUsuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        TurmaDAO turmaDAO = new TurmaDAO();
        try {
           Usuario usuario = usuarioDAO.buscarPorId(idUsuario);
           turmaSelecionada.getUsuarioCollection().remove(usuario);
           turmaDAO.atualizar(turmaSelecionada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Collection<Usuario> alunosTurma() {
        int idTurma = this.turmaSelecionada.getIdTurma();
        TurmaDAO turmaDAO = new TurmaDAO();
        Turma turma = turmaDAO.buscarPorId(idTurma);
        return turma.getUsuarioCollection();
    }

}
