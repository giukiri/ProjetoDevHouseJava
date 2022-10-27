package br.com.devinhouse.util;

public class GeradorIdentificador {
	
	public static Integer identificadorBase = 0;
	
	public static Integer getProximoIdentificador() {
		return ++identificadorBase;
	}

}
