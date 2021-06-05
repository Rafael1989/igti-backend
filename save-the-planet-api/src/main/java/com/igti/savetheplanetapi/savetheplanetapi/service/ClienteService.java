package com.igti.savetheplanetapi.savetheplanetapi.service;

import com.igti.savetheplanetapi.savetheplanetapi.model.Pedido;
import com.igti.savetheplanetapi.savetheplanetapi.model.Prato;
import com.igti.savetheplanetapi.savetheplanetapi.model.Usuario;
import com.igti.savetheplanetapi.savetheplanetapi.model.Venda;
import com.igti.savetheplanetapi.savetheplanetapi.repository.PedidoRepository;
import com.igti.savetheplanetapi.savetheplanetapi.repository.PratoRepository;
import com.igti.savetheplanetapi.savetheplanetapi.repository.UsuarioRepository;
import com.igti.savetheplanetapi.savetheplanetapi.repository.VendaRepository;
import com.igti.savetheplanetapi.savetheplanetapi.repository.filter.PratoFilter;
import com.igti.savetheplanetapi.savetheplanetapi.repository.projection.ResumoPrato;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PratoRepository pratoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private VendaRepository vendaRepository;

	public Page<ResumoPrato> resumirParaCliente(PratoFilter pratoFilter, Pageable pageable, Long codigo){
		return pratoRepository.resumirParaCliente(pratoFilter, pageable, codigo);
	}

	public Page<Usuario> resumirCozinheiras(Usuario usuario, Pageable pageable){
		return usuarioRepository.resumirCozinheiras(usuario, pageable);
	}

	public Page<Pedido> resumirPedidos(Pedido pedido, Pageable pageable, Long codigo){
		return pedidoRepository.resumirPedidos(pedido, pageable, codigo);
	}

	public Page<Venda> resumirVendas(Venda venda, Pageable pageable, Long codigo){
		return vendaRepository.resumirVendas(venda, pageable, codigo);
	}
	
	public Pedido comprar(List<Prato> pratos, Long codigo) throws IllegalArgumentException{

		Pedido pedido = new Pedido();
		Usuario usuario = new Usuario();
		usuario.setCodigo(codigo);
		pedido.setCliente(usuario);
		pedido.setStatus("COMPRADO");
		for(Prato prato : pratos){
			Optional<Prato> pratoOptional = pratoRepository.findById(prato.getCodigo());
			if(Integer.parseInt(prato.getQuantidade()) > Integer.parseInt(pratoOptional.get().getQuantidade())){
				throw new IllegalArgumentException("Existem apenas " + pratoOptional.get().getQuantidade() + "pratos");
			}
			pratoOptional.get().setQuantidade(""+(Integer.parseInt(pratoOptional.get().getQuantidade()) - Integer.parseInt(prato.getQuantidade())));
		}
		pedido.setPratos(pratos);
		pedido.setEntregador(null);

		Pedido pedidoSalvo = pedidoRepository.save(pedido);
		for(Prato prato : pratos){
			Venda venda = new Venda();
			Optional<Prato> pratoOptional = pratoRepository.findById(prato.getCodigo());
			venda.setPedido(pedidoSalvo);
			venda.setPrato(prato);
			venda.setQuantidade(prato.getQuantidade());
			venda.setValor(prato.getValor().multiply(new BigDecimal(prato.getQuantidade())));
			venda.setCliente(pedidoSalvo.getCliente());
			venda.setEntregador(null);
			venda.setCozinheira(pratoOptional.get().getCozinheira());
			vendaRepository.save(venda);
		}

		return pedidoSalvo;
	}

}
