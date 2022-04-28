package br.com.luan.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.luan.drogaria.domain.Fabricante;

public class FabricanteDAOTest {
	// -------------- SALVAR ----------------------
	@Test
	@Ignore
	public void salvar(){
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Astrazenica");
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.salvar(fabricante);
		System.out.println("Fabricante salvo com sucesso");
		
	}
	// -------------- LISTAR ----------------------
	@Ignore
	@Test
	public void listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> resultado = fabricanteDAO.listar();
		
		for (Fabricante fabricante : resultado) {
			System.out.println("Código do Fabricante : " + fabricante.getCodigo());
			System.out.println("Nome do Fabricante : " + fabricante.getDescricao());
			System.out.println();
		}
	}
	// -------------- BUSCAR ----------------------
	@Test
	@Ignore
	public void buscar() {
		
		long codigo = 1L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		
		if(fabricante == null) {
			System.out.println("Nenhum registro encontrado");
		}else {
		System.out.println("Registro encontrado : ");
		System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		
		}
	}
	// -------------- EXCLUIR ----------------------
	@Ignore
	@Test
	public void excluir() {
		Long codigo = 4L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		
		
		if(fabricante == null) {
			System.out.println("Nao foi encontrado nenhum registro do fabricante");
		}else {
			fabricanteDAO.excluir(fabricante);
		System.out.println("Registro de estado removido com sucesso: ");
		System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao() + " - Foi removido ");
		}
		
	}
	// -------------- EDITAR ----------------------
	@Test
	@Ignore
	public void editar() {
		Long codigo = 2L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		
		if(fabricante == null) {
			System.out.println("Nao foi encontrado nenhum registro desse fabricante");
		}else {
			System.out.println("Registro editado - antes :");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		
			fabricante.setDescricao("Laboratorio STA.Cruz");
			fabricanteDAO.editar(fabricante);
			
			System.out.println("Registro editado - depois :");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}
		
	}
}

