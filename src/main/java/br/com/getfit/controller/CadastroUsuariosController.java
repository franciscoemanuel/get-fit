/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.getfit.controller;

import br.com.getfit.dao.UsuarioDAO;
import br.com.getfit.model.Usuario;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;
import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCauseMessage;

/**
 *
 * @author Francisco
 */
//@Dependent;
@ManagedBean(name = "cadastroUsuariosMB")
public class CadastroUsuariosController extends AbstractController{

    private Usuario usuario;

    /**
     * Creates a new instance of UsuarioMB
     */
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
            dao.salvar(usuario);
            limparCampos();
            redirecionarPara("/login");
        } catch (PersistenceException e) {
            this.mensagemErro(getRootCauseMessage(e));
        } catch (Exception e) {
            e.printStackTrace();
            this.mensagemErro(e.getMessage());
        }
    }

    public void limparCampos() {
        this.usuario = null;
    }

    public void inicializar() {
        this.usuario = new Usuario();
    }

    public void mensagemErro(String mensagem) {
        FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, " - Erro ao inserir"));
    }

}
