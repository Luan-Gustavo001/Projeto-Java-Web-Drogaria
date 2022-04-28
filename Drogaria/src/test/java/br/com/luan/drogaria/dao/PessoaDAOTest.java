package br.com.luan.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.luan.drogaria.domain.Pessoa;

public class PessoaDAOTest {
	@Test
	@Ignore
	public void salvar(){
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Luan");
		pessoa.setCpf("14181455570");
		pessoa.setRg("966666635");
		pessoa.setEmail("luan14@Gmail.com");
		pessoa.setCelular("14998658580");
		pessoa.setTelefone("1499996655");
		pessoa.setCep("55888777");
		pessoa.setRua("Rua dos bobos");
		pessoa.setBairro("Bairro estranha");
		pessoa.setNumero(new Short("0"));
		pessoa.setComplemento("");
		
		PessoaDAO pessoaDAO = new PessoaDAO();
	    pessoaDAO.salvar(pessoa);
		System.out.println("Pessoa salvo com sucesso");
		
	} 
//-------------- LISTAR ----------------------
	@Ignore
	@Test
	public void listar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Pessoa> resultado = pessoaDAO.listar();
		
		for (Pessoa pessoa : resultado) {
			System.out.println("Código da Pessoa : " + pessoa.getCodigo());
			System.out.println("Nome : " + pessoa.getNome());
			System.out.println("RG : " + pessoa.getRg());
			System.out.println("Email: " + pessoa.getEmail());
			System.out.println("Celular: " + pessoa.getCelular());
			System.out.println("Telefone: " + pessoa.getTelefone());
			System.out.println("CEP: " + pessoa.getCep());
			System.out.println("Rua: " + pessoa.getRua());
			System.out.println("Bairro: " + pessoa.getBairro());
			System.out.println("Numero: " + pessoa.getNumero());
			System.out.println("Complemento: " + pessoa.getComplemento());
			
			System.out.println();
		}
	}
	// -------------- BUSCAR ----------------------
		@Test
		@Ignore
		public void buscar() {
			
			long codigo = 2L;
			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoa = pessoaDAO.buscar(codigo);
			
			if(pessoa == null) {
				System.out.println("Nenhum registro encontrado");
			}else {
			System.out.println("Registro encontrado : ");

			System.out.println("Código da Pessoa : " + pessoa.getCodigo());
			System.out.println("Nome : " + pessoa.getNome());
			System.out.println("RG : " + pessoa.getRg());
			System.out.println("Email: " + pessoa.getEmail());
			System.out.println("Celular: " + pessoa.getCelular());
			System.out.println("Telefone: " + pessoa.getTelefone());
			System.out.println("CEP: " + pessoa.getCep());
			System.out.println("Rua: " + pessoa.getRua());
			System.out.println("Bairro: " + pessoa.getBairro());
			System.out.println("Numero: " + pessoa.getNumero());
			System.out.println("Complemento: " + pessoa.getComplemento());
			
			System.out.println();
			
		}
	}
		
		// -------------- EXCLUIR ----------------------
		@Ignore
		@Test
		public void excluir() {
			long codigo = 2L;
			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoa = pessoaDAO.buscar(codigo);
			
			
			if(pessoa == null) {
				System.out.println("Nao foi encontrado nenhum registro pessoal");
			}else {
				pessoaDAO.excluir(pessoa);
			System.out.println("Registro de estado removido com sucesso: ");
			System.out.println(pessoa.getCodigo() + " - " + pessoa.getNome() + " - Foi removido ");
			
			
		}
			
	}
		@Test
		@Ignore
		public void editar() {
			long codigo = 2L;
			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoa = pessoaDAO.buscar(codigo);
			
			if(pessoa == null) {
				System.out.println("Nao foi encontrado nenhum registro pesssoa");
			}else {
				System.out.println("Registro editado - antes :");
				System.out.println(pessoa.getCodigo() + " - " + pessoa.getNome());
			
				pessoa.setNome("Lucas");
				pessoaDAO.editar(pessoa);
				
				System.out.println("Registro editado - depois :");
				System.out.println(pessoa.getCodigo() + " - " + pessoa.getNome());
		}		
	}
}

