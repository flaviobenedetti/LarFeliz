package br.com.larfeliz.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import br.com.larfeliz.domain.Pessoa;

public class PessoaDAOTest {

	@Test
	@Ignore
	public void salvar() {
		
		Pessoa pessoa = new Pessoa();
		
		pessoa.setNome("Fulano");
		pessoa.setCpf("13210072809");
		pessoa.setRg("224324767");
		pessoa.setDataNascimento(new Date());
		pessoa.setRua("Rua Fernando Augusto Santa Cruz Oliveira");
		pessoa.setNumero((short) 5);
		pessoa.setComplemento("casa 5");
		pessoa.setBairro("Vila São Francisco");
		pessoa.setCep("05386290");
		pessoa.setTelefone("37191150");
		pessoa.setCelular("996805389");
		pessoa.setEmail("maninhofab@gmail.com");
		pessoa.setCidade("São Paulo");
		pessoa.setEstado("São Paulo");
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.salvar(pessoa);		
		
		pessoa.setNome("Ciclano");
		pessoaDAO.salvar(pessoa);
		
		pessoa.setNome("Beltrano");
		pessoaDAO.salvar(pessoa);
	}
}
