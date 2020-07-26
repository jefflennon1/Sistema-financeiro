package com.example.algamoney.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria atualizar(Long codigo, Categoria categoria) {
		return null;
	}

	public Categoria buscarPeloCodigo(long codigo) {
		Categoria categoria = categoriaRepository.findOne(codigo);
		if(StringUtils.isEmpty(categoria)) {
			throw new EmptyResultDataAccessException(1);
		}
		return categoria;
	}

	
}
