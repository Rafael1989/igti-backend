package com.igti.savetheplanetapi.savetheplanetapi.repository.projection;

import java.math.BigDecimal;

public class ResumoPrato {

	private Long codigo;
	private String descricao;
	private BigDecimal valor;
	private String quantidade;
	private String status;

	public ResumoPrato(Long codigo, String descricao, BigDecimal valor, String quantidade, String status) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
		this.quantidade = quantidade;
		this.status = status;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
