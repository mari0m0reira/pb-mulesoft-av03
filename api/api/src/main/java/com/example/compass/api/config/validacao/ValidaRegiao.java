package com.example.compass.api.config.validacao;

public class ValidaRegiao{
	
	public static boolean validar(String nomeRegiao) {
		
		if(nomeRegiao.equals("Norte") || nomeRegiao.equals("Nordeste") || nomeRegiao.equals("Sul") || nomeRegiao.equals("Suldeste") || nomeRegiao.equals("Centro-Oeste")) {
			return true;
		}			
		return false;
	}

}