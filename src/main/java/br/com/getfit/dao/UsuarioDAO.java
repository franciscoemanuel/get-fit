package br.com.getfit.dao;

import br.com.getfit.model.Usuario;
import javax.transaction.Transactional;

/**
 *
 * @author Francisco
 */
@Transactional
public class UsuarioDAO extends AbstractDAO<Usuario> {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario findByLogin(String login) {
        return criaNamedQuery("Usuario.findByLogin").setParameter("login", login).getSingleResult();
    }

    public Usuario findByEmail(String email) {
        return criaNamedQuery("Usuarios.findByEmail").setParameter("email", email).getSingleResult();
    }

}
