package br.com.larfeliz.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.larfeliz.bean.AutenticarBean;
import br.com.larfeliz.domain.Usuario;

@SuppressWarnings("serial")
public class AutenticarListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {

		
		//recupera nome da pagina atual
		String paginaAtual = Faces.getViewId();

		//verifica se pagina atual eh igual "Autenticar.xhtml"
		boolean ehPaginaAutenticacao = paginaAtual.contains("Autenticar.xhtml");
		
		//caso pagina atual nao seja a pagina de autenticacao
		if(!ehPaginaAutenticacao) {
			//recupera o Bean da pagina Atenticar.xhtml
			AutenticarBean autenticarBean = Faces.getSessionAttribute("autenticarBean");
			
			//se o bean da pagina Autenticar.xhtml nunca acessada
			if(autenticarBean == null) {
				Faces.navigate("/pages/Autenticar.xhtml");
				return;
			}
			
			//recupera usuario e verifica se ja foi validado verificando se foi teclado
			Usuario usuario = autenticarBean.getUsuarioLogado();
			if(usuario == null) {
				Faces.navigate("/pages/Autenticar.xhtml");
				return;
			}
		}
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		
		return PhaseId.ANY_PHASE;
	}

}
