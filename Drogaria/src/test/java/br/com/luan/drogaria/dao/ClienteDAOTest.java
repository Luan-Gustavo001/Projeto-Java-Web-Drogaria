package br.com.luan.drogaria.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.luan.drogaria.domain.Cliente;
import br.com.luan.drogaria.domain.Pessoa;

public class ClienteDAOTest {
	// -------------- SALVAR ----------------------
	@Test
	@Ignore
	public void salvar() {

		long codigo = 3L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigo);

		Cliente cliente = new Cliente();
		cliente.setDataCadastro(new Date());
		cliente.setLiberado(true);
		cliente.setPessoa(pessoa);

		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);

		System.out.println("Cliente salvo com sucesso : " + pessoa.getNome());
	}

	// -------------- LISTAR ----------------------
	@Ignore
	@Test
	public void listar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> resultado = clienteDAO.listar();

		for (Cliente cliente : resultado) {
			System.out.println("Código do Cliente : " + cliente.getCodigo());
			System.out.println("Nome do Cliente : " + cliente.getPessoa().getNome());
			System.out.println("Cliente desde  : " + cliente.getDataCadastro());

			if (cliente.getLiberado() == true) {
				System.out.println("Funcionário");
			} else {
				System.out.println("Cliente");
			}
		}

		System.out.println();
	}

	// -------------- BUSCAR ----------------------
	@Test
	@Ignore
	public void buscar() {

		long codigo = 1L;
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigo);

		if (cliente == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro encontrado : ");
			System.out.println(cliente.getCodigo() + " - " + cliente.getPessoa().getNome());

		}
	}

	// -------------- EXCLUIR ----------------------
	@Ignore
	@Test
	public void excluir() {
		long codigo = 1L;
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigo);

		if (cliente == null) {
			System.out.println("Nao foi encontrado nenhum registro do fabricante");
		} else {
			clienteDAO.excluir(cliente);
			System.out.println("Registro de cliente removido com sucesso: ");
			System.out.println(
					"O Cliente" + cliente.getCodigo() + " - " + cliente.getPessoa().getNome() + " Foi removido ");
		}

	}

	// -------------- EDITAR ----------------------
	@Test
	@Ignore
	public void editar() {
		long codigo = 1L;
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigo);

		if (cliente == null) {
			System.out.println("Nao foi encontrado nenhum registro desse fabricante");
		} else {
			System.out.println("Registro editado - antes :");
			System.out.println(cliente.getCodigo() + " - " + cliente.getPessoa().getNome());

			cliente.getPessoa().setNome("Luis");
			clienteDAO.editar(cliente);

			System.out.println("Registro editado - depois :");
			System.out.println(cliente.getCodigo() + " - " + cliente.getPessoa().getNome());
		}

	}
}
