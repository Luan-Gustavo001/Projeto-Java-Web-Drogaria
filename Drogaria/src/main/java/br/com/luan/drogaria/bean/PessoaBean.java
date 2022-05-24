package br.com.luan.drogaria.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

import com.google.gson.Gson;

import br.com.luan.drogaria.dao.CidadeDAO;
import br.com.luan.drogaria.dao.EstadoDAO;
import br.com.luan.drogaria.dao.PessoaDAO;
import br.com.luan.drogaria.domain.Cidade;
import br.com.luan.drogaria.domain.Estado;
import br.com.luan.drogaria.domain.Pessoa;
import br.com.luan.drogaria.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {
	private Pessoa pessoa;
	private List<Pessoa> pessoas;

	private Estado estado;
	private List<Estado> estados;
	private List<Cidade> cidades;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@PostConstruct
	public void listar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao listar as pessoas");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {

			pessoa = new Pessoa();

			estado = new Estado();

			Client cliente = ClientBuilder.newClient();
			WebTarget caminho = cliente.target("http://192.168.0.114:8080/Drogaria/rest/estado");
			String json = caminho.request().get(String.class);

			Gson gson = new Gson();
			Estado[] vetor = gson.fromJson(json, Estado[].class);

			estados = Arrays.asList(vetor);

			cidades = new ArrayList<Cidade>();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova pessoa");
			erro.printStackTrace();
		}

	}

	public void salvar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.merge(pessoa);

			pessoas = pessoaDAO.listar("nome");
			novo();
			Messages.addFlashGlobalInfo("Pessoa cadastrada com sucesso");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao salvar salvar uma pessoa");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);

			pessoas = pessoaDAO.listar();

			Messages.addGlobalInfo("Pessoa excluida com sucesso");
			Messages.addGlobalInfo("Pessoa: " + pessoa.getNome());
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tenta excluir uma pessoa");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();

			estado = pessoa.getCidade().getEstado();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao selecionar uma pessoa");
			erro.printStackTrace();
		}

	}

	public void popular() {
		try {
			if (estado != null) {
				Client cliente = ClientBuilder.newClient();
				WebTarget caminho = cliente.target("http://192.168.0.114:8080/Drogaria/rest/cidade/{estadoCodigo}").resolveTemplate("estadoCodigo", estado.getCodigo());
				String json = caminho.request().get(String.class);

				Gson gson = new Gson();
				Cidade[] vetor = gson.fromJson(json, Cidade[].class);

				cidades = Arrays.asList(vetor);
				
			} else {
				cidades = new ArrayList<>();
			}
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao filtrar as cidades");
			erro.printStackTrace();
		}
	}

	public void imprimir() {
		try {
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
			Map<String, Object> filtros = tabela.getFilters();
			String Nome = (String) filtros.get("nome");
			String Cpf = (String) filtros.get("cpf");

			String caminho = Faces.getRealPath("/reports/pessoas.jasper");

			Map<String, Object> parametros = new HashMap<>();
			if (Nome == null) {
				parametros.put("NOME", "%%");
			} else {
				parametros.put("NOME", "%" + Nome + "%");
			}
			if (Cpf == null) {
				parametros.put("CPF", "%%");
			} else {
				parametros.put("CPF", "%" + Cpf + "%");
			}

			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);

			JasperPrintManager.printReport(relatorio, true);
		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}
	}
}
