package br.com.fiap.service;

import java.sql.Connection;
import java.sql.SQLException;


import br.com.fiap.dao.LocalDao;
import br.com.fiap.exception.BadInfoException;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Local;

public class LocalService {
	private LocalDao localDao;

	public LocalService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		localDao = new LocalDao(conn);
	}
	
	public void cadastrar(Local local) throws SQLException, BadInfoException {
		validar(local);
		localDao.cadastrarLocal(local);
	}
	
	public void atualizar(Local local) throws IdNotFoundException, SQLException, BadInfoException {
		validar(local);
		localDao.atualizarLocal(local);
	}
	
	public void remover(int id) throws IdNotFoundException, SQLException {
		localDao.removerLocal(id);
	}
	
	private void validar(Local local) throws BadInfoException {
		if(local.getBairro() == null || local.getBairro().length() > 50) {
			throw new BadInfoException("Bairro não pode ser nulo ou maior que 50 caracteres!");
		}
		if(local.getCidade() == null || local.getCidade().length() > 50) {
			throw new BadInfoException("Cidade não pode ser nulo ou maior que 50 caracteres!");
		}
		if(local.getLogradouro() == null || local.getLogradouro().length() > 100) {
			throw new BadInfoException("Cidade não pode ser nulo ou maior que 50 caracteres!");
		}
	}
}
