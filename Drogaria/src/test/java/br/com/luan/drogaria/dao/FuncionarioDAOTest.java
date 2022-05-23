package br.com.luan.drogaria.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import br.com.luan.drogaria.domain.Funcionario;
import br.com.luan.drogaria.domain.Pessoa;

public class FuncionarioDAOTest{ 
	@Test
	@Ignore
	public void salvar(){
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(5L);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setCarteiratrabalho("654321");
		funcionario.setDataCadastro(new Date());
		funcionario.setPessoa(pessoa);
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.salvar(funcionario);
		
		System.out.println("Funcionario salvo com sucesso");
		
}

}
