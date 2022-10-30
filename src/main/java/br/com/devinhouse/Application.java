package br.com.devinhouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.devinhouse.entity.*;
import br.com.devinhouse.exceptions.CPFNaoValido;

public class Application {

	private static List<Pessoa> listaColaboradores = new ArrayList<>();
	private static List<Documento> listaDocumentos = new ArrayList<>();
	private static List<Acoes> listaAcoesDoSistema = new ArrayList<>();

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
			Pessoa colaboradorLogado = null;
			FluxoEnum fluxoAtual = FluxoEnum.MENU_INICIAL;
			System.out.println("DOCin");
			System.out.println("");
			System.out.println("");

			Boolean termina = false;


			do {

				System.out.println("Fluxo atual : " + fluxoAtual);

				switch (fluxoAtual) {
					case MENU_INICIAL:
						colaboradorLogado = null;
						apresentarColaboradores(listaColaboradores);
						colaboradorLogado = menuInicial();
						fluxoAtual = getFluxoMenuInicial(colaboradorLogado);
						break;
					case MENU_FUNCIONARIO:
						fluxoAtual = menuFuncionario(colaboradorLogado);
						break;
					case MENU_SUPERVISOR:
						fluxoAtual = menuSupervisor(colaboradorLogado);
						break;
					case MENU_GERENTE:
						fluxoAtual = menuGerente(colaboradorLogado);
						break;
					case TERMINAR:
						termina = true;
						break;
					case MENU_RELATORIOS:
						fluxoAtual = menuRelatorios(colaboradorLogado);
						break;
				}

			} while (!termina);

		}

	}

	private static FluxoEnum menuRelatorios(Pessoa colaboradorLogado) {
		Scanner tramitarDoc = new Scanner(System.in);
		System.out.println("Escolha 1 para listar todos documentos, 2 para listar todos colaboradores, 3 para listar documentos arquivados, " +
				" \n 4 para listar documentos ativos, 5 para listar todos funcionarios, 6 para listar supervisores, " +
				"\n 7 para listar gerentes, 8 para logar com outro colaborador, 9 para sair do sistema e 10 para lista de Acoes");
		Integer opcao = null;
		try {
			opcao = tramitarDoc.nextInt();
		} catch (Exception e) {
			System.out.println("Opcao invalida!");
			menuRelatorios(colaboradorLogado);
		}

		FluxoEnum fluxo = FluxoEnum.MENU_RELATORIOS;

		if (opcao < 1 || opcao > 9) {
			System.out.println("Opcao invalida!");
			menuRelatorios(colaboradorLogado);
		}

		listaAcoesDoSistema.add(new Acoes(TipoAcaoEnum.RELATORIO, colaboradorLogado, opcao));

		if (opcao.equals(1)) {
			apresentarDocumentos(listaDocumentos);
		} else if (opcao.equals(2)) {
			apresentarColaboradores(listaColaboradores);
		} else if (opcao.equals(3)) {
			List<Documento> docs = listarDocumentosPorEstado(EstadoEnum.ARQUIVADO);
			apresentarDocumentos(docs);
		} else if (opcao.equals(4)) {
			List<Documento> docs = listarDocumentosPorEstado(EstadoEnum.ATIVO);
			apresentarDocumentos(docs);
		} else if (opcao.equals(5)) {
			List<Pessoa> lista = listaColaboradores(PerfilEnum.FUNCIONARIO);
			apresentarColaboradores(lista);
		} else if (opcao.equals(6)) {
			List<Pessoa> lista = listaColaboradores(PerfilEnum.SUPERVISOR);
			apresentarColaboradores(lista);
		} else if (opcao.equals(7)) {
			List<Pessoa> lista = listaColaboradores(PerfilEnum.GERENTE);
			apresentarColaboradores(lista);
		} else if (opcao.equals(8)) {
			fluxo = FluxoEnum.MENU_INICIAL;
		} else if (opcao.equals(9)) {
			fluxo = FluxoEnum.TERMINAR;
		} else if (opcao.equals(9)) {
			System.out.println("Tipo | Colaborador | Data Hora | Documento(ID) | Opcao Relatorio");
			for (Acoes a : listaAcoesDoSistema) {
				System.out.println(a.getTipo() + " | " + a.getColaborador().getNome() + " " + a.getColaborador().getSobrenome() + " | " +
						a.getDataHora() + " | " + (a.getDocumento() != null ? a.getDocumento().getIdentificador() : "") + " | "
								+ (a.getOpcaoRelatorio() != null ? a.getOpcaoRelatorio() : "") );
			}
		}
		return fluxo;
	}

	private static FluxoEnum menuGerente(Pessoa colaboradorLogado) {
		Scanner tramitarDoc = new Scanner(System.in);
		System.out.println("Escolha 1 para verificar documentos, 2 para voltar ao menu anterior, \n " +
				"3 para logar com outro colaborador, 4 para Relatorios e 5 para sair do sistema.");

		Integer opcao = null;
		try {
			opcao = tramitarDoc.nextInt();
		} catch (Exception e) {
			System.out.println("Opcao invalida!");
			menuGerente(colaboradorLogado);
		}

		FluxoEnum fluxo = FluxoEnum.MENU_GERENTE;

		if (opcao < 1 || opcao > 5) {
			System.out.println("Opcao invalida!");
			menuGerente(colaboradorLogado);
		}

		if (opcao.equals(1)) {
			List<Documento> lista = listarDocumentosDoColaborador(colaboradorLogado);
			if (lista.isEmpty()) {
				System.out.println("Nenhum documento para verificar!");
			} else {
				escolherDocumentoParaArquivar(lista, colaboradorLogado);
			}
		} else if (opcao.equals(3)) {
			fluxo = FluxoEnum.MENU_INICIAL;
		} else if (opcao.equals(4)) {
			fluxo = FluxoEnum.MENU_RELATORIOS;
		} else if (opcao.equals(5)) {
			fluxo = FluxoEnum.TERMINAR;
		}

		return fluxo;
	}

	private static void escolherDocumentoParaArquivar(List<Documento> lista, Pessoa colaboradorLogado) {
		Documento documento = escolherDocumento(lista, "Escolha um documento para arquivar.");
		documento.setEstado(EstadoEnum.ARQUIVADO);
		listaAcoesDoSistema.add(new Acoes(TipoAcaoEnum.ARQUIVAR_DOCUMENTO, colaboradorLogado, documento));
	}

	private static FluxoEnum menuSupervisor(Pessoa colaboradorLogado) {

		Scanner tramitarDoc = new Scanner(System.in);
		System.out.println("Escolha 1 para verificar documentos, 2 para voltar ao menu anterior, \n " +
				"3 para logar com outro colaborador, 4 para Relatorios e 5 para sair do sistema.");

		Integer opcao = null;
		try {
			opcao = tramitarDoc.nextInt();
		} catch (Exception e) {
			System.out.println("Opcao invalida!");
			menuSupervisor(colaboradorLogado);
		}

		FluxoEnum fluxo = FluxoEnum.MENU_SUPERVISOR;

		if (opcao < 1 || opcao > 5) {
			System.out.println("Opcao invalida!");
			menuSupervisor(colaboradorLogado);
		}

		if (opcao.equals(1)) {
			List<Documento> lista = listarDocumentosDoColaborador(colaboradorLogado);
			if (lista.isEmpty()) {
				System.out.println("Nenhum documento para verificar!");
			} else {
				verificarDocumentoSupervisor(colaboradorLogado, lista);
			}
		} else if (opcao.equals(3)) {
			fluxo = FluxoEnum.MENU_INICIAL;
		} else if (opcao.equals(4)) {
			fluxo = FluxoEnum.MENU_RELATORIOS;
		} else if (opcao.equals(5)) {
			fluxo = FluxoEnum.TERMINAR;
		}

		return fluxo;

	}

	private static FluxoEnum verificarDocumentoSupervisor(Pessoa colaboradorLogado, List<Documento> documentosSupervisor) {
		apresentarDocumentos(documentosSupervisor);
		Documento documento = escolherDocumento(documentosSupervisor, "Escolha um documento.");
		return tramitarDocumento(colaboradorLogado, documento);
	}

	private static void apresentarDocumentos(List<Documento> documentos) {
		System.out.println("Identificador | Link | Estado | Colaborador Responsavel");
		for (Documento d : documentos) {
			System.out.println(d.getIdentificador() + " | " + d.getLink() + " | " + d.getEstado() +
					" | " + d.getColaboradorResponsavel().getNome() + " " + d.getColaboradorResponsavel().getSobrenome());
		}
	}

	private static List<Documento> listarDocumentosDoColaborador(Pessoa colaboradorLogado) {
		List<Documento> listaDocumentosDoSupervidor = new ArrayList<>();
		for (Documento d : listaDocumentos) {
			if (d.getEstado().equals(EstadoEnum.ATIVO) && d.getColaboradorResponsavel().getIdentificador().equals(colaboradorLogado.getIdentificador())) {
				listaDocumentosDoSupervidor.add(d);
			}
		}
		return listaDocumentosDoSupervidor;
	}

	private static List<Documento> listarDocumentosPorEstado(EstadoEnum estado) {
		List<Documento> listaDocumentosDoSupervidor = new ArrayList<>();
		for (Documento d : listaDocumentos) {
			if (d.getEstado().equals(estado)) {
				listaDocumentosDoSupervidor.add(d);
			}
		}
		return listaDocumentosDoSupervidor;
	}

	private static FluxoEnum tramitarDocumento(Pessoa colaboradorLogado, Documento documento) {
		Scanner tramitarDoc = new Scanner(System.in);
		System.out.println("Escolha 1 para Tramitar, 2 para Recusar o Documento, 3 para voltar ao menu anterior, \n" +
				"4 para logar com outro colaborador, 5 para Relatorios e 6 para sair do sistema.");
		Integer opcao = null;
		try {
			opcao = tramitarDoc.nextInt();
		} catch (Exception e) {
			System.out.println("Opcao invalida!");
			tramitarDocumento(colaboradorLogado, documento);
		}

		FluxoEnum fluxo = FluxoEnum.MENU_SUPERVISOR;

		if (opcao < 1 || opcao > 6) {
			System.out.println("Opcao invalida!");
			tramitarDocumento(colaboradorLogado, documento);
		}

		if (opcao.equals(1)) {
			List<Pessoa> lista = listaColaboradores(PerfilEnum.GERENTE);
			apresentarColaboradores(lista);
			escolherGerente(lista, documento);
			listaAcoesDoSistema.add(new Acoes(TipoAcaoEnum.TRAMITAR_DOCUMENTO, colaboradorLogado, documento));
		} else if (opcao.equals(2)) {
			documento.setColaboradorResponsavel(documento.getColoboradorCriouDocumento());
			listaAcoesDoSistema.add(new Acoes(TipoAcaoEnum.RECUSAR_DOCUMENTO, colaboradorLogado, documento));
		} else if (opcao.equals(4)) {
			fluxo = FluxoEnum.MENU_INICIAL;
		} else if (opcao.equals(5)) {
			fluxo = FluxoEnum.MENU_RELATORIOS;
		} else if (opcao.equals(6)) {
			fluxo = FluxoEnum.TERMINAR;
		}

		return fluxo;
	}

	private static void escolherGerente(List<Pessoa> gerentes, Documento documento) {
		Scanner escolherGer = new Scanner(System.in);
		System.out.println("Escolha um Gerente.");

		Integer opcao = null;
		try {
			opcao = escolherGer.nextInt();
		} catch (Exception e) {
			System.out.println("Opcao invalida!");
			escolherGerente(gerentes, documento);
		}

		Pessoa gerEscolhido = null;
		for (Pessoa p : gerentes) {
			if (p.getIdentificador().equals(opcao)) {
				gerEscolhido = p;
				break;
			}
		}
		if (gerEscolhido == null) {
			escolherGerente(gerentes, documento);
		}
		documento.setColaboradorResponsavel(gerEscolhido);
	}

	private static Documento escolherDocumento(List<Documento> listaDocumento, String mensagem) {
		Scanner escolherDoc = new Scanner(System.in);
		System.out.println(mensagem);

		Integer opcao = null;
		try {
			opcao = escolherDoc.nextInt();
		} catch (Exception e) {
			System.out.println("Opcao invalida!");
			escolherDocumento(listaDocumento, mensagem);
		}

		Documento docEscolhido = null;
		for (Documento d : listaDocumento) {
			if (d.getIdentificador().equals(opcao)) {
				docEscolhido = d;
				break;
			}
		}
		if (docEscolhido == null) {
			escolherDocumento(listaDocumento, mensagem);
		}
		return docEscolhido;
	}

	private static Pessoa menuInicial() {
		Scanner menuInicial = new Scanner(System.in);

		System.out.println("Com qual colaborador vou entrar no sistema?");

		Integer col = null;
		try {
			col = menuInicial.nextInt();
		} catch (Exception e) {
			System.out.println("Opcao invalida!");
			return menuInicial();
		}

		Pessoa colaboradorLogado = pesquisarColaborador(col);

		if (colaboradorLogado == null) {
			System.out.println("Colaborador não encontrado, digite novamente.");
			colaboradorLogado = menuInicial();
		}

		return colaboradorLogado;

	}

	private static FluxoEnum getFluxoMenuInicial(Pessoa colaboradorLogado) {
		FluxoEnum fluxoAtual = null;
		if (colaboradorLogado.getPerfil().equals(PerfilEnum.FUNCIONARIO)) {
			fluxoAtual = FluxoEnum.MENU_FUNCIONARIO;
		} else if (colaboradorLogado.getPerfil().equals(PerfilEnum.SUPERVISOR)) {
			fluxoAtual = FluxoEnum.MENU_SUPERVISOR;
		} else if (colaboradorLogado.getPerfil().equals(PerfilEnum.GERENTE)) {
			fluxoAtual = FluxoEnum.MENU_GERENTE;
		}
		listaAcoesDoSistema.add(new Acoes(TipoAcaoEnum.ACESSO, colaboradorLogado));
		return fluxoAtual;
	}

	private static FluxoEnum menuFuncionario(Pessoa colaboradorLogado) {
		FluxoEnum fluxoAtual =  FluxoEnum.MENU_FUNCIONARIO;
		Scanner menuFuncionario = new Scanner(System.in);
		System.out.println("Escolha 1 para criar de documento, 2 para encaminhar documento, \n 3 para logar com outro usuario, 4 para Relatorios e 5 para sair");

		Integer opcao = null;
		try {
			opcao = menuFuncionario.nextInt();
		} catch (Exception e) {
			System.out.println("Opcao invalida!");
			menuFuncionario(colaboradorLogado);
		}

		if (opcao < 1 || opcao > 5) {
			System.out.println("opção inválida!");
			menuFuncionario(colaboradorLogado);
		}
		if (opcao.equals(1)) {
			criarDocumento(colaboradorLogado);
		} else if (opcao.equals(2)) {
			encaminharDocumento(colaboradorLogado);
		} else if (opcao.equals(3)) {
			colaboradorLogado = null;
			fluxoAtual = FluxoEnum.MENU_INICIAL;
		} else if (opcao.equals(4)) {
			fluxoAtual = FluxoEnum.MENU_RELATORIOS;
		} else if (opcao.equals(5)) {
			fluxoAtual = FluxoEnum.TERMINAR;
		}
		return fluxoAtual;

	}

	private static void encaminharDocumento(Pessoa colaboradorLogado) {
		List<Documento> docsParaEncaminhar = listaDocumentosPendentesDeEncaminhamento(colaboradorLogado);
		if (docsParaEncaminhar.size() > 0) {
			System.out.println("Identificador | Link ");
			for (Documento d : docsParaEncaminhar) {
				System.out.println(d.getIdentificador() + " | " + d.getLink());
			}
			Scanner encDoc = new Scanner(System.in);
			System.out.println("Qual Documento sera encaminhado?");

			Integer opcao = null;
			try {
				opcao = encDoc.nextInt();
			} catch (Exception e) {
				System.out.println("Opcao invalida!");
				encaminharDocumento(colaboradorLogado);
			}

			Documento docParaEncaminhar = null;
			for (Documento d : docsParaEncaminhar) {
				if (d.getIdentificador().equals(opcao)) {
					docParaEncaminhar = d;
					break;
				}
			}
			if (docParaEncaminhar == null) {
				System.out.println("Opcao Invalida!");
				encaminharDocumento(colaboradorLogado);
			}
			listarSupervisores();
			encaimnharParaSupervisor(docParaEncaminhar);
			listaAcoesDoSistema.add(new Acoes(TipoAcaoEnum.ENCAMINHAR_DOCUMENTO, colaboradorLogado, docParaEncaminhar));
		} else {
			System.out.println("Nenhum documento para encaminhar foi encontrado.");
		}
	}

	private static void listarSupervisores() {
		List<Pessoa> lista = listaColaboradores(PerfilEnum.SUPERVISOR);
		apresentarColaboradores(lista);
	}

	private static void encaimnharParaSupervisor(Documento documento) {
		Scanner encDoc = new Scanner(System.in);
		System.out.println("Escolha um supervisor para encaminhar?");
		Integer opcaoSuperv = null;
		try {
			opcaoSuperv = encDoc.nextInt();
		} catch (Exception e) {
			System.out.println("Opcao Invalida!");
			encaimnharParaSupervisor(documento);
		}

		Pessoa supervisor = null;
		for (Pessoa p : listaColaboradores) {
			if (p.getPerfil().equals(PerfilEnum.SUPERVISOR) && p.getIdentificador().equals(opcaoSuperv)) {
				supervisor = p;
				break;
			}
		}
		if (supervisor == null) {
			System.out.println("Opcao Invalida!");
			encaimnharParaSupervisor(documento);
		}
		documento.setColaboradorResponsavel(supervisor);
	}

	private static List<Documento> listaDocumentosPendentesDeEncaminhamento(Pessoa colaboradorLogado) {
		List<Documento> listaFiltrada = new ArrayList<>();
		for (Documento d : listaDocumentos) {
			if (d.getColaboradorResponsavel().equals(colaboradorLogado) && d.getEstado().equals(EstadoEnum.ATIVO)) {
				listaFiltrada.add(d);
			}
		}
		return listaFiltrada;
	}

	private static void criarDocumento(Pessoa colaboradorLogado) {
		Scanner criarDoc = new Scanner(System.in);
		System.out.println("Digite o Link do documento.");
		String link = criarDoc.nextLine();

		Documento doc = new Documento(colaboradorLogado, link);
		listaDocumentos.add(doc);
		listaAcoesDoSistema.add(new Acoes(TipoAcaoEnum.CRIAR_DOCUMENTO, colaboradorLogado, doc));

	}

	private static Pessoa pesquisarColaborador(Integer col) {
		for(Pessoa p : listaColaboradores){
			if(p.getIdentificador().equals(col)){
				return p;
			}
		}

		return null;
	}

	private static void apresentarColaboradores(List<Pessoa> listaCol) {
		System.out.println("INDENTIFICADOR | NOME | PERFIL");
		for(Pessoa p : listaCol) {
			System.out.println(p.getIdentificador() + " | " + p.getNome() + " " + p.getSobrenome() + " | " + p.getPerfil());
		}
	}

	private static List<Pessoa> listaColaboradores(PerfilEnum perfil) {
		List<Pessoa> listaCol = new ArrayList<>();
		for(Pessoa p : listaColaboradores){
			if (perfil == null || p.getPerfil().equals(perfil)) {
				listaCol.add(p);
			}
		}
		return listaCol;
	}

}
