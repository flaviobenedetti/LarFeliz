package br.com.larfeliz.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.FlowEvent;

import br.com.larfeliz.dao.ColaboradorDAO;
import br.com.larfeliz.dao.PacienteDAO;
import br.com.larfeliz.dao.ProntuarioDAO;
import br.com.larfeliz.domain.Colaborador;
import br.com.larfeliz.domain.Paciente;
import br.com.larfeliz.domain.Pessoa;
import br.com.larfeliz.domain.Prontuario;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProntuarioBean implements Serializable {

	private Pessoa pessoa;
	private Colaborador colaborador;
	private Paciente paciente;
	private List<Pessoa> pessoas;
	private List<Colaborador> colaboradores;
	private List<Paciente> pacientes;
	private Prontuario prontuario;
	private List<Prontuario> prontuarios;
	private boolean skip;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public boolean isSkip() {
		return skip;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}
	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}
	
	public List<Prontuario> getProntuarios() {
		return prontuarios;
	}
	
	public void setProntuarios(List<Prontuario> prontuarios) {
		this.prontuarios = prontuarios;
	}
	
	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String onFlowProcess(FlowEvent event) {
		
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	@PostConstruct
	public void listar() {
		
		novo();

		try {
			ProntuarioDAO prontuarioDAO = new ProntuarioDAO();
			prontuarios = prontuarioDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}
	}

	public void novo() {

		try {			
			
			prontuario = new Prontuario();

			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			setColaboradores(colaboradorDAO.listar());

			PacienteDAO pacienteDAO = new PacienteDAO();
			setPacientes(pacienteDAO.listar());


		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo Prontuario");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {

		try {
						
			Date dataAtual = new Date();
			prontuario.setDataCadastro(dataAtual);
			
			prontuario.setHoraCadastro(dataAtual);
			
			ProntuarioDAO prontuarioDAO = new ProntuarioDAO();
			prontuarioDAO.salvar(prontuario);
			
			prontuarios = prontuarioDAO.listar();
						
			Messages.addGlobalInfo("Prontuario salvo com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o responsável");
			erro.printStackTrace();
		}

	}
	
	public void excluir(ActionEvent evento) {
		
		try {
			prontuario = (Prontuario) evento.getComponent().getAttributes().get("prontuarioSelecionado");
			
			ProntuarioDAO prontuarioDAO = new ProntuarioDAO();
			prontuarioDAO.excluir(prontuario);
			
			prontuarios = prontuarioDAO.listar();
			
			Messages.addGlobalInfo("Prontuario excluido com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir o prontuario");
			erro.printStackTrace();			
		}
		
	}
	
	public String editar(ActionEvent evento) {
		
		prontuario = (Prontuario) evento.getComponent().getAttributes().get("prontuarioSelecionado");
		return "personalEditar";
	}

	public void consultar(ActionEvent evento) {
		
		prontuario = (Prontuario) evento.getComponent().getAttributes().get("prontuarioSelecionado");
		
	}

}
