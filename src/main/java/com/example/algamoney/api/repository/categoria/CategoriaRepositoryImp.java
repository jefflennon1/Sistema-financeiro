package com.example.algamoney.api.repository.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.filter.CategoriaFilter;

public class CategoriaRepositoryImp implements CategoriaQuery {

	@Override
	public Page<Categoria> filtrar(CategoriaFilter categoriaFilter, Pageable pageable) {
		
		return new PageImpl<Categoria>(content, pageable, total(categoriaFilter));
	}

}
