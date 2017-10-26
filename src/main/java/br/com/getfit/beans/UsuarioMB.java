/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.getfit.beans;

import br.com.getfit.dao.UsuarioDAO;
import br.com.getfit.models.Usuario;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Francisco
 */
@Named(value = "usuarioMB")
@Dependent
public class UsuarioMB {
    
    private Usuario usuario;
    private List<Usuario> usuarios;
    
    /**
     * Creates a new instance of UsuarioMB
     */
    public UsuarioMB() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    public void cadastrarUsuario() {
        UsuarioDAO dao = new UsuarioDAO();
        dao.salvar(usuario);
        this.listarUsuarios();
    }
    
    public void listarUsuarios() {
        UsuarioDAO dao = new UsuarioDAO();
        usuarios = dao.listar();
    }
    
    public void inicializar() {
         this.listarUsuarios();
    }
    
}
