package br.com.larfeliz.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.larfeliz.dao.ColaboradorDAO;
import br.com.larfeliz.dao.PessoaDAO;
import br.com.larfeliz.domain.Colaborador;
import br.com.larfeliz.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ColaboradorBean implements Serializable{
	
	private Pessoa pessoa;
	private Colaborador colaborador;
	private List<Pessoa> pessoas;
	private List<Colaborador> colaboradores;

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
	
	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}
	
	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@PostConstruct
	public void listar() {

		novo();

		try {
			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			setColaboradores(colaboradorDAO.listar());

		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}
	}

	public void novo() {
		
		try {
			colaborador = new Colaborador();
		
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo cliente");
			erro.printStackTrace();
		}
	}

	public void salvar() {

		try {
			
			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			colaboradorDAO.salvar(colaborador);
						
			colaborador = new Colaborador();
			
			colaboradores = colaboradorDAO.listar();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
						
			Messages.addGlobalInfo("Colaborador salvo com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o colaborador");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {

		try {

			colaborador = (Colaborador) evento.getComponent().getAttributes().get("colaboradorSelecionado");
			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			colaboradorDAO.excluir(colaborador);
			colaboradores = colaboradorDAO.listar();

			Messages.addGlobalInfo("Exclusao efetuada com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {

		colaborador = (Colaborador) evento.getComponent().getAttributes().get("colaboradorSelecionado");
	}

	public void atualizar() {

		try {
			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			colaboradorDAO.editar(colaborador);
			
			colaboradores = colaboradorDAO.listar();
			
			Messages.addGlobalInfo("Alteração efetuada com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}

	}


}
