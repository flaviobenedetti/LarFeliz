package br.com.larfeliz.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.larfeliz.domain.Usuario;
import br.com.larfeliz.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {

	/*
	 * o metodo autenticar eh uma exclusividade da classe usuario 
	 * e por esse motivo voi construida aqui.
	 * 
	 * Neste ponto tambem eh transformada a senha utilizando o 
	 * pacote shiro.
	 */
	
	public Usuario autenticar(String cpf, String senha) {
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {		
			
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("pessoa", "p");
			
			consulta.add(Restrictions.eq("p.cpf", cpf));
			
			SimpleHash hash = new SimpleHash("md5", senha);
			
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			
			Usuario resultado = (Usuario) consulta.uniqueResult();			
			
			return resultado;
			
		} catch (RuntimeException erro) {
			throw erro;			
		} finally {
			sessao.close();
		}
		
		
		
		
	}
}
