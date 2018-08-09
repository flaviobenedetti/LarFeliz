package br.com.larfeliz.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.larfeliz.dao.ItemMovimentacaoDAO;
import br.com.larfeliz.dao.MovimentacaoDAO;
import br.com.larfeliz.domain.ItemMovimentacao;
import br.com.larfeliz.domain.Movimentacao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MovimentacaoBean implements Serializable {

	private Movimentacao movimentacao;
	private List<Movimentacao> movimentacoes;

	private ItemMovimentacao itemMovimentacao;
	private List<ItemMovimentacao> itensMovimentacao;

	

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

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

	@PostConstruct
	public void listar() {

		novo();
		try {
			MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();
			movimentacoes = movimentacaoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}
	}

	public void novo() {
		movimentacao = new Movimentacao();		
		ItemMovimentacaoDAO itemMovimentacaoDAO = new ItemMovimentacaoDAO();
		itensMovimentacao = itemMovimentacaoDAO.listar();
		
		TotalCredito(new BigDecimal(1.99).setScale(2, RoundingMode.HALF_EVEN));
		
	}

	public void salvar() {

		try {
					
			MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();
			movimentacaoDAO.salvar(movimentacao);
			novo();
			movimentacoes  = movimentacaoDAO.listar();
			Messages.addGlobalInfo("Movimentação salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a Movimentação");
			erro.printStackTrace();
		}

	}

	
	public void excluir(ActionEvent evento) {

		try {

			movimentacao = (Movimentacao) evento.getComponent().getAttributes().get("movimentacaoSelecionado");
			MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();
			movimentacaoDAO.excluir(movimentacao);
			movimentacoes = movimentacaoDAO.listar();
			Messages.addGlobalInfo("Exclusao efetuada com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {

		movimentacao = (Movimentacao) evento.getComponent().getAttributes().get("movimentacaoSelecionado");
		
	}

	public void atualizar() {

		try {
			MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();
			movimentacaoDAO.editar(movimentacao);
			movimentacoes = movimentacaoDAO.listar();
			Messages.addGlobalInfo("Alteração efetuada com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}

	}

}
