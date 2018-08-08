package br.com.larfeliz.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Pessoa extends GenericDomain {

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 14, nullable = false)
	private String cpf;

	@Column(length = 12, nullable = false)
	private String rg;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Column(length = 100, nullable = true)
	private String rua;

	@Column(nullable = true)
	private Short numero;

	@Column(length = 30, nullable = true)
	private String complemento;

	@Column(length = 50, nullable = true)
	private String bairro;

	@Column(length = 10, nullable = true)
	private String cep;

	@Column(length = 13, nullable = true)
	private String telefone;

	@Column(length = 14, nullable = true)
	private String celular;

	@Column(length = 100, nullable = true)
	private String email;

	@Column(nullable = true, length = 50)
	private String cidade;
	
	@Column(nullable = true, length = 30)
	private String estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;		
	}

	@Transient
	public String getCpfFormatado() {
		
		String cpfFormatado;
		
		cpfFormatado = this.cpf.substring(0, 3) + "." + 
					   this.cpf.substring(3, 6) + "." + 
					   this.cpf.substring(6, 9) + "-" + 
					   this.cpf.substring(9, 11);
		
		
		
		return cpfFormatado;		
	}

	@Transient
	public String getCpfNum() {
		
		String cpfNum = null;
		
		cpfNum = this.cpf.replace(".", "");
		cpfNum = cpfNum.replace("-", "");	
		
		return cpfNum;		
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Short getNumero() {
		return numero;
	}

	public void setNumero(Short numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
