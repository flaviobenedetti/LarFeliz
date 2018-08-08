package br.com.larfeliz.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Prontuario extends GenericDomain{
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date horaCadastro;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Paciente paciente;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Colaborador colaborador;
	
	@Column(length = 1000, nullable = true)
	private String apresenta;
	
	@Column(length = 1000, nullable = true)
	private String mantem;
	
	@Column(length = 1000, nullable = true)
	private String refere;

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getHoraCadastro() {
		return horaCadastro;
	}

	public void setHoraCadastro(Date horaCadastro) {
		this.horaCadastro = horaCadastro;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public String getApresenta() {
		return apresenta;
	}

	public void setApresenta(String apresenta) {
		this.apresenta = apresenta;
	}

	public String getMantem() {
		return mantem;
	}

	public void setMantem(String mantem) {
		this.mantem = mantem;
	}

	public String getRefere() {
		return refere;
	}

	public void setRefere(String refere) {
		this.refere = refere;
	}
	
}

