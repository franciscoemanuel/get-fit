/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.getfit.models;

import br.com.getfit.model.Usuario;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Francisco
 */
public class UsuarioTest {

    private Validator validator;

    public UsuarioTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @After
    public void tearDown() {
    }

    private ConstraintViolation<Usuario> getPrimeiraRestricao(Set<ConstraintViolation<Usuario>> restricoes) {
        return restricoes.iterator().next();
    }

    private String getMensagem(ConstraintViolation<Usuario> restricao) {
        return restricao.getMessage();
    }

    private String getNomeDaPropriedade(ConstraintViolation<Usuario> restricao) {
        return restricao.getPropertyPath().iterator().next().getName();
    }

    @Test
    public void usuarioNaoPodeTerNomeVazio() {
        Usuario usuario = new Usuario();
        usuario.setSenha("123456");
        usuario.setEmail("teste@teste.com");

        Set<ConstraintViolation<Usuario>> restricoes = validator.validate(usuario);
        ConstraintViolation<Usuario> restricao = getPrimeiraRestricao(restricoes);

        assertThat(restricoes, hasSize(1));
        assertThat(getNomeDaPropriedade(restricao), is("nome"));
        assertThat(getMensagem(restricao), is("não pode ser nulo"));
    }

    @Test
    public void usuarioNaoPodeTerNomeCurto() {
        Usuario usuario = new Usuario();
        usuario.setNome("F.");
        usuario.setSenha("123456");
        usuario.setEmail("teste@teste.com");

        Set<ConstraintViolation<Usuario>> restricoes = validator.validate(usuario);
        ConstraintViolation<Usuario> restricao = getPrimeiraRestricao(restricoes);

        assertThat(restricoes, hasSize(1));
        assertThat(getNomeDaPropriedade(restricao), is("nome"));
        assertThat(getMensagem(restricao), is("O nome deve ter entre 3 a 255 caracteres"));
    }

    @Test
    public void usuarioNaoPodeTerSenhaVazia() {
        Usuario usuario = new Usuario();
        usuario.setNome("Fulano da Silva");
        usuario.setEmail("teste@teste.com");

        Set<ConstraintViolation<Usuario>> restricoes = validator.validate(usuario);
        ConstraintViolation<Usuario> restricao = getPrimeiraRestricao(restricoes);

        assertThat(restricoes, hasSize(1));
        assertThat(getNomeDaPropriedade(restricao), is("senha"));
        assertThat(getMensagem(restricao), is("não pode ser nulo"));
    }

    @Test
    public void usuarioNaoPodeTerSenhaComMenosDeSeisCaracteres() {
        Usuario usuario = new Usuario();
        usuario.setNome("Fulano da Silva");
        usuario.setSenha("12345");
        usuario.setEmail("teste@teste.com");

        Set<ConstraintViolation<Usuario>> restricoes = validator.validate(usuario);
        ConstraintViolation<Usuario> restricao = getPrimeiraRestricao(restricoes);

        assertThat(restricoes, hasSize(1));
        assertThat(getNomeDaPropriedade(restricao), is("senha"));
        assertThat(getMensagem(restricao), is("A senha deve ter entre 6 a 12 caracteres"));
    }

    @Test
    public void usuarioNaoPodeTerSenhaComMaisDeDozeCaracteres() {
        Usuario usuario = new Usuario();
        usuario.setNome("Fulano da Silva");
        usuario.setSenha("123456789101112");
        usuario.setEmail("teste@teste.com");

        Set<ConstraintViolation<Usuario>> restricoes = validator.validate(usuario);
        ConstraintViolation<Usuario> restricao = getPrimeiraRestricao(restricoes);

        assertThat(restricoes, hasSize(1));
        assertThat(getNomeDaPropriedade(restricao), is("senha"));
        assertThat(getMensagem(restricao), is("A senha deve ter entre 6 a 12 caracteres"));
    }

    @Test
    public void usuarioNaoPodeTerEmailVazio() {
        Usuario usuario = new Usuario();
        usuario.setNome("Fulano da Silva");
        usuario.setSenha("123456");

        Set<ConstraintViolation<Usuario>> restricoes = validator.validate(usuario);
        ConstraintViolation<Usuario> restricao = getPrimeiraRestricao(restricoes);

        assertThat(restricoes, hasSize(1));
        assertThat(getNomeDaPropriedade(restricao), is("email"));
        assertThat(getMensagem(restricao), is("não pode ser nulo"));
    }

    @Test
    public void usuarioDeveTerEmailValido() {
        Usuario usuario = new Usuario();
        usuario.setNome("Fulano da Silva");
        usuario.setSenha("123456");
        usuario.setEmail("teste@.com");

        Set<ConstraintViolation<Usuario>> restricoes = validator.validate(usuario);
        ConstraintViolation<Usuario> restricao = getPrimeiraRestricao(restricoes);

        assertThat(restricoes, hasSize(1));
        assertThat(getNomeDaPropriedade(restricao), is("email"));
        assertThat(getMensagem(restricao), is("Não é um endereço de e-mail"));
    }

    @Test
    public void usuarioDeveTerEmailComMaisDeCincoCaracteres() {
        Usuario usuario = new Usuario();
        usuario.setNome("Fulano da Silva");
        usuario.setSenha("123456");
        usuario.setEmail("a@b.c");

        Set<ConstraintViolation<Usuario>> restricoes = validator.validate(usuario);
        assertThat(restricoes, hasSize(1));

        ConstraintViolation<Usuario> restricao = getPrimeiraRestricao(restricoes);

        assertThat(getNomeDaPropriedade(restricao), is("email"));
        assertThat(getMensagem(restricao), is("O e-mail deve ter entre 6 a 255 caracteres"));
    }

}
