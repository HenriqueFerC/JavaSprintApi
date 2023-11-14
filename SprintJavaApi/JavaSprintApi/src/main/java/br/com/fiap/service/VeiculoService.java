package br.com.fiap.service;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.fiap.dao.VeiculoDao;
import br.com.fiap.exception.BadInfoException;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Veiculo;

public class VeiculoService {
	private VeiculoDao veiculoDao;
	
	public VeiculoService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		veiculoDao = new VeiculoDao(conn);
	}
	
	public void cadastrar(Veiculo veiculo) throws SQLException, BadInfoException {
		validar(veiculo);
		veiculoDao.cadastrarVeiculo(veiculo);
	}
	
	public void atualizar(Veiculo veiculo) throws SQLException, IdNotFoundException, BadInfoException {
		validar(veiculo);
		veiculoDao.atualizarVeiculo(veiculo);
	}
	
	public void remover(int id) throws SQLException, IdNotFoundException {
		veiculoDao.removerVeiculo(id);	
	}
	
	public void validar(Veiculo veiculo) throws BadInfoException {
		if(veiculo.getPlaca() == null || veiculo.getPlaca().length() > 20) {
			throw new BadInfoException("Placa não pode ser nulo ou maior que 20 caracteres!");
		}
		if(veiculo.getModelo() == null || veiculo.getModelo().length() > 50) {
			throw new BadInfoException("Modelo não pode ser nulo ou maior que 50 caracteres!");
		}
		if(veiculo.getPeso() < 0) {
			throw new BadInfoException("Peso precisa ser um valor positivo!");
		}
		if(veiculo.getComprimento() < 0) {
			throw new BadInfoException("Comprimento precisa ser um valor positivo!");
		}
		if(veiculo.getAltura() < 0) {
			throw new BadInfoException("Altura precisa ser um valor positivo!");
		}
	}
	
}
