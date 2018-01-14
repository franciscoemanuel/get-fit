package br.com.getfit.models;

import br.com.getfit.model.Pessoa;
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
public class PessoaTest {

    private Validator validator;

    public PessoaTest() {
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

    private ConstraintViolation<Pessoa> getPrimeiraRestricao(Set<ConstraintViolation<Pessoa>> restricoes) {
        return restricoes.iterator().next();
    }

    private String getMensagem(ConstraintViolation<Pessoa> restricao) {
        return restricao.getMessage();
    }

    private String getNomeDaPropriedade(ConstraintViolation<Pessoa> restricao) {
        return restricao.getPropertyPath().iterator().next().getName();
    }

    @Test
    public void pessoaNaoPodeTerNomeVazio() {
        Pessoa pessoa = new Pessoa();
        pessoa.setSenha("123456");
        pessoa.setEmail("teste@teste.com");

        Set<ConstraintViolation<Pessoa>> restricoes = validator.validate(pessoa);
        ConstraintViolation<Pessoa> restricao = getPrimeiraRestricao(restricoes);
        assertThat(restricoes, hasSize(1));
        assertThat(getNomeDaPropriedade(restricao), is("nome"));
        assertThat(getMensagem(restricao), is("não pode ser nulo"));
    }

    @Test
    public void pessoaNaoPodeTerNomeCurto() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("F.");
        pessoa.setSenha("123456");
        pessoa.setEmail("teste@teste.com");

        Set<ConstraintViolation<Pessoa>> restricoes = validator.validate(pessoa);
        ConstraintViolation<Pessoa> restricao = getPrimeiraRestricao(restricoes);

        assertThat(restricoes, hasSize(1));
        assertThat(getNomeDaPropriedade(restricao), is("nome"));
        assertThat(getMensagem(restricao), is("O nome deve ter entre 3 a 255 caracteres"));
    }

    @Test
    public void pessoaNaoPodeTerSenhaVazia() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Fulano da Silva");
        pessoa.setEmail("teste@teste.com");

        Set<ConstraintViolation<Pessoa>> restricoes = validator.validate(pessoa);
        ConstraintViolation<Pessoa> restricao = getPrimeiraRestricao(restricoes);

        assertThat(restricoes, hasSize(1));
        assertThat(getNomeDaPropriedade(restricao), is("senha"));
        assertThat(getMensagem(restricao), is("não pode ser nulo"));
    }

    @Test
    public void pessoaNaoPodeTerEmailVazio() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Fulano da Silva");
        pessoa.setSenha("123456");

        Set<ConstraintViolation<Pessoa>> restricoes = validator.validate(pessoa);
        ConstraintViolation<Pessoa> restricao = getPrimeiraRestricao(restricoes);

        assertThat(restricoes, hasSize(1));
        assertThat(getNomeDaPropriedade(restricao), is("email"));
        assertThat(getMensagem(restricao), is("não pode ser nulo"));
    }

    @Test
    public void pessoaDeveTerEmailValido() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Fulano da Silva");
        pessoa.setSenha("123456");
        pessoa.setEmail("teste@.com");

        Set<ConstraintViolation<Pessoa>> restricoes = validator.validate(pessoa);
        ConstraintViolation<Pessoa> restricao = getPrimeiraRestricao(restricoes);

        assertThat(restricoes, hasSize(1));
        assertThat(getNomeDaPropriedade(restricao), is("email"));
        assertThat(getMensagem(restricao), is("Não é um endereço de e-mail"));
    }

    @Test
    public void pessoaDeveTerEmailComMaisDeCincoCaracteres() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Fulano da Silva");
        pessoa.setSenha("123456");
        pessoa.setEmail("a@b.c");

        Set<ConstraintViolation<Pessoa>> restricoes = validator.validate(pessoa);
        assertThat(restricoes, hasSize(1));

        ConstraintViolation<Pessoa> restricao = getPrimeiraRestricao(restricoes);

        assertThat(getNomeDaPropriedade(restricao), is("email"));
        assertThat(getMensagem(restricao), is("O e-mail deve ter entre 6 a 255 caracteres"));
    }

}
