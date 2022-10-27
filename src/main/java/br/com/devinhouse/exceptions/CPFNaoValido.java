package br.com.devinhouse.exceptions;

public class CPFNaoValido extends Exception {
	
	public CPFNaoValido() {
		super("Este CPF não é válido!");
	}

}
