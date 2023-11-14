package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.model.Pessoa;

public class PessoaDao {
	
	private Connection conn;

	public PessoaDao(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public void cadastrarPessoa(Pessoa pessoa) throws SQLException {
		PreparedStatement stm = conn.prepareStatement("INSERT INTO T_PESSOA (CD_PESSOA, NOME, CPF) VALUES (?, ?, ?)");
		stm.setInt(1, pessoa.getId());
		stm.setString(2, pessoa.getNome());
		stm.setString(3, pessoa.getCpf());
		
		stm.executeUpdate();
	}
	
	public void removerPessoa(int id) throws IdNotFoundException, SQLException {
		PreparedStatement stm = conn.prepareStatement("DELETE FROM T_PESSOA WHERE CD_PESSOA = ?");
		stm.setInt(1, id);
		
		int linha = stm.executeUpdate();
		if(linha == 0) {
			throw new IdNotFoundException("ID da Pessoa não localizado!");
		}
	}
	
	public void atualizarPessoa(Pessoa pessoa) throws SQLException, IdNotFoundException {
		PreparedStatement stm = conn.prepareStatement("UPDATE T_PESSOA SET NOME = ?, CPF = ? WHERE CD_PESSOA = ?");
		stm.setString(1, pessoa.getNome());
		stm.setString(2, pessoa.getCpf());
		stm.setInt(3, pessoa.getId());
		
		int linha = stm.executeUpdate();
		if(linha == 0) {
			throw new IdNotFoundException("ID da Pessoa não localizado para atualizar!");
		}
	}
}
