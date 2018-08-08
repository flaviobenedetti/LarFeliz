package br.com.larfeliz.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.com.larfeliz.domain.Pessoa;
import br.com.larfeliz.domain.Usuario;

public class usuarioDAOTest {

	
	@Test
	@Ignore
	public void salvar() {
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(1L);
		
		System.out.println("Pessoa Encontrada");
		System.out.println("Nome: " + pessoa.getNome());
		System.out.println("CPF: " + pessoa.getCpf());
				
		Usuario usuario = new Usuario();
		usuario.setSenha("123456");
		SimpleHash hash = new SimpleHash("md5", usuario.getSenha() );
		usuario.setSenha(hash.toHex());		
		usuario.setTipo('a');
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		
		System.out.println("usuario salvo com sucesso!");
		
		
		
		
	}
	
	@Test
	public void autenticar() {
	
		String cpf = "13210072809";
		String senha = "123456";
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(cpf, senha);
		
		System.out.println("teste = " + usuario);
		
	}
}
