package com.igti.savetheplanetapi.savetheplanetapi.service;

import com.igti.savetheplanetapi.savetheplanetapi.model.Pedido;
import com.igti.savetheplanetapi.savetheplanetapi.model.Prato;
import com.igti.savetheplanetapi.savetheplanetapi.model.Venda;
import com.igti.savetheplanetapi.savetheplanetapi.repository.PedidoRepository;
import com.igti.savetheplanetapi.savetheplanetapi.repository.PratoRepository;
import com.igti.savetheplanetapi.savetheplanetapi.repository.UsuarioRepository;
import com.igti.savetheplanetapi.savetheplanetapi.repository.VendaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PratoService {
	
	private static final Logger logger = LoggerFactory.getLogger(PratoService.class);

	@Autowired
	private PratoRepository pratoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private VendaRepository vendaRepository;
	
	public Prato salvar(Prato prato) {
//		prato.setStatus("DISPONÍVEL");
		return pratoRepository.save(prato);
	}

	public Prato atualizar(Long codigo, Prato prato) {
		Prato pratoSalvo = buscarPratoExistente(codigo);

		BeanUtils.copyProperties(prato, pratoSalvo, "codigo", "cozinheira", "status");

		return pratoRepository.save(pratoSalvo);
	}

	public Prato atualizarAdmin(Long codigo, Prato prato) {
		Prato pratoSalvo = buscarPratoExistente(codigo);

		pratoSalvo.setDescricao(prato.getDescricao());
		pratoSalvo.setQuantidade(prato.getQuantidade());
		pratoSalvo.setValor(prato.getValor());

		return pratoRepository.save(pratoSalvo);
	}

	private Prato buscarPratoExistente(Long codigo) {
		Optional<Prato> pratoSalvo = pratoRepository.findById(codigo);
		if (!pratoSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return pratoSalvo.get();
	}

	private Pedido buscarPedidoExistente(Long codigo) {
		Optional<Pedido> pedidoSalvo = pedidoRepository.findById(codigo);
		if (!pedidoSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return pedidoSalvo.get();
	}

	public Pedido pronto(Long codigo, Pedido pedido) {
		Pedido pedidoSalvo = buscarPedidoExistente(codigo);

		pedidoSalvo.setStatus("PRONTO");

		return pedidoRepository.save(pedidoSalvo);
	}

	public Page<Pedido> resumirPedidos(Pedido pedido, Pageable pageable, Long codigo){
		return pedidoRepository.resumirPedidosCozinheira(pedido, pageable, codigo);
	}

	public Page<Venda> resumirVendas(Venda venda, Pageable pageable, Long codigo){
		return vendaRepository.resumirVendasCozinheira(venda, pageable, codigo);
	}

}
