package com.igti.savetheplanetapi.savetheplanetapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.igti.savetheplanetapi.savetheplanetapi.model.Usuario;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "prato")
public class Prato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	private String descricao;

	@NotNull
	private BigDecimal valor;

	@NotNull
	private String quantidade;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_cozinheira")
	private Usuario cozinheira;

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

	public Usuario getCozinheira() {
		return cozinheira;
	}

	public void setCozinheira(Usuario cozinheira) {
		this.cozinheira = cozinheira;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prato other = (Prato) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
