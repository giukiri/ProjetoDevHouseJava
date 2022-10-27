package br.com.devinhouse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.devinhouse.entity.Colaborador;
import br.com.devinhouse.entity.Pessoa;
import br.com.devinhouse.entity.Supervidor;
import br.com.devinhouse.exceptions.CPFNaoValido;

public class Application {
	
	private static List<Pessoa> listaColaboradores = new ArrayList<Pessoa>();
	private static List<Pessoa> listaSupervisores = new ArrayList<Pessoa>();
	private static List<Pessoa> listaGerentes = new ArrayList<Pessoa>();
	
	private static CPFNaoValido erroCpf;
	
	static {
		try {
			listaColaboradores.add(new Colaborador("Col1", "SBCol1", LocalDate.of(1995, 5, 5), "34868876961", "EndeCol1"));
			listaColaboradores.add(new Colaborador("Col1", "SBCol2", LocalDate.of(1995, 6, 5), "32760576469", "EndeCol2"));
			listaColaboradores.add(new Colaborador("Col1", "SBCol3", LocalDate.of(1995, 7, 5), "75922259563", "EndeCol3"));
			listaColaboradores.add(new Colaborador("Col1", "SBCol4", LocalDate.of(1995, 8, 5), "56471756194", "EndeCol4"));
			listaSupervisores.add(new Supervidor("Sup1", "SBSup1", LocalDate.of(1993, 5, 15), "44725914568", "EndeSup1"));
			listaSupervisores.add(new Supervidor("Sup2", "SBSup2", LocalDate.of(1993, 6, 15), "81340515881", "EndeSup2"));
			listaSupervisores.add(new Supervidor("Sup3", "SBSup3", LocalDate.of(1993, 7, 15), "32312626233", "EndeSup3"));
			listaGerentes.add(new Supervidor("Ger1", "SBGer1", LocalDate.of(1991, 5, 25), "73283175152", "EndeGer1"));
			listaGerentes.add(new Supervidor("Ger2", "SBGer2", LocalDate.of(1991, 6, 25), "77249493442", "EndeGer2"));
		} catch (CPFNaoValido e) {
			e.printStackTrace();
			erroCpf = e;
		}
	}

	public static void main(String[] args) {
		if (erroCpf != null) {
			System.out.println(erroCpf.getMessage());
		} else {
			
		}

	}

}
