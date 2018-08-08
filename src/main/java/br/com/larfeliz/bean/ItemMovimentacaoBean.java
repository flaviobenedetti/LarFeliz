package br.com.larfeliz.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.larfeliz.dao.ItemMovimentacaoDAO;
import br.com.larfeliz.domain.ItemMovimentacao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ItemMovimentacaoBean implements Serializable {

	private ItemMovimentacao itemMovimentacao;
	private List<ItemMovimentacao> itensMovimentacao;


	public ItemMovimentacao getItemMovimentacao() {
		return itemMovimentacao;
	}

	public void setItemMovimentacao(ItemMovimentacao itemMovimentacao) {
		this.itemMovimentacao = itemMovimentacao;
	}

	public List<ItemMovimentacao> getItensMovimentacao() {
		return itensMovimentacao;
	}

	public void setItensMovimentacao(List<ItemMovimentacao> itensMovimentacao) {
		this.itensMovimentacao = itensMovimentacao;
	}

	public void novo() {
		itemMovimentacao = new ItemMovimentacao();
	}

	public void salvar() {

		try {
					
			ItemMovimentacaoDAO itemMovimentacaoDAO = new ItemMovimentacaoDAO();
			itemMovimentacaoDAO.salvar(itemMovimentacao);
			novo();
			itensMovimentacao  = itemMovimentacaoDAO.listar();			
			Messages.addGlobalInfo("Descrição Item salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a Descrição item");
			erro.printStackTrace();
		}

	}

	@PostConstruct
	public void listar() {

		novo();

		try {
			ItemMovimentacaoDAO itemMovimentacaoDAO = new ItemMovimentacaoDAO();
			setItensMovimentacao(itemMovimentacaoDAO.listar());

		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {

		try {

			itemMovimentacao = (ItemMovimentacao) evento.getComponent().getAttributes().get("itemMovimentacaoSelecionado");
			ItemMovimentacaoDAO itemMovimentacaoDAO = new ItemMovimentacaoDAO();
			itemMovimentacaoDAO.excluir(itemMovimentacao);
			itensMovimentacao = itemMovimentacaoDAO.listar();
						
			Messages.addGlobalInfo("Exclusao efetuada com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {

		itemMovimentacao = (ItemMovimentacao) evento.getComponent().getAttributes().get("itemMovimentacaoSelecionado");
		
	}

	public void atualizar() {

		try {
			ItemMovimentacaoDAO itemMovimentacaoDAO = new ItemMovimentacaoDAO();
			itemMovimentacaoDAO.editar(itemMovimentacao);
			itensMovimentacao = itemMovimentacaoDAO.listar();
			
			Messages.addGlobalInfo("Alteração efetuada com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}

	}

}
