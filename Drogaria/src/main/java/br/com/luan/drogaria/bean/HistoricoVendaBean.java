package br.com.luan.drogaria.bean;

import java.io.Serializable;
//import java.sql.Connection;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

//import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
//import org.primefaces.component.datatable.DataTable;

import br.com.luan.drogaria.dao.HistoricoVendaDAO;
import br.com.luan.drogaria.dao.VendaDAO;
import br.com.luan.drogaria.domain.Cliente;
import br.com.luan.drogaria.domain.Funcionario;
import br.com.luan.drogaria.domain.HistoricoVenda;
import br.com.luan.drogaria.domain.Pessoa;
import br.com.luan.drogaria.domain.Venda;
//import br.com.luan.drogaria.util.HibernateUtil;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperPrintManager;
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class HistoricoVendaBean implements Serializable {
	private HistoricoVenda historicoVenda;
	private List<HistoricoVenda> historicos;
	private Venda venda;
	private List<Venda> vendas;
	private Cliente cliente;
	private Funcionario funcionario;
	private Pessoa pessoa;
	public HistoricoVenda getHistoricoVenda() {
		return historicoVenda;
	}
	public void setHistoricoVenda(HistoricoVenda historicoVenda) {
		this.historicoVenda = historicoVenda;
	}
	
	public List<HistoricoVenda> getHistoricos() {
		return historicos;
	}
	public void setHistoricos(List<HistoricoVenda> historicos) {
		this.historicos = historicos;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public List<Venda> getVendas() {
		return vendas;
	}
	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
			HistoricoVendaDAO historicoVendaDAO = new HistoricoVendaDAO();
			historicos = historicoVendaDAO.listar();
			VendaDAO vendaDAO = new VendaDAO();
			vendas = vendaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao listar as vendas");
			erro.printStackTrace();
		}
	}
//	public void imprimir() {
//		try {
//			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
//			Map<String, Object> filtros = tabela.getFilters();
//			String Ncliente = (String) filtros.get("venda.cliente.pessoa.nome");
//			String Nfuncionario = (String) filtros.get("venda.funcionario.pessoa.nome");
//			
//			String caminho = Faces.getRealPath("/reports/historico.jasper");
// 
//			Map<String, Object> parametros = new HashMap<>();
//			if (Ncliente == null) {
//				parametros.put("VENDA_CLIENTE", "%%");
//			}else {
//				parametros.put("VENDA_CLIENTE", "%" +  Ncliente + "%");
//			}
//			if (Nfuncionario == null) {
//				parametros.put("VENDA_FUNCIONARIO", "%%");
//			}else {
//				parametros.put("VENDA_FUNCIONARIO", "%" + Nfuncionario + "%");
//			}
//						
//			Connection conexao = HibernateUtil.getConexao();
//
//			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
//			
//			JasperPrintManager.printReport(relatorio, true);
//		} catch (JRException erro) {
//			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
//			erro.printStackTrace();
//		}
//	}
	
 }
