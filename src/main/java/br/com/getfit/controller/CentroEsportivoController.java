package br.com.getfit.controller;

import br.com.getfit.dao.CentroEsportivoDAO;
import br.com.getfit.model.CentroEsportivo;
import br.com.getfit.model.Turma;
import br.com.getfit.util.SessionUtil;
import java.util.ArrayList;
import java.util.Collection;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Francisco
 */
@ManagedBean
public class CentroEsportivoController {
    
    private CentroEsportivo getCentroEsportivoFromSession() {
        int idUsuario = SessionUtil.getIdUsuario();
        CentroEsportivoDAO centroEsportivoDAO = new CentroEsportivoDAO();
        CentroEsportivo centroEsportivo = centroEsportivoDAO.buscarPorId(idUsuario);
        return centroEsportivo;
    }
    
    public Collection<Turma> getTurmas() {
        Collection<Turma> turmasCentro = new ArrayList<Turma>();
        try {
            CentroEsportivo centroEsportivo = this.getCentroEsportivoFromSession();
            turmasCentro = centroEsportivo.getTurmaCollection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return turmasCentro;
    }
}
