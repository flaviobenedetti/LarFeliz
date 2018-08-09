package br.com.larfeliz.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Movimentacao extends GenericDomain {
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@OneToOne
	@JoinColumn(nullable = false)	
	private ItemMovimentacao itemMovimentacao;
	
	@Column(nullable = false)
	private Character tipo;
	
	@Column(nullable = false, precision = 15, scale = 2)
	private BigDecimal valor;
	
	@Column(nullable = true, length = 255)
	private String descricao;
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public ItemMovimentacao getItemMovimentacao() {
		return itemMovimentacao;
	}

	public void setItemMovimentacao(ItemMovimentacao itemMovimentacao) {
		this.itemMovimentacao = itemMovimentacao;
	}

	public Character getTipo() {
		return tipo;
	}
	
	@Transient
	public String getTipoForm() {
		if(tipo == 'C') {
			return "Crédito"; 
		} else {
			return "Débito";
		}		
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
