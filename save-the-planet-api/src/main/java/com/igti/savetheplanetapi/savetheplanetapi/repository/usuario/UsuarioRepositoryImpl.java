package com.igti.savetheplanetapi.savetheplanetapi.repository.usuario;

import com.igti.savetheplanetapi.savetheplanetapi.model.*;
import com.igti.savetheplanetapi.savetheplanetapi.repository.filter.PratoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Usuario> filtrar(Usuario usuario, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);

		Predicate[] predicates = criarRestricoes(usuario, builder, root);
		criteria.where(predicates);

		TypedQuery<Usuario> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total());
	}

	private Predicate[] criarRestricoes(Usuario usuario, CriteriaBuilder builder,
										Root<Usuario> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(usuario.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(Usuario_.nome)), "%" + usuario.getNome().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Predicate[] criarRestricoesCozinheiras(Usuario usuario, CriteriaBuilder builder,
										Root<Usuario> root) {
		List<Predicate> predicates = new ArrayList<>();
		Perfil perfil = new Perfil();
		perfil.setCodigo(2L);
		predicates.add(builder.equal(root.get(Usuario_.perfil), perfil));

		if (!StringUtils.isEmpty(usuario.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(Usuario_.nome)), "%" + usuario.getNome().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	@Override
	public Page<Usuario> resumir(Usuario usuario, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		
		criteria.select(builder.construct(Usuario.class, root.get(Usuario_.codigo)
				, root.get(Usuario_.nome), root.get(Usuario_.email)
				, root.get(Usuario_.senha), root.get(Usuario_.cpf), root.get(Usuario_.perfil), root.get(Usuario_.cidade), root.get(Usuario_.bairro), root.get(Usuario_.rua), root.get(Usuario_.numero), root.get(Usuario_.complemento)));

		Predicate[] predicates = criarRestricoes(usuario, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Usuario> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total());
	}

	@Override
	public Page<Usuario> resumirCozinheiras(Usuario usuario, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);

		criteria.select(builder.construct(Usuario.class, root.get(Usuario_.codigo)
				, root.get(Usuario_.nome), root.get(Usuario_.email)
				, root.get(Usuario_.senha), root.get(Usuario_.cpf), root.get(Usuario_.perfil), root.get(Usuario_.cidade), root.get(Usuario_.bairro), root.get(Usuario_.rua), root.get(Usuario_.numero), root.get(Usuario_.complemento)));

		Predicate[] predicates = criarRestricoesCozinheiras(usuario, builder, root);
		criteria.where(predicates);

		TypedQuery<Usuario> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, totalCozinheiras(usuario));
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private Long totalCozinheiras(Usuario usuario) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Usuario> root = criteria.from(Usuario.class);

		Predicate[] predicates = criarRestricoesCozinheiras(usuario, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
