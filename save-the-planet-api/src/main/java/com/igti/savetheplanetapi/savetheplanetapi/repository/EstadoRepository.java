package com.igti.savetheplanetapi.savetheplanetapi.repository;

import com.igti.savetheplanetapi.savetheplanetapi.model.Cidade;
import com.igti.savetheplanetapi.savetheplanetapi.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
	
	List<Estado> findByPaisCodigo(Long paisCodigo);

}
