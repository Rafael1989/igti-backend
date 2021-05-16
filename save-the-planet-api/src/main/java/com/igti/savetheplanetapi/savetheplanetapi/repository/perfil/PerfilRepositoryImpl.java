package com.igti.savetheplanetapi.savetheplanetapi.repository.perfil;

import com.igti.savetheplanetapi.savetheplanetapi.model.Perfil;
import com.igti.savetheplanetapi.savetheplanetapi.model.Perfil_;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PerfilRepositoryImpl implements PerfilRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	private Predicate[] criarRestricoes(CriteriaBuilder builder,
										Root<Perfil> root) {
		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.notLike(builder.lower(root.get(Perfil_.nome)), "%ADMIN%"));

		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	@Override
	public List<Perfil> resumir() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Perfil> criteria = builder.createQuery(Perfil.class);
		Root<Perfil> root = criteria.from(Perfil.class);
		
		criteria.select(builder.construct(Perfil.class, root.get(Perfil_.codigo)
				, root.get(Perfil_.nome)));

		Predicate[] predicates = criarRestricoes(builder, root);
		criteria.where(predicates);
		
		TypedQuery<Perfil> query = manager.createQuery(criteria);

		return query.getResultList();
	}

}
