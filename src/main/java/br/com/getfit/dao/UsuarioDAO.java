package br.com.getfit.dao;

import br.com.getfit.model.Usuario;

/**
 *
 * @author Francisco
 */
public class UsuarioDAO extends AbstractDAO<Usuario> {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario findByLogin(String login) {
        return criaNamedQuery("Usuario.findByLogin").setParameter("login", login).getSingleResult();
    }

    public Usuario findByEmail(String email) {
        return criaNamedQuery("Usuario.findByEmail").setParameter("email", email).getSingleResult();
    }
    
}
