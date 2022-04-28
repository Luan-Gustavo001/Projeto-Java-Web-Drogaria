package br.com.luan.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.luan.drogaria.domain.Cidade;
import br.com.luan.drogaria.domain.Estado;

public class CidadeDAOTest {
	// -------------- SALVAR ----------------------
	@Test
	@Ignore
	public void salvar() {
		Long codigoEstado = 1L;
		EstadoDAO estadoDAO = new EstadoDAO();
		
		Estado estado = estadoDAO.buscar(codigoEstado);
		
		Cidade cidade = new Cidade();
		cidade.setNome("Santa cruz do Rio Pardo");
		cidade.setEstado(estado);
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
	}
	// -------------- LISTAR ----------------------
	@Test
	@Ignore
	public void listar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.listar();
		
		for (Cidade cidade : resultado) {
			System.out.println("Código da Cidade : " + cidade.getCodigo());
			System.out.println("Nome da Cidade : " + cidade.getNome());
			System.out.println("Código do Estado : " + cidade.getEstado().getCodigo());
			System.out.println("Nome do Estado : " + cidade.getEstado().getNome());
			System.out.println("Sigla do Estado : " + cidade.getEstado().getSigla());
			System.out.println();
		}
	}
	// -------------- BUSCAR ----------------------
	@Test
	@Ignore
	public void buscar() {
		
		long codigo = 1L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		
			System.out.println("Código da Cidade : " + cidade.getCodigo());
			System.out.println("Nome da Cidade : " + cidade.getNome());
			System.out.println("Código do Estado : " + cidade.getEstado().getCodigo());
			System.out.println("Nome do Estado : " + cidade.getEstado().getNome());
			System.out.println("Sigla do Estado : " + cidade.getEstado().getSigla());
			System.out.println();
		
	}
	// -------------- EXCLUIR ----------------------
	@Test
	@Ignore
	public void excluir() {
		long codigo = 7L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		
		cidadeDAO.excluir(cidade);
		System.out.println("Cidade removida : "); 
		System.out.println("Código da Cidade : " + cidade.getCodigo());
		System.out.println("Nome da Cidade : " + cidade.getNome());
		System.out.println("Código do Estado : " + cidade.getEstado().getCodigo());
		System.out.println("Nome do Estado : " + cidade.getEstado().getNome());
		System.out.println("Sigla do Estado : " + cidade.getEstado().getSigla());
		System.out.println();
		
	}
	// -------------- EDITAR ----------------------
	@Test
	@Ignore
	public void editar() {
		Long codigoEstado = 6L;
		Long codigoCidade = 6L;
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigoEstado);
		
		System.out.println("Código do Estado : " + estado.getCodigo());
		System.out.println("Nome do Estado : " + estado.getNome());
		System.out.println("Sigla do Estado : " + estado.getSigla());
		System.out.println();
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		
		System.out.println("Cidade a ser Editada : "); 
		System.out.println("Código da Cidade : " + cidade.getCodigo());
		System.out.println("Nome da Cidade : " + cidade.getNome());
		System.out.println("Código do Estado : " + cidade.getEstado().getCodigo());
		System.out.println("Nome do Estado : " + cidade.getEstado().getNome());
		System.out.println("Sigla do Estado : " + cidade.getEstado().getSigla());
		System.out.println();
		
		cidade.setNome("Havai");
		cidade.setEstado(estado);
		
		cidadeDAO.editar(cidade);
		
		System.out.println("Cidade editada com sucesso : "); 
		System.out.println("Código da Cidade : " + cidade.getCodigo());
		System.out.println("Nome da Cidade : " + cidade.getNome());
		System.out.println("Código do Estado : " + cidade.getEstado().getCodigo());
		System.out.println("Nome do Estado : " + cidade.getEstado().getNome());
		System.out.println("Sigla do Estado : " + cidade.getEstado().getSigla());
		System.out.println();
		
	}
}