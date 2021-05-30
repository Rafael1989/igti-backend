package com.igti.savetheplanetapi.savetheplanetapi.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(com.igti.savetheplanetapi.savetheplanetapi.model.Prato.class)
public abstract class Prato_ {

	public static volatile SingularAttribute<com.igti.savetheplanetapi.savetheplanetapi.model.Prato, Long> codigo;
	public static volatile SingularAttribute<com.igti.savetheplanetapi.savetheplanetapi.model.Prato, BigDecimal> valor;
	public static volatile SingularAttribute<com.igti.savetheplanetapi.savetheplanetapi.model.Prato, String> descricao;
	public static volatile SingularAttribute<com.igti.savetheplanetapi.savetheplanetapi.model.Prato, String> quantidade;
	public static volatile SingularAttribute<com.igti.savetheplanetapi.savetheplanetapi.model.Prato, Usuario> cozinheira;

}

