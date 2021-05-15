package com.igti.savetheplanetapi.savetheplanetapi.repository.usuario;

import com.igti.savetheplanetapi.savetheplanetapi.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioRepositoryQuery {

	public Page<Usuario> filtrar(Usuario usuario, Pageable pageable);
	public Page<Usuario> resumir(Usuario usuario, Pageable pageable);

}
