package br.com.fiap.model;

import java.time.LocalDate;

public class Local {
	
	 private int id;
	 private String logradouro;
	 private String bairro;
	 private String cidade;
	 private LocalDate dataIncidente;
	 
	public Local() {
		super();
	}

	public Local(int id, String logradouro, String bairro, String cidade, LocalDate dataIncidente) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.dataIncidente = dataIncidente;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDataIncidente() {
		return dataIncidente;
	}

	public void setDataIncidente(LocalDate dataIncidente) {
		this.dataIncidente = dataIncidente;
	}
	
	
}
