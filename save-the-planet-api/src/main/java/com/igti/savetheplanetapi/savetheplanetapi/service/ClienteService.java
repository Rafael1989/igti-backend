package com.igti.savetheplanetapi.savetheplanetapi.service;

import com.igti.savetheplanetapi.savetheplanetapi.model.Prato;
import com.igti.savetheplanetapi.savetheplanetapi.repository.PratoRepository;
import com.igti.savetheplanetapi.savetheplanetapi.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

	@Autowired
	private PratoRepository pratoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Prato comprar(Long codigo, Prato prato) {
		Prato pratoSalvo = buscarPratoExistente(codigo);

		pratoSalvo.setCliente(prato.getCliente());
		pratoSalvo.setQuantidade(""+(Integer.parseInt(prato.getQuantidade()) - 1));
		pratoSalvo.setStatus("COMPRADO");

		return pratoRepository.save(pratoSalvo);
	}

	private Prato buscarPratoExistente(Long codigo) {
		Optional<Prato> pratoSalvo = pratoRepository.findById(codigo);
		if (!pratoSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return pratoSalvo.get();
	}

}