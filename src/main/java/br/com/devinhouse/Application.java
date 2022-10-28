package br.com.devinhouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.devinhouse.entity.Funcionario;
import br.com.devinhouse.entity.Gerente;
import br.com.devinhouse.entity.Pessoa;
import br.com.devinhouse.entity.Supervidor;
import br.com.devinhouse.exceptions.CPFNaoValido;

public class Application {
	
	private static List<Pessoa> listaColaboradores = new ArrayList<Pessoa>();

	
	private static CPFNaoValido erroCpf;
	
	static {
		try {
			listaColaboradores.add(new Funcionario("Col1", "SBCol1", LocalDate.of(1995, 5, 5), "34868876961", "EndeCol1"));
			listaColaboradores.add(new Funcionario("Col1", "SBCol2", LocalDate.of(1995, 6, 5), "32760576469", "EndeCol2"));
			listaColaboradores.add(new Funcionario("Col1", "SBCol3", LocalDate.of(1995, 7, 5), "75922259563", "EndeCol3"));
			listaColaboradores.add(new Funcionario("Col1", "SBCol4", LocalDate.of(1995, 8, 5), "56471756194", "EndeCol4"));
			listaColaboradores.add(new Supervidor("Sup1", "SBSup1", LocalDate.of(1993, 5, 15), "44725914568", "EndeSup1"));
			listaColaboradores.add(new Supervidor("Sup2", "SBSup2", LocalDate.of(1993, 6, 15), "81340515881", "EndeSup2"));
			listaColaboradores.add(new Supervidor("Sup3", "SBSup3", LocalDate.of(1993, 7, 15), "32312626233", "EndeSup3"));
			listaColaboradores.add(new Gerente("Ger1", "SBGer1", LocalDate.of(1991, 5, 25), "73283175152", "EndeGer1"));
			listaColaboradores.add(new Gerente("Ger2", "SBGer2", LocalDate.of(1991, 6, 25), "77249493442", "EndeGer2"));
		} catch (CPFNaoValido e) {
			e.printStackTrace();
			erroCpf = e;
		}
	}

	public static void main(String[] args) {
		if (erroCpf != null) {
			System.out.println(erroCpf.getMessage());
		} else {
			System.out.println("DOCin");
			System.out.println("");
			System.out.println("");

			Boolean termina = false;

			do{
				listaColaboradores();
				termina = menuPrincipal(termina);

			} while(!termina);

		}

	}

	private static Boolean menuPrincipal(Boolean termina) {
		Scanner menuPrincipal = new Scanner(System.in);

		System.out.println("Com qual colaborador vou entrar no sistema?");
		Integer col = menuPrincipal.nextInt();
		Pessoa colaboradorLogado =  pesquisarColaborador(col);

		if(colaboradorLogado == null){
			System.out.println("Colaborador não encontrado, digite novamente.");
		} else {
			System.out.println(colaboradorLogado.getNome());
			termina = true;
		}
		return termina;
	}

	private static Pessoa pesquisarColaborador(Integer col) {
		for(Pessoa p : listaColaboradores){
			if(p.getIdentificador().equals(col)){
				return p;
			}
		}

		return null;
	}

	private static void listaColaboradores() {
		System.out.println("INDENTIFICADOR | NOME | PERFIL");
		for(Pessoa p : listaColaboradores){
			System.out.println(p.getIdentificador() + " | " + p.getNome() + " " + p.getSobrenome() + " | " + p.getPerfil());


		}
	}

}
