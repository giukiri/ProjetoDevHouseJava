package br.com.devinhouse.entity;

import java.time.LocalDate;

import br.com.devinhouse.exceptions.CPFNaoValido;

public class Gerente extends Pessoa {

	public Gerente(String nome, String sobrenome, LocalDate dataNascimento, String cpf, String enderecoCompleto) throws CPFNaoValido {
		super(nome, sobrenome, dataNascimento, cpf, enderecoCompleto);
	}

	@Override
	public PerfilEnum getPerfil() {
		return PerfilEnum.GERENTE;
	}

}
