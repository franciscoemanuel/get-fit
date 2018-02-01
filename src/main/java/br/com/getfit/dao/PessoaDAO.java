package br.com.getfit.dao;

import br.com.getfit.model.Pessoa;

/**
 *
 * @author Francisco
 */
public class PessoaDAO extends AbstractDAO<Pessoa> {

    public PessoaDAO() {
        super(Pessoa.class);
    }
}
