package com.example.algamoney.api.repository.categoria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.filter.CategoriaFilter;



public class CategoriaRepositoryImp implements CategoriaQuery {

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public Page<Categoria> filtrar(CategoriaFilter categoriaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Categoria> criteria = builder.createQuery(Categoria.class);
		Root<Categoria> root = criteria.from(Categoria.class);
		 
		Predicate[] predicates = criarRestricoes(categoriaFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Categoria> query = manager.createQuery(criteria);
		criarPaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(categoriaFilter));
	}
 

	private void criarPaginacao(TypedQuery<Categoria> query, Pageable pageable) {
		// TODO Auto-generated method stub
		
	}


	private Long total(CategoriaFilter categoriaFilter) {
		// TODO Auto-generated method stub
		return null;
	}


	private Predicate[] criarRestricoes(CategoriaFilter categoriaFilter, CriteriaBuilder builder,
			Root<Categoria> root) {
		// TODO Auto-generated method stub
		return null;
	}

}
