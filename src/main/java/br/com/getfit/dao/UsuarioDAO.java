/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.getfit.dao;

import br.com.getfit.model.Usuario;
import br.com.getfit.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Francisco
 */
public class UsuarioDAO extends DAOBase<Usuario> {

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

    public Usuario findByLogin(String login) {
        return createNamedQuery("Usuario.findByLogin").setParameter("login", login).getSingleResult();
    }
    
    public Usuario findByEmail(String email) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Usuario usuario = (Usuario) sessao.getNamedQuery("Usuarios.findByEmail").setParameter("email", email).getSingleResult();
        sessao.close();
        return usuario;
    }

}
