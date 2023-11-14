package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.model.Formulario;
import br.com.fiap.model.Local;
import br.com.fiap.model.Pessoa;
import br.com.fiap.model.Veiculo;

public class FormularioDao {

	private Connection conn;

	public FormularioDao(Connection conn) {
		super();
		this.conn = conn;
	}

	
	public void cadastrarFormulario(Formulario formulario) throws SQLException {
		PreparedStatement stm = conn.prepareStatement(
				"INSERT INTO T_FORMULARIO  (CD_FORMULARIO, CD_VEICULO, CD_PESSOA, CD_LOCAL, DESCRICAO) values (?, ?, ?, ?, ?)");
		stm.setInt(1, formulario.getId());
		stm.setInt(2, formulario.getVeiculo().getId());
		stm.setInt(3, formulario.getPessoa().getId());
		stm.setInt(4, formulario.getLocal().getId());
		stm.setString(5, formulario.getDescricao());

		stm.executeUpdate();
	}

	public void removerFormulario(int id) throws SQLException, IdNotFoundException {
		PreparedStatement stm = conn.prepareStatement("DELETE FROM T_FORMULARIO WHERE CD_FORMULARIO = ?");
		stm.setInt(1, id);

		int linha = stm.executeUpdate();

		if (linha == 0) {
			throw new IdNotFoundException("ID do Formulário não encontrado!");
		}
	}

	public String consultarGuincho(int id) throws SQLException {
		PreparedStatement stm = conn.prepareStatement(
				"SELECT V.PESO, V.ALTURA, V.COMPRIMENTO FROM T_VEICULO V JOIN T_FORMULARIO F ON V.CD_VEICULO = F.CD_VEICULO WHERE F.CD_VEICULO = ?");
		stm.setInt(1, id);
		ResultSet result = stm.executeQuery();

		if (result.next()) {
			double peso = result.getDouble("PESO");
			double altura = result.getDouble("ALTURA");
			double comprimento = result.getDouble("COMPRIMENTO");

			if (peso <= 1000) {
				return "Guincho de Reboque Rápido";
			} else if (peso >= 1000 & peso < 2000) {
				if (altura < 2.50) {
					if (comprimento < 5) {
						return "Guincho Articulado";
					} else if (comprimento >= 5 & comprimento < 7) {
						return "Guincho de Plataforma";
					} else if (comprimento >= 7) {
						return "Guincho de Plataforma";
					}
				}

				else if (altura >= 2.50 & altura < 4.00) {
					if (comprimento < 5) {
						return "Guincho de Cabo";
					} else if (comprimento >= 5 & comprimento < 7) {
						return "Guincho Articulado";
					} else if (comprimento >= 7) {
						return "Guincho de Resgate Urbano";
					}
				}

				else if (altura >= 4) {
					if (comprimento < 5) {
						return "Guincho Compacto";
					} else if (comprimento >= 5 & comprimento < 7) {
						return "Guincho Resistente";
					} else if (comprimento >= 7) {
						return "Guincho Extensível";
					}
				}
			}

			else if (peso >= 2000) {
				return "Guincho de Serviço Pesado";
			}
		}	
		return "Informações fornecidas inválidas!";
	}

	public void atualizarFormulario(Formulario formulario) throws SQLException, IdNotFoundException {
		PreparedStatement stm = conn.prepareStatement("UPDATE T_FORMULARIO SET DESCRICAO = ?");
		stm.setString(1, formulario.getDescricao());

		int linha = stm.executeUpdate();

		if (linha == 0) {
			throw new IdNotFoundException("Formulário não encontrado!");
		}
	}

	public Formulario consultarFormulario(int id) throws SQLException, IdNotFoundException {
		PreparedStatement stm = conn
				.prepareStatement("SELECT V.*, F.*, P.*, L.* FROM T_FORMULARIO F JOIN T_PESSOA P ON F.CD_PESSOA = P.CD_PESSOA JOIN T_LOCAL L ON F.CD_LOCAL = L.CD_LOCAL JOIN T_VEICULO V ON F.CD_VEICULO = V.CD_VEICULO WHERE F.CD_FORMULARIO = ?");
		stm.setInt(1, id);
		ResultSet resultado = stm.executeQuery();

		if (!resultado.next()) {
			throw new IdNotFoundException("ID não localizado para consulta!");
		}

		int codigo = resultado.getInt("CD_FORMULARIO");
		String descricao = resultado.getString("DESCRICAO");

		int codigoVeiculo = resultado.getInt("CD_VEICULO");
		String placa = resultado.getString("PLACA");
		String modelo = resultado.getString("MODELO");
		double peso = resultado.getDouble("PESO");
		double altura = resultado.getDouble("ALTURA");
		double comprimento = resultado.getDouble("COMPRIMENTO");

		int codigoPessoa = resultado.getInt("CD_PESSOA");
		String nome = resultado.getString("NOME");
		String cpf = resultado.getString("CPF");

		int codigoLocal = resultado.getInt("CD_LOCAL");
		String logradouro = resultado.getString("LOGRADOURO");
		String bairro = resultado.getString("BAIRRO");
		String cidade = resultado.getString("CIDADE");
		LocalDate data = resultado.getDate("DATAINCIDENTE").toLocalDate();

		Veiculo veiculo = new Veiculo(codigoVeiculo, placa, modelo, peso, altura, comprimento);

		Pessoa pessoa = new Pessoa(codigoPessoa, nome, cpf);

		Local local = new Local(codigoLocal, logradouro, bairro, cidade, data);

		Formulario formulario = new Formulario(codigo, veiculo, pessoa, local, descricao);

		return formulario;
	}

}
