package br.com.getfit.controller;

import br.com.getfit.dao.AtividadeFisicaDAO;
import br.com.getfit.model.AtividadeFisica;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Francisco
 */
@ManagedBean
public class AtividadeFisicaController {
    
    public List<AtividadeFisica> getListaAtividadesFisicas() {
        AtividadeFisicaDAO atividadeFisicaDAO = new AtividadeFisicaDAO();
        List<AtividadeFisica> listaAtividadesFisicas = atividadeFisicaDAO.buscarTodos();
        return listaAtividadesFisicas;
    }
}
