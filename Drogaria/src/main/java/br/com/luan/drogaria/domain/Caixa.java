package br.com.luan.drogaria.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Caixa  extends GenericDomain {
	@Column(nullable = false)
	@Temporal(TemporalType.DATE )
	private Date dataAbertura;
	
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dafaFechamento;
	
	@Column(nullable = false, precision = 7, scale = 2) 
	private BigDecimal valorAbertura;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDafaFechamento() {
		return dafaFechamento;
	}

	public void setDafaFechamento(Date dafaFechamento) {
		this.dafaFechamento = dafaFechamento;
	}

	public BigDecimal getValorAbertura() {
		return valorAbertura;
	}

	public void setValorAbertura(BigDecimal valorAbertura) {
		this.valorAbertura = valorAbertura;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	
		
	}