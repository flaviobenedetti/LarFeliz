package br.com.larfeliz.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import br.com.larfeliz.domain.Cep;

public class BuscaCEP {

	private Client client;
	private WebResource webResourse;
	
	public Cep ConsultaCEP(String codCep) {	
		client = Client.create();
		webResourse = client.resource("https://viacep.com.br/ws/");
		return webResourse.path(codCep + "/json/").get(new GenericType<Cep>() {});				
	}
	
}
