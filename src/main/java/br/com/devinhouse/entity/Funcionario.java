package br.com.devinhouse.entity;

import java.time.LocalDate;

import br.com.devinhouse.exceptions.CPFNaoValido;

public class Funcionario extends Pessoa {

	public Funcionario(String nome, String sobrenome, LocalDate dataNascimento, String cpf,
					   String enderecoCompleto) throws CPFNaoValido {
		super(nome, sobrenome, dataNascimento, cpf, enderecoCompleto);
	}

	@Override
	public PerfilEnum getPerfil() {
		return PerfilEnum.FUNCIONARIO;
	}

}
