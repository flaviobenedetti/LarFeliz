package br.com.larfeliz.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class CustoPaciente extends GenericDomain {

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataLancamento;

	@Column(nullable = false, length = 100)
	private String descricaoCusto;

	@Column(nullable = false, precision = 15, scale = 2)
	private BigDecimal valor;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Paciente dependente;

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getDescricaoCusto() {
		return descricaoCusto;
	}

	public void setDescricaoCusto(String descricaoCusto) {
		this.descricaoCusto = descricaoCusto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Paciente getDependente() {
		return dependente;
	}

	public void setDependente(Paciente dependente) {
		this.dependente = dependente;
	}

}
