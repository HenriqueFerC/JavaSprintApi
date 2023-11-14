package br.com.fiap.service;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.fiap.dao.PessoaDao;
import br.com.fiap.exception.BadInfoException;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Pessoa;

public class PessoaService {
	
	private PessoaDao pessoaDao;
	
	public PessoaService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		pessoaDao = new PessoaDao(conn);
	}
	
	public void cadastrar(Pessoa pessoa) throws BadInfoException, SQLException {
		validar(pessoa);
		pessoaDao.cadastrarPessoa(pessoa);
	}
	
	public void atualizar(Pessoa pessoa) throws BadInfoException, SQLException, IdNotFoundException {
		validar(pessoa);
		pessoaDao.atualizarPessoa(pessoa);
	}
	
	public void remover(int id) throws IdNotFoundException, SQLException {
		pessoaDao.removerPessoa(id);
	}
	
	private void validar(Pessoa pessoa) throws BadInfoException {
		if(pessoa.getNome() == null || pessoa.getNome().length() > 100) {
			throw new BadInfoException("Nome não pode ser nulo ou maior que 100 caracteres!");
		}
		if(pessoa.getCpf() == null || pessoa.getCpf().length() > 14) {
			throw new BadInfoException("CPF não pode ser nulo ou maior que 14 caracteres!");
		}
	}
}
