package br.com.larfeliz.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import br.com.larfeliz.domain.Paciente;
import br.com.larfeliz.domain.Pessoa;
import br.com.larfeliz.domain.Responsavel;

public class DependenteDAOTest {

	@Test
	@Ignore
	public void salvar() throws Exception {
		
		Long codigoResponsavel = 1L;
		Long codigoDependente = 3L;
				
		Paciente depentente = new Paciente();
		
		depentente.setDataCadastro(new Date());
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa p1 = pessoaDAO.buscar(codigoDependente);
		
		if(p1 == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			System.out.println("Registro dependente encontrado:");
			System.out.println(p1.getCodigo() + " - " + p1.getNome() + " - " + p1.getCpf());
			
			depentente.setPessoa(p1);
			
			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			Responsavel r1 = responsavelDAO.buscar(codigoResponsavel);
			
			if(r1 == null) {
				System.out.println("Registro responsavel nao encontrado:");
				System.out.println(p1.getCodigo() + " - " + p1.getNome() + " - " + p1.getCpf());
			} else {
				
				System.out.println("Registro responsavel encontrado:");
				System.out.println(r1.getCodigo() + " - " + r1.getPessoa().getNome());
				
				depentente.setResponsavel(r1);
				
				PacienteDAO dependenteDAO = new PacienteDAO();
				dependenteDAO.salvar(depentente);
			}
			
			
			
		}
		
		
		
		
		
	}
}
