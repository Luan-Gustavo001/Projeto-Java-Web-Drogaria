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

import br.com.luan.drogaria.dao.FuncionarioDAO;
import br.com.luan.drogaria.dao.PessoaDAO;
import br.com.luan.drogaria.domain.Funcionario;
import br.com.luan.drogaria.domain.Pessoa;
import br.com.luan.drogaria.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable{
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	@PostConstruct
	public void listar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao listar os funcionários");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			funcionario = new Funcionario();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
		
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo funcionário");
			erro.printStackTrace();
		}

	}
	public void salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.merge(funcionario);

			funcionario = new Funcionario();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			funcionarios = funcionarioDAO.listar();

			Messages.addGlobalInfo("Funcionário salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo funcionário");
			erro.printStackTrace();
		}
	}
	public void excluir(ActionEvent evento) {
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionario);

			funcionarios = funcionarioDAO.listar();

			Messages.addGlobalInfo("Funcionário excluido com sucesso");
			Messages.addGlobalInfo("Funcionário: " + funcionario.getPessoa().getNome());
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tenta excluir um funcionário");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao selecionar um funcionário");
			erro.printStackTrace();
		}

	}
	public void imprimir() {
		try {
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
			Map<String, Object> filtros = tabela.getFilters();
			String PNome = (String) filtros.get("pessoa.nome");

			String caminho = Faces.getRealPath("/reports/funcionarios.jasper");

			Map<String, Object> parametros = new HashMap<>();
			if (PNome == null) {
				parametros.put("FUNCIONARIO_NOME", "%%");
			} else {
				parametros.put("FUNCIONARIO_NOME", "%" + PNome + "%");
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
