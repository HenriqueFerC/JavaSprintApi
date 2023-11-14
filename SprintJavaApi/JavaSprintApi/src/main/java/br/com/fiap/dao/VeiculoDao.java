package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.model.Veiculo;

public class VeiculoDao {
	
	private Connection conn;

	public VeiculoDao(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public void cadastrarVeiculo(Veiculo veiculo) throws SQLException {
		PreparedStatement stm = conn.prepareStatement("INSERT INTO T_VEICULO (CD_VEICULO, PLACA, MODELO, PESO, ALTURA, COMPRIMENTO) VALUES (?, ?, ?, ?, ?, ?)");
		stm.setInt(1, veiculo.getId());
		stm.setString(2, veiculo.getPlaca());
		stm.setString(3, veiculo.getModelo());
		stm.setDouble(4, veiculo.getPeso());
		stm.setDouble(5, veiculo.getAltura());
		stm.setDouble(6, veiculo.getComprimento());
		
		stm.executeUpdate();
	}
	
	public void removerVeiculo(int id) throws SQLException, IdNotFoundException {
		PreparedStatement stm = conn.prepareStatement("DELETE FROM T_VEICULO WHERE CD_VEICULO = ?");
		stm.setInt(1, id);
		
		int linha = stm.executeUpdate();
		if(linha == 0) {
			throw new IdNotFoundException("ID do Carro não localizado!");
		}
	}
	
	public void atualizarVeiculo(Veiculo veiculo) throws SQLException, IdNotFoundException {
		PreparedStatement stm = conn.prepareStatement("UPDATE T_VEICULO SET PLACA = ?, MODELO = ?, PESO = ?, ALTURA = ?, COMPRIMENTO = ? WHERE CD_VEICULO = ?");
		stm.setString(1, veiculo.getPlaca());
		stm.setString(2, veiculo.getModelo());
		stm.setDouble(3, veiculo.getPeso());
		stm.setDouble(4, veiculo.getAltura());
		stm.setDouble(5, veiculo.getComprimento());
		stm.setInt(6, veiculo.getId());
		
		int linha = stm.executeUpdate();
		if(linha == 0) {
			throw new IdNotFoundException("ID do Carro naõ localizado para atualizar!");
		}
	}
}