package com.igti.savetheplanetapi.savetheplanetapi.repository;

import com.igti.savetheplanetapi.savetheplanetapi.model.Perfil;
import com.igti.savetheplanetapi.savetheplanetapi.repository.perfil.PerfilRepositoryImpl;
import com.igti.savetheplanetapi.savetheplanetapi.repository.perfil.PerfilRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long>, PerfilRepositoryQuery {

}
