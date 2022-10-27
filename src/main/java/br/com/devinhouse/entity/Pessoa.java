package br.com.devinhouse.entity;

import java.time.LocalDate;

import br.com.devinhouse.exceptions.CPFNaoValido;
import br.com.devinhouse.util.CpfValidator;
import br.com.devinhouse.util.GeradorIdentificador;

public abstract class Pessoa {

	private Integer identificador;
	private String nome;
	private String sobrenome;
	private LocalDate dataNascimento;
	private String cpf;
	private String enderecoCompleto;

	public Pessoa(String nome, String sobrenome, LocalDate dataNascimento, String cpf,
			String enderecoCompleto) throws CPFNaoValido {
		super();
		criarPessoa(nome, sobrenome, dataNascimento, cpf, enderecoCompleto);
	}
	
	public abstract PerfilEnum getPerfil();
	
	public void criarPessoa(String nome, String sobrenome, LocalDate dataNascimento, String cpf,
			String enderecoCompleto) throws CPFNaoValido {
		gerarIdetificador();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		if (CpfValidator.isCpf(cpf)) {
			this.cpf = cpf;
		} else {
			throw new CPFNaoValido();
		}
		this.enderecoCompleto = enderecoCompleto;
	}

	private void gerarIdetificador() {
		this.identificador = GeradorIdentificador.getProximoIdentificador();
		
	}

	public Integer getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}

}
