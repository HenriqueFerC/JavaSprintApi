package br.com.fiap.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fiap.dao.FormularioDao;
import br.com.fiap.exception.BadInfoException;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Formulario;

public class FormularioService {

	private FormularioDao formularioDao;

	public FormularioService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		formularioDao = new FormularioDao(conn);
	}
	
	public void cadastrar(Formulario formulario) throws SQLException, BadInfoException {
		validar(formulario);
		formularioDao.cadastrarFormulario(formulario);
	}
	
	public Formulario consultarFormulario(int id) throws SQLException, IdNotFoundException {
		return formularioDao.consultarFormulario(id);
	}
	
	public String consultarGuincho(int id) throws SQLException {
		return formularioDao.consultarGuincho(id);
	}
	
	public void remover(int id) throws SQLException, IdNotFoundException {
		formularioDao.removerFormulario(id);
	}
	
	public void atualizar(Formulario formulario) throws SQLException, IdNotFoundException, BadInfoException {
		validar(formulario);
		formularioDao.atualizarFormulario(formulario);
	}
	
	public void validar(Formulario formulario) throws BadInfoException {
		if(formulario.getDescricao() == null || formulario.getDescricao().length() > 255) {
			throw new BadInfoException("Descrição não pode ser nulo ou maior que 255 caracteres!");
		}
	}
}
