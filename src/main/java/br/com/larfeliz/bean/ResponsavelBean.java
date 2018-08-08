package br.com.larfeliz.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.larfeliz.dao.ResponsavelDAO;
import br.com.larfeliz.dao.PessoaDAO;
import br.com.larfeliz.domain.Pessoa;
import br.com.larfeliz.domain.Responsavel;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ResponsavelBean implements Serializable {

	private Pessoa pessoa;
	private Responsavel responsavel;
	
	private List<Pessoa> pessoas;
	private List<Responsavel> responsaveis;
	
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
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}
	
	public void setReponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}
	
	@PostConstruct
	public void listar() {

		novo();

		try {
			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			setReponsaveis(responsavelDAO.listar());

		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}
	}

	public void novo() {
		
		try {
			responsavel = new Responsavel();
		
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo responsável");
			erro.printStackTrace();
		}
	}

	public void salvar() {

		try {
			
			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			responsavelDAO.salvar(responsavel);
						
			responsavel = new Responsavel();
			
			responsaveis = responsavelDAO.listar();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
						
			Messages.addGlobalInfo("Responsavel salvo com sucesso");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o responsável");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {

		try {

			responsavel = (Responsavel) evento.getComponent().getAttributes().get("responsavelSelecionado");
			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			responsavelDAO.excluir(responsavel);
			responsaveis = responsavelDAO.listar();

			Messages.addGlobalInfo("Exclusao efetuada com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {

		responsavel = (Responsavel) evento.getComponent().getAttributes().get("responsavelSelecionado");
	}

	public void atualizar() {

		try {
			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			responsavelDAO.editar(responsavel);
			
			responsaveis = responsavelDAO.listar();
			
			Messages.addGlobalInfo("Alteração efetuada com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}

	}
}
