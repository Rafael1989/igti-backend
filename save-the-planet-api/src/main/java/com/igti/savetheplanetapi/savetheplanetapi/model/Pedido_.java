package com.igti.savetheplanetapi.savetheplanetapi.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pedido.class)
public abstract class Pedido_ {

	public static volatile SingularAttribute<Pedido, Long> codigo;
	public static volatile SingularAttribute<Pedido, String> status;
	public static volatile SingularAttribute<Pedido, Usuario> cliente;
	public static volatile SingularAttribute<Pedido, Usuario> entregador;
	public static volatile ListAttribute<Pedido, Prato> pratos;

}

