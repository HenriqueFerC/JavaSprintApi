package br.com.fiap.model;

public class Veiculo {
	
	private int id;
	private String placa;
	private String modelo;
	private double peso;
	private double altura;
	private double comprimento;
	
	public Veiculo() {
		super();
	}

	public Veiculo(int id, String placa, String modelo, double peso, double altura, double comprimento) {
		super();
		this.id = id;
		this.placa = placa;
		this.modelo = modelo;
		this.peso = peso;
		this.altura = altura;
		this.comprimento = comprimento;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public double getComprimento() {
		return comprimento;
	}
	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}
	
}
