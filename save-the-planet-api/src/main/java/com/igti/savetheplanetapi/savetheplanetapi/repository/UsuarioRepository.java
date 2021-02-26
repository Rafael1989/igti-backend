package com.igti.savetheplanetapi.savetheplanetapi.repository;


import com.igti.savetheplanetapi.savetheplanetapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Optional<Usuario> findByEmail(String email);
	
	public List<Usuario> findByPermissoesDescricao(String permissaoDescricao);
	
}
