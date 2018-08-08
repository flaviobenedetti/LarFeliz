package br.com.larfeliz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;


@SuppressWarnings("serial")
@Entity
public class ItemMovimentacao extends GenericDomain {

	@Column(length = 50, nullable = false) 
	private String descricaoItem;

	public String getDescricaoItem() {
		return descricaoItem;
	}

	public void setDescricaoItem(String descricaoItem) {
		this.descricaoItem = descricaoItem;
	}
	
}
