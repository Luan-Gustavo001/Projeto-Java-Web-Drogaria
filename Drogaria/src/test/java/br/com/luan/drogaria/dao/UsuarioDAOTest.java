package br.com.luan.drogaria.dao;

import org.junit.Ignore;
import org.junit.Test;

import br.com.luan.drogaria.domain.Pessoa;
import br.com.luan.drogaria.domain.Usuario;

public class UsuarioDAOTest {
	// -------------- SALVAR ----------------------
	@Ignore
	@Test
	public void salvar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa  = pessoaDAO.buscar(3L);
		
		System.out.println("Pessoa encontrada :");
		System.out.println("Nome :" + pessoa.getNome());
		System.out.println("CPF :" + pessoa.getCpf());
		
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		usuario.setSenha("q1w2e3r4");
		usuario.setTipo('A');
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		
		System.out.println("O Usuario " + usuario.getPessoa().getNome() + " Foi adicionado ao Banco");
	}

}
