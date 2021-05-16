package com.igti.savetheplanetapi.savetheplanetapi.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile ListAttribute<Usuario, Permissao> permissoes;
	public static volatile SingularAttribute<Usuario, Long> codigo;
	public static volatile SingularAttribute<Usuario, String> nome;
	public static volatile SingularAttribute<Usuario, String> cpf;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile SingularAttribute<Usuario, Perfil> perfil;
	public static volatile SingularAttribute<Usuario, Cidade> cidade;
	public static volatile SingularAttribute<Usuario, String> bairro;
	public static volatile SingularAttribute<Usuario, String> rua;
	public static volatile SingularAttribute<Usuario, String> numero;
	public static volatile SingularAttribute<Usuario, String> complemento;

}

