package br.com.larfeliz.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.larfeliz.dao.PacienteDAO;
import br.com.larfeliz.dao.PessoaDAO;
import br.com.larfeliz.dao.ResponsavelDAO;
import br.com.larfeliz.domain.Paciente;
import br.com.larfeliz.domain.Pessoa;
import br.com.larfeliz.domain.Responsavel;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PacienteBean implements Serializable {

	private Pessoa pessoa;
	private Responsavel responsavel;
	private Paciente paciente;
	
	private List<Pessoa> pessoas;
	private List<Responsavel> responsaveis;
	private List<Paciente> pacientes;
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Responsavel getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
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
	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}
	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}
	public List<Paciente> getPacientes() {
		return pacientes;
	}
	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	
	@PostConstruct
	public void listar() {

		novo();

		try {
			PacienteDAO pacienteDAO = new PacienteDAO();
			setPacientes(pacienteDAO.listar());

		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		
		try {
			paciente = new Paciente();	
			responsavel = new Responsavel();
		
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			
			ResponsavelDAO resposavelDAO = new ResponsavelDAO();
			responsaveis = resposavelDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo paciente");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {

		try {
			
			PacienteDAO pacienteDAO = new PacienteDAO();
			pacienteDAO.salvar(paciente);
									
			paciente = new Paciente();
			
			pacientes = pacienteDAO.listar();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			
			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			responsaveis = responsavelDAO.listar();
						
			Messages.addGlobalInfo("Paciente salvo com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o paciente");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {

		try {

			paciente = (Paciente) evento.getComponent().getAttributes().get("pacienteSelecionado");
			PacienteDAO pacienteDAO = new PacienteDAO();
			pacienteDAO.excluir(paciente);
			
			pacientes = pacienteDAO.listar();

			Messages.addGlobalInfo("Exclusao efetuada com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}

	}
	
	public void editar(ActionEvent evento) {

		paciente = (Paciente) evento.getComponent().getAttributes().get("pacienteSelecionado");
	}

	public void atualizar() {

		try {
			PacienteDAO pacienteDAO = new PacienteDAO();
			pacienteDAO.editar(paciente);
			
			pacientes = pacienteDAO.listar();
			
			Messages.addGlobalInfo("Alteração efetuada com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}

	}


}
