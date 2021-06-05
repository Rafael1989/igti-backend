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
public class PratoService {
	
	private static final Logger logger = LoggerFactory.getLogger(PratoService.class);

	@Autowired
	private PratoRepository pratoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
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

	public Prato pronto(Long codigo, Prato prato) {
		Prato pratoSalvo = buscarPratoExistente(codigo);

//		pratoSalvo.setStatus("PRONTO");

		return pratoRepository.save(pratoSalvo);
	}

}
