package br.com.luan.drogaria.dao;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
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
		Pessoa pessoa = pessoaDAO.buscar(3L);

		System.out.println("Pessoa encontrada :");
		System.out.println("Nome :" + pessoa.getNome());
		System.out.println("CPF :" + pessoa.getCpf());

		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		usuario.setSenhaSemCriptografia("q1w2e3r4");
		
		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
		usuario.setSenha(hash.toHex());
		usuario.setTipo('A');

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);

		System.out.println("O Usuario " + usuario.getPessoa().getNome() + " Foi adicionado ao Banco");
	}

	// -------------- LISTAR ----------------------
	@Ignore
	@Test
	public void listar() {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> resultado = usuarioDAO.listar();

		for (Usuario usuario : resultado) {
			System.out.println("Código do Fabricante : " + usuario.getCodigo());
			System.out.println("Nome do Usuario : " + usuario.getPessoa().getNome());
			System.out.println("Senha : " + usuario.getSenha());
			System.out.println("Tipo : " + usuario.getTipo());

			if (usuario.getAtivo() == true) {
				System.out.println("Ativo : ON");
			} else {
				System.out.println("Ativo : OFF");
			}

			System.out.println();
		}
	}

	// -------------- BUSCAR ----------------------
	@Test
	@Ignore
	public void buscar() {

		long codigo = 1L;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigo);

		if (usuario == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro encontrado : ");
			System.out.println(usuario.getCodigo() + " - " + usuario.getPessoa().getNome());

		}
	}

	// -------------- EXCLUIR ----------------------
	@Ignore
	@Test
	public void excluir() {
		long codigo = 1L;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigo);

		if (usuario == null) {
			System.out.println("Nao foi encontrado nenhum registro do fabricante");
		} else {
			usuarioDAO.excluir(usuario);
			System.out.println("Registro de cliente removido com sucesso: ");
			System.out.println(
					"O Cliente" + usuario.getCodigo() + " - " + usuario.getPessoa().getNome() + " Foi removido ");
		}

	}

	// -------------- EDITAR ----------------------
	@Test
	@Ignore
	public void editar() {
		long codigo = 1L;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigo);

		if (usuario == null) {
			System.out.println("Nao foi encontrado nenhum registro desse fabricante");
		} else {
			System.out.println("Registro editado - antes :");
			System.out.println(usuario.getCodigo() + " - " + usuario.getPessoa().getNome());

			usuario.getPessoa().setNome("Luis");
			usuarioDAO.editar(usuario);

			System.out.println("Registro editado - depois :");
			System.out.println(usuario.getCodigo() + " - " + usuario.getPessoa().getNome());
		}

	}
}
