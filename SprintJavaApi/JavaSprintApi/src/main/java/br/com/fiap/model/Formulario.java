package br.com.fiap.model;

public class Formulario {
	
	private int id; 
	private Veiculo veiculo;
	private Pessoa pessoa;
	private Local local;
	private String descricao;

	
	
	public Formulario() {
		super();
	}

	public Formulario(int id, Veiculo veiculo, Pessoa pessoa, Local local, String descricao) {
		super();
		this.id = id;
		this.veiculo = veiculo;
		this.pessoa = pessoa;
		this.local = local;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

		
}	
