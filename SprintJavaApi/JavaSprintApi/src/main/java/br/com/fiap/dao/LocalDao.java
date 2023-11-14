package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.model.Local;

public class LocalDao {
	
	private Connection conn;

	public LocalDao(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public void cadastrarLocal(Local local) throws SQLException {
		PreparedStatement stm = conn.prepareStatement("INSERT INTO T_LOCAL (CD_LOCAL, LOGRADOURO, BAIRRO,  CIDADE, DATAINCIDENTE) VALUES (?, ?, ?, ?, ?)");
		stm.setInt(1, local.getId());
		stm.setString(2, local.getLogradouro());
		stm.setString(3, local.getBairro());
		stm.setString(4, local.getCidade());
		local.setDataIncidente(LocalDate.now());
		stm.setDate(5, Date.valueOf(local.getDataIncidente()));
		
		stm.executeUpdate();
	}
	
	public void removerLocal(int id) throws IdNotFoundException, SQLException {
		PreparedStatement stm = conn.prepareStatement("DELETE FROM T_LOCAL WHERE CD_LOCAL = ?");
		stm.setInt(1, id);
		
		int linha = stm.executeUpdate();
		if(linha == 0) {
			throw new IdNotFoundException("ID do Local não localizado!");
		}
	}
	
	public void atualizarLocal (Local local) throws IdNotFoundException, SQLException {
		PreparedStatement stm = conn.prepareStatement("UPDATE T_LOCAL SET LOGRADOURO = ?, BAIRRO = ?,  CIDADE = ?, DATAINCIDENTE = ? WHERE CD_LOCAL = ?");
		stm.setString(1, local.getLogradouro());
		stm.setString(2, local.getBairro());
		stm.setString(3, local.getCidade());
		local.setDataIncidente(LocalDate.now());
		stm.setDate(4, Date.valueOf(local.getDataIncidente()));
		stm.setInt(5, local.getId());
		
		int linha = stm.executeUpdate();
		if(linha == 0) {
			throw new IdNotFoundException("Id do Local não localizado para atualizar!");
		}
		
	}
}
