package br.com.luan.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.luan.drogaria.dao.ClienteDAO;
import br.com.luan.drogaria.dao.PessoaDAO;
import br.com.luan.drogaria.domain.Cliente;
import br.com.luan.drogaria.domain.Pessoa;

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
	}
	
	
		public void excluir() {
	
		}
			public void editar() {
			}
	

	
	
}


