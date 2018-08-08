package br.com.larfeliz.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.larfeliz.dao.PessoaDAO;
import br.com.larfeliz.domain.Cep;
import br.com.larfeliz.domain.Pessoa;
import br.com.larfeliz.util.BuscaCEP;
import br.com.larfeliz.util.FormatarCpf;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {

	private Pessoa pessoa;
	private List<Pessoa> pessoas;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void novo() {
		pessoa = new Pessoa();
	}

	public void salvar() {

		try {
			pessoa.setCpf(pessoa.getCpf().replaceAll("[.-]", ""));			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.salvar(pessoa);
			novo();
			pessoas = pessoaDAO.listar();
			String cpfFormatado;
			
			for (int i = 0; i < pessoas.size(); i++) {
				FormatarCpf formatarCpf = new FormatarCpf();			
				cpfFormatado = formatarCpf.Formatar(pessoas.get(i).getCpf());
				pessoas.get(i).setCpf(cpfFormatado);
			}
			Messages.addGlobalInfo("Cadastro salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a pessoa");
			erro.printStackTrace();
		}

	}

	@PostConstruct
	public void listar() {

		novo();

		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			setPessoas(pessoaDAO.listar());

		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}
	}

	public List<Pessoa> getPessoas() {

		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		
/*
 		String cpfFormatado;
		
		for (int i = 0; i < pessoas.size(); i++) {			
			
			SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
			String dataFormat = formatado.format(pessoas.get(i).getDataNascimento());
		}
*/
		this.pessoas = pessoas;
	}

	public void buscaEndereco() {

		BuscaCEP dadosCep = new BuscaCEP();
		String nrCep = pessoa.getCep().replace(".", "").replace("-", "").replace("_", "");

		if (nrCep != null && !nrCep.equals("")) {
			Cep cep = dadosCep.ConsultaCEP(nrCep);
			pessoa.setRua(cep.getLogradouro());
			pessoa.setComplemento(cep.getComplemento());
			pessoa.setBairro(cep.getBairro());
			pessoa.setCidade(cep.getLocalidade());
			pessoa.setEstado(cep.getUf());
		}

	}

	public void excluir(ActionEvent evento) {

		try {

			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionado");
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);
			pessoas = pessoaDAO.listar();
			String cpfFormatado;
			
			for (int i = 0; i < pessoas.size(); i++) {
				FormatarCpf formatarCpf = new FormatarCpf();			
				cpfFormatado = formatarCpf.Formatar(pessoas.get(i).getCpf());
				pessoas.get(i).setCpf(cpfFormatado);
			}

			Messages.addGlobalInfo("Exclusao efetuada com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {

		pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionado");
	}

	public void atualizar() {

		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoa.setCpf(pessoa.getCpf().replaceAll("[.-]", ""));	
			pessoaDAO.editar(pessoa);
			pessoas = pessoaDAO.listar();
			String cpfFormatado;
			
			for (int i = 0; i < pessoas.size(); i++) {
				FormatarCpf formatarCpf = new FormatarCpf();			
				cpfFormatado = formatarCpf.Formatar(pessoas.get(i).getCpf());
				pessoas.get(i).setCpf(cpfFormatado);
			}

			Messages.addGlobalInfo("Alteração efetuada com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Sistema Indisponivel");
			erro.printStackTrace();
		}

	}

}
