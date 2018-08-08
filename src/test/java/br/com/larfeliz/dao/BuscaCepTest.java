package br.com.larfeliz.dao;

import org.junit.Ignore;
import org.junit.Test;

import br.com.larfeliz.domain.Cep;
import br.com.larfeliz.util.BuscaCEP;

public class BuscaCepTest {

	@Test
	@Ignore
	public void buscaEndereco(){
		
		BuscaCEP dadosCep = new BuscaCEP();
		Cep cep = dadosCep.ConsultaCEP("05386290");
		
		System.out.println(cep.getBairro());
		
	}
}
