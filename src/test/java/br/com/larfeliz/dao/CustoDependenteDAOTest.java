package br.com.larfeliz.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import br.com.larfeliz.domain.CustoPaciente;
import br.com.larfeliz.domain.Paciente;

public class CustoDependenteDAOTest {

	@Test
	@Ignore
	public void salvar() throws Exception {
		
		BigDecimal valor = new BigDecimal("300.00");
		Long codigoDependente = 1L;
		
		CustoPaciente cd = new CustoPaciente();
		
		cd.setDataLancamento(new Date());
		cd.setDescricaoCusto("Frauda");
		cd.setValor(valor);
				
		PacienteDAO dependenteDAO = new PacienteDAO();
		Paciente dp = dependenteDAO.buscar(codigoDependente);
		
		if(dp == null){
			System.out.println("Nenhum registro dependente encontrado");
		}else{
			System.out.println("Registro dependente encontrado:");
			System.out.println(dp.getCodigo() + " - " + dp.getPessoa().getNome());
			
			cd.setDependente(dp);
			
			CustoPacienteDAO cdDAO = new CustoPacienteDAO();
			cdDAO.salvar(cd);
		}
		
		
		
	}
	
}
