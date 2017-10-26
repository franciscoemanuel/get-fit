/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.getfit.dao;

import br.com.getfit.models.Usuario;
import br.com.getfit.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Francisco
 */
public class UsuarioDAO {
    
    public void salvar(Usuario usuario) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        sessao.persist(usuario);
        sessao.getTransaction().commit();
        sessao.close();
    }
    
    public List<Usuario> listar() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        List<Usuario> usuarios = sessao.getNamedQuery("Usuarios.findAll").list();
        sessao.close();
        return usuarios;
    }
    
}
