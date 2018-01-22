package br.com.getfit.controller;

import br.com.getfit.model.Turma;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Francisco
 */
@ManagedBean
public class CentroEsportivoController {

    private List<Turma> turmas;

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

}
