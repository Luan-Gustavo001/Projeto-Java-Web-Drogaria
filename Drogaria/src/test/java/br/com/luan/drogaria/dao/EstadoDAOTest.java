package br.com.luan.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.luan.drogaria.domain.Estado;

public class EstadoDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Rio de Janeiro");
		estado.setSigla("RJ");
		
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}
	@Test
	@Ignore
	 public void listar() {
		 EstadoDAO estadoDAO = new EstadoDAO();
		 List<Estado> resultado = estadoDAO.listar();
		 
		 System.out.println("Total de Registros Encontrados: " + resultado.size());
		 
		 for (Estado estado : resultado) {
			 System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		 }
	 }
	@Test
	@Ignore
	public void buscar() {
		Long codigo = 5L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if(estado == null) {
			System.out.println("Nenhum Estado encontrado");
		}else {
		System.out.println("Registro encontrado : ");
		System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}
	@Test
	public void excluir() {
		Long codigo = 1L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		
		if(estado == null) {
			System.out.println("Nao foi encontrado nenhum registro desse Estado");
		}else {
			estadoDAO.excluir(estado);
		System.out.println("Registro de estado removido com sucesso: ");
		System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
		
	}
}
