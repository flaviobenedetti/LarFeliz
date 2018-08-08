package br.com.larfeliz.dao;

import org.junit.Ignore;
import org.junit.Test;

import br.com.larfeliz.domain.Pessoa;
import br.com.larfeliz.domain.Usuario;

public class UsusarioDAOTest {

	@Test
	@Ignore
	public void salvar() throws Exception {
		
		Long codigo = 1L;
		
		Usuario usuario = new Usuario();
		
		usuario.setSenha("123456789");
		usuario.setAtivo(true);
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa p1 = pessoaDAO.buscar(codigo);
		
		if(p1 == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			System.out.println("Registro encontrado:");
			System.out.println(p1.getCodigo() + " - " + p1.getNome() + " - " + p1.getCpf());
			
			usuario.setPessoa(p1);
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.salvar(usuario);
		}
		
		
		
	}
	
}
