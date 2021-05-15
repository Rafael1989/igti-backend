package com.igti.savetheplanetapi.savetheplanetapi.service;

import com.igti.savetheplanetapi.savetheplanetapi.model.Permissao;
import com.igti.savetheplanetapi.savetheplanetapi.model.Prato;
import com.igti.savetheplanetapi.savetheplanetapi.model.Usuario;
import com.igti.savetheplanetapi.savetheplanetapi.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UsuarioService {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario salvar(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		Permissao permissao = new Permissao();
		permissao.setCodigo(usuario.getPerfil().getCodigo());
		usuario.setPermissoes(Arrays.asList(permissao));
		return usuarioRepository.save(usuario);
	}

	private Usuario buscarUsuarioExistente(Long codigo) {
		Optional<Usuario> usuarioSalvo = usuarioRepository.findById(codigo);
		if (!usuarioSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return usuarioSalvo.get();
	}

	public Usuario atualizar(Long codigo, Usuario usuario) {
		Usuario usuarioSalvo = buscarUsuarioExistente(codigo);

		BeanUtils.copyProperties(usuario, usuarioSalvo, "codigo");

		return usuarioRepository.save(usuarioSalvo);
	}

}
