package com.igti.savetheplanetapi.savetheplanetapi.service;

import com.igti.savetheplanetapi.savetheplanetapi.model.Pedido;
import com.igti.savetheplanetapi.savetheplanetapi.model.Usuario;
import com.igti.savetheplanetapi.savetheplanetapi.model.Venda;
import com.igti.savetheplanetapi.savetheplanetapi.repository.PedidoRepository;
import com.igti.savetheplanetapi.savetheplanetapi.repository.UsuarioRepository;
import com.igti.savetheplanetapi.savetheplanetapi.repository.VendaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EntregadorService {
	
	private static final Logger logger = LoggerFactory.getLogger(EntregadorService.class);

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private VendaRepository vendaRepository;

	public Page<Pedido> resumirPedidos(Pedido pedido, Pageable pageable, Long codigo){
		return pedidoRepository.resumirPedidosEntregador(pedido, pageable, codigo);
	}

	public Page<Venda> resumirVendas(Venda venda, Pageable pageable, Long codigo){
		return vendaRepository.resumirVendasEntregador(venda, pageable, codigo);
	}
	
	public Pedido entregar(Long codigo, Pedido pedido, Long codigoEntregador) {
		Pedido pedidoSalvo = buscarPedidoExistente(codigo);

		pedidoSalvo.setStatus("ENTREGANDO");
		Usuario usuario = new Usuario();
		usuario.setCodigo(codigoEntregador);
		pedidoSalvo.setEntregador(usuario);
		List<Venda> vendas = vendaRepository.getVendasByPedido(codigo);
		for (Venda venda : vendas){
			Usuario entregador = new Usuario();
			entregador.setCodigo(codigoEntregador);
			venda.setEntregador(entregador);
			vendaRepository.save(venda);
		}


		return pedidoRepository.save(pedidoSalvo);
	}

	public Pedido pagar(Long codigo, Pedido pedido) {
		Pedido pedidoSalvo = buscarPedidoExistente(codigo);

		pedidoSalvo.setStatus("PAGO");

		return pedidoRepository.save(pedidoSalvo);
	}

	private Pedido buscarPedidoExistente(Long codigo) {
		Optional<Pedido> pedidoSalvo = pedidoRepository.findById(codigo);
		if (!pedidoSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return pedidoSalvo.get();
	}

}
