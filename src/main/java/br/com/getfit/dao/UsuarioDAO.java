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
        return criaNamedQuery("Usuarios.findByEmail").setParameter("email", email).getSingleResult();
    }
    
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = new Usuario();
        usuario.setNome("teste");
        usuario.setSenha("123456");
        usuario.setEmail("teste4@teste.com");
        dao.salvar(usuario);
        usuario.setNome("nome diferente");
        //usuario.setEmail("teste@teste.com");
        dao.atualizar(usuario);
    }

}
