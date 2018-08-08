package br.com.larfeliz.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import br.com.larfeliz.domain.Colaborador;
import br.com.larfeliz.domain.Pessoa;

public class ColaboradorDAOTest {

	@Test
	@Ignore
	public void salvar() throws Exception {
		
		Long codigo = 1L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa p1 = pessoaDAO.buscar(codigo);
		
		if(p1 == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			System.out.println("Registro encontrado:");
			System.out.println(p1.getCodigo() + " - " + p1.getNome() + " - " + p1.getCpf());
			BigDecimal salario = new BigDecimal("9000.00");
			Colaborador colaborador = new Colaborador();
			colaborador.setCarteiraTrabalho("1234567890");
			colaborador.setDataAdmissao(new Date());
			colaborador.setDataDemissao(null);
			colaborador.setSalarioBase(salario);
			colaborador.setPessoa(p1);
			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			colaboradorDAO.salvar(colaborador);
		}
		
		
		
	}
}
