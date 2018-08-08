package br.com.larfeliz.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.larfeliz.dao.PessoaDAO;
import br.com.larfeliz.dao.UsuarioDAO;
import br.com.larfeliz.dao.UsusarioDAOTest;
import br.com.larfeliz.domain.Pessoa;
import br.com.larfeliz.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private Pessoa pessoa;
	private Usuario usuario;
	private List<Pessoa> pessoas;
	private List<Usuario> usuarios;
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@PostConstruct
	public void Listar() {
		
		novo();		
		try {			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();			
		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
	
		try {			
			usuario = new Usuario();
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo Usuario");
			erro.printStackTrace();
		}			
				
	}
	
	public void salvar() {
		
		try {
	
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			SimpleHash hash = new SimpleHash("md5", usuario.getSenha() );
			usuario.setSenha(hash.toHex());
			usuarioDAO.salvar(usuario);
			
			novo();
			
			usuarios = usuarioDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Usuario");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {

		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
	}

	public void atualizar() {

		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			SimpleHash hash = new SimpleHash("md5", usuario.getSenha() );
			usuario.setSenha(hash.toHex());
			usuarioDAO.editar(usuario);
			
			usuarios = usuarioDAO.listar();
			
			Messages.addGlobalInfo("Alteração efetuada com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		
		try {
			
			AutenticarBean autenticarBean = Faces.getSessionAttribute("autenticarBean");
			Usuario usuarioLogado = autenticarBean.getUsuarioLogado();
			
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			
			if(usuario.getPessoa().getCpf().equals(usuarioLogado.getPessoa().getCpf())){
				Messages.addGlobalInfo("Usuario nao possui permissão de exclusão!");				
			} else {
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				usuarioDAO.excluir(usuario);			
				usuarios = usuarioDAO.listar();			
				Messages.addGlobalInfo("Exclusão efetuada com sucesso!");
			}
			
			
		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}
	}
	
}
