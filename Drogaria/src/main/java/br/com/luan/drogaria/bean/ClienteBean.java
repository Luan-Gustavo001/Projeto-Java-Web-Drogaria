package br.com.luan.drogaria.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

import br.com.luan.drogaria.dao.ClienteDAO;
import br.com.luan.drogaria.dao.PessoaDAO;
import br.com.luan.drogaria.domain.Cliente;
import br.com.luan.drogaria.domain.Pessoa;
import br.com.luan.drogaria.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {
	private Cliente cliente;

	private List<Cliente> clientes;
	private List<Pessoa> pessoas;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@PostConstruct
	public void listar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar("dataCadastro");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao listar os clientes");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			cliente = new Cliente();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("erro ao listar o cliente");
			erro.printStackTrace();
		}

	}

	public void salvar() {
		try {

			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.merge(cliente);

			cliente = new Cliente();

			clientes = clienteDAO.listar("dataCadastro");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			Messages.addFlashGlobalInfo("Cliente salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("ocorreu um erro ao salvar um cliente");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {
		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.excluir(cliente);

			clientes = clienteDAO.listar();

			Messages.addGlobalInfo("Cliente excluido com sucesso");
			Messages.addGlobalInfo("Cliente: " + cliente.getPessoa().getNome());
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tenta excluir um cliente");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao selecionar um cliente");
			erro.printStackTrace();
		}

	}

	public void imprimir() {
		try {
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
			Map<String, Object> filtros = tabela.getFilters();
			String pNome = (String) filtros.get("pessoa.nome");
			String pCpf = (String) filtros.get("pessoa.cpf");
			
			String caminho = Faces.getRealPath("/reports/Clientes.jasper");

			Map<String, Object> parametros = new HashMap<>();
			if (pNome == null) {
				parametros.put("PESSOA_NOME", "%%");
			}else {
				parametros.put("PESSOA_NOME", "%" +  pNome + "%");
			}
			if (pCpf == null) {
				parametros.put("PESSOA_CPF", "%%");
			}else {
				parametros.put("PESSOA_CPF", "%" + pCpf + "%");
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
