package com.igti.savetheplanetapi.savetheplanetapi.service;

import com.igti.savetheplanetapi.savetheplanetapi.model.Pedido;
import com.igti.savetheplanetapi.savetheplanetapi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido atualizar(Long codigo, Pedido pedido) {
		Pedido pedidoSalvo = buscarPedidoExistente(codigo);

		pedidoSalvo.setStatus(pedido.getStatus());

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
