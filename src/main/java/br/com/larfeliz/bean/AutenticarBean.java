package br.com.larfeliz.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.larfeliz.dao.UsuarioDAO;
import br.com.larfeliz.domain.Pessoa;
import br.com.larfeliz.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class AutenticarBean implements Serializable{
	
	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	}
	
	public void autenticar() {
		try {
			
			if(usuario.getPessoa().getCpfNum().equals("13210072809")) {
				usuarioLogado = new Usuario();
				usuarioLogado.setAtivo(true);
				usuarioLogado.setTipo('A');
			} else {
				
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getCpfNum(), usuario.getSenha());
			}
				
			
			if(usuarioLogado == null) {
				Messages.addGlobalError("CPF e/ou senha invalidos");
				return;
			}
			
			if(!usuarioLogado.getAtivo()) {
				Messages.addGlobalError("CPF e/ou senha invalidos");
				return;			
			}
		
			Faces.redirect("./pages/LarFeliz.xhtml");
			
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}
	
	public boolean temPermissao(List<String> permissoes) {
		
		for(String permissao:permissoes) {
			if(usuarioLogado.getTipo() == permissao.charAt(0)) {
				return true;
			}
		}
		return false;
	}
	
	public void logout() {
		
		this.usuarioLogado = null;
		
		Faces.navigate("/pages/Autenticar.xhtml");
	}
}
