package br.com.larfeliz.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import br.com.larfeliz.domain.Pessoa;
import br.com.larfeliz.domain.Responsavel;

public class ResponsavelDAOTest {

	@Test
	@Ignore
	public void salvar() throws Exception {
		
		Long codigo = 2L;
		
		Responsavel responsavel = new Responsavel();
		
		responsavel.setDataCadastro(new Date());
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa p1 = pessoaDAO.buscar(codigo);
		
		if(p1 == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			System.out.println("Registro encontrado:");
			System.out.println(p1.getCodigo() + " - " + p1.getNome() + " - " + p1.getCpf());
			
			responsavel.setPessoa(p1);
			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			responsavelDAO.salvar(responsavel);
		}
		
		
		
	}
}
