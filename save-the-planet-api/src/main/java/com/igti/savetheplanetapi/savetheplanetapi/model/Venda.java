package com.igti.savetheplanetapi.savetheplanetapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "venda")
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@OneToOne
	@JoinColumn(name = "codigo_pedido")
	private Pedido pedido;

	@OneToOne
	@JoinColumn(name = "codigo_prato")
	private Prato prato;

	@NotNull
	private String quantidade;

	@NotNull
	private BigDecimal valor;

	@ManyToOne
	@JoinColumn(name = "codigo_cliente")
	private Usuario cliente;

	@ManyToOne
	@JoinColumn(name = "codigo_entregador")
	private Usuario entregador;

	@ManyToOne
	@JoinColumn(name = "codigo_cozinheira")
	private Usuario cozinheira;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Prato getPrato() {
		return prato;
	}

	public void setPrato(Prato prato) {
		this.prato = prato;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public Usuario getEntregador() {
		return entregador;
	}

	public void setEntregador(Usuario entregador) {
		this.entregador = entregador;
	}

	public Usuario getCozinheira() {
		return cozinheira;
	}

	public void setCozinheira(Usuario cozinheira) {
		this.cozinheira = cozinheira;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Venda venda = (Venda) o;
		return codigo.equals(venda.codigo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}
}
