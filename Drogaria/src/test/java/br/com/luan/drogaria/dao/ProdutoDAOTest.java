package br.com.luan.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.luan.drogaria.domain.Fabricante;
import br.com.luan.drogaria.domain.Produto;

public class ProdutoDAOTest {
	// -------------- SALVAR ----------------------
	@Test
	@Ignore
	public void salvar(){
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(3L);
		
		Produto produto = new Produto();
		produto.setDescricao("Paracetamol");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("13.70"));
		produto.setQuantidade(new Short ("7"));
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
		System.out.println("Produto salvo com sucesso");
	}
	// -------------- LISTAR ----------------------
	@Ignore
	@Test
	public void listar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> resultado = produtoDAO.listar();
		
		for (Produto produto : resultado) {
			System.out.println("Código da Produto : " + produto.getCodigo());
			System.out.println("Nome da Produto : " + produto.getDescricao());
			System.out.println("Nome do Fabricante : " + produto.getFabricante().getDescricao());
			System.out.println("Valor do Produto : " + produto.getPreco());
			System.out.println("Quantidade em estoque : " + produto.getQuantidade());
			System.out.println();
		}
	}
	// -------------- BUSCAR ----------------------
	@Test
	@Ignore
	public void buscar() {
		
		long codigo = 1L;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
		
		if(produto == null) {
			System.out.println("Nenhum registro encontrado");
		}else {
		System.out.println("Registro encontrado : ");
		System.out.println(produto.getCodigo() + " - " + produto.getDescricao());
		
		}
	}
	// -------------- EXCLUIR ----------------------
		@Ignore
		@Test
		public void excluir() {
			
			long codigo = 2L;
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produto = produtoDAO.buscar(codigo);
			
			
			if(produto == null) {
				System.out.println("Nao foi encontrado nenhum registro do produto");
			}else {
				produtoDAO.excluir(produto);
			System.out.println("Registro de estado removido com sucesso: ");
			System.out.println();
			System.out.println(produto.getCodigo() + " - " + produto.getDescricao() + " - Foi removido do sistema ");
			}
			
	}
	// -------------- EDITAR ----------------------
		@Test
		public void editar() {
			long codigoProduto = 1L;
			long codigoFabricante = 3L;
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			Fabricante fabricante = fabricanteDAO.buscar(codigoFabricante);
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produto = produtoDAO.buscar(codigoProduto);
			
			if(produto == null) {
				System.out.println("Nao foi encontrado nenhum registro deste produto");
			}else {
				System.out.println("Registro editado - antes :");
				System.out.println(produto.getCodigo() + " - " + produto.getDescricao());
			
				produto.setDescricao("Paracetamol 80g - caixa de 20 comprimidos");
				produto.setPreco(new BigDecimal("14.30"));
				produto.setQuantidade(new Short("10"));
				produto.setFabricante(fabricante);
				produtoDAO.editar(produto);
				
				System.out.println("Registro editado - depois :");
				System.out.println(produto.getCodigo() + " Produto : " + produto.getDescricao());
				System.out.println(produto.getCodigo() + " Preço : " + produto.getPreco());
				System.out.println(produto.getCodigo() + " Quantidade em Estoque :" + produto.getQuantidade());
				System.out.println(produto.getCodigo() + " Fabricante :" + produto.getFabricante().getDescricao());
				
			}			
	}
	
}
