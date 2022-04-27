package br.com.luan.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Funcionario extends GenericDomain {
	@Column(length = 15, nullable = false)
	private String carteiratrabalho;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private java.util.Calendar dataCadastro;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;

	public String getCarteiratrabalho() {
		return carteiratrabalho;
	}

	public void setCarteiratrabalho(String carteiratrabalho) {
		this.carteiratrabalho = carteiratrabalho;
	}

	public java.util.Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(java.util.Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	

}
