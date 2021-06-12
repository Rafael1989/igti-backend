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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

	@Autowired
	private PratoRepository pratoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PratoService pratoService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private PedidoService pedidoService;

	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

	public Page<ResumoPrato> resumirParaAdmin(PratoFilter pratoFilter, Pageable pageable) {
		return pratoRepository.resumirParaAdmin(pratoFilter, pageable);
	}

	public ResponseEntity<Prato> buscarPeloCodigoPratos(@PathVariable Long codigo) {
		Optional<Prato> prato = pratoRepository.findById(codigo);
		return prato.isPresent() ? ResponseEntity.ok(prato.get()) : ResponseEntity.notFound().build();
	}

	public void removerPrato(@PathVariable Long codigo) {
		pratoRepository.deleteById(codigo);
	}

	public ResponseEntity<Prato> atualizarPrato(@PathVariable Long codigo, @Valid @RequestBody Prato prato) {
		try {
			Prato pratoSalvo = pratoService.atualizarAdmin(codigo, prato);
			return ResponseEntity.ok(pratoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	public Page<Usuario> filtrar(Usuario usuario, Pageable pageable) {
		return usuarioRepository.filtrar(usuario, pageable);
	}

	public Page<Usuario> resumir(Usuario usuario, Pageable pageable) {
		return usuarioRepository.resumir(usuario, pageable);
	}

	public ResponseEntity<Usuario> buscarPeloCodigoUsuario(@PathVariable Long codigo) {
		Optional<Usuario> usuario = usuarioRepository.findById(codigo);
		return usuario.isPresent() ? ResponseEntity.ok(usuario.get()) : ResponseEntity.notFound().build();
	}

	public void removerUsuario(@PathVariable Long codigo) {
		usuarioRepository.deleteById(codigo);
	}

	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long codigo, @Valid @RequestBody Usuario usuario) {
		try {
			Usuario usuarioSalvo = usuarioService.atualizar(codigo, usuario);
			return ResponseEntity.ok(usuarioSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long codigo, @Valid @RequestBody Pedido pedido) {
		try {
			Pedido pedidoSalvo = pedidoService.atualizar(codigo, pedido);
			return ResponseEntity.ok(pedidoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	public ResponseEntity<Pedido> buscarPeloCodigoPedido(@PathVariable Long codigo) {
		Optional<Pedido> pedido = pedidoRepository.findById(codigo);
		return pedido.isPresent() ? ResponseEntity.ok(pedido.get()) : ResponseEntity.notFound().build();
	}


	public Page<Pedido> resumirPedidos(Pedido pedido, Pageable pageable){
		return pedidoRepository.resumirPedidos(pedido, pageable);
	}

	public Page<Venda> resumirVendas(Venda venda, Pageable pageable){
		return vendaRepository.resumirVendas(venda, pageable);
	}

}
