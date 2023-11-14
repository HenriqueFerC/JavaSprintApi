package br.com.fiap.teste;

import java.time.LocalDate;

import br.com.fiap.model.Formulario;
import br.com.fiap.model.Local;
import br.com.fiap.model.Pessoa;
import br.com.fiap.model.Veiculo;
import br.com.fiap.service.FormularioService;
import br.com.fiap.service.LocalService;
import br.com.fiap.service.PessoaService;
import br.com.fiap.service.VeiculoService;

public class Terminal {
	public static void main(String[] args) {

		Local local = new Local(1, "Avenida Lins", "IDK", "São Paulo", LocalDate.of(2023, 12, 11));
		Veiculo veiculo = new Veiculo(1, "ABC1324", "civic", 999, 2.4, 5);
		Pessoa pessoa = new Pessoa(1, "Rick", "53163512432");
		Formulario formulario = new Formulario(1, veiculo, pessoa, local, "Caiu legal caiu bacana caiu mermo");
		
		
		try {
			//System.out.println(formulario.getId());
			//System.out.println(formulario.getVeiculo().getId());
			//System.out.println(formulario.getPessoa().getId());
			//System.out.println(formulario.getLocal().getId());
			//System.out.println(formulario.getDescricao());
			FormularioService formServ = new FormularioService();
			System.out.println(formServ.consultarGuincho(1));
			System.out.println("Gravado!");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
