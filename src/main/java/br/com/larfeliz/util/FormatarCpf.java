package br.com.larfeliz.util;

public class FormatarCpf {

	public String Formatar(String cpf) {
		
		
		String cpfFormatado;
		
		cpfFormatado = cpf.substring(0, 3) + "." + 
					   cpf.substring(3, 6) + "." + 
					   cpf.substring(6, 9) + "-" + 
					   cpf.substring(9, 11);
		
		return cpfFormatado;
		
		
	}
}
