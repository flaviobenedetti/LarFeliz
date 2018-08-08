package br.com.larfeliz.dao;

import org.junit.Ignore;
import org.junit.Test;

public class CargaInicial {

	@Test
	@Ignore
	public void carga() throws Exception {
		
		PessoaDAOTest c1 = new PessoaDAOTest();
		c1.salvar();
		
		ColaboradorDAOTest c2 = new ColaboradorDAOTest();
		c2.salvar();
		
		UsusarioDAOTest c3 = new UsusarioDAOTest();
		c3.salvar();
		
		ResponsavelDAOTest c4 = new ResponsavelDAOTest();
		c4.salvar();
		
		DependenteDAOTest c5 = new DependenteDAOTest();
		c5.salvar();
		
		CustoDependenteDAOTest c6 = new CustoDependenteDAOTest();
		c6.salvar();
		
		
	}
	
}
