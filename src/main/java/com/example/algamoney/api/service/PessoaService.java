package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Endereco;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPeloCodigo(codigo);
		
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return pessoaRepository.save(pessoaSalva);		
	}


	public Pessoa buscarPeloCodigo(Long codigo) {
		Pessoa pessoaCodigo = pessoaRepository.findOne(codigo);
		if(pessoaCodigo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
	 return pessoaCodigo;
	}

	
	public void atualizaPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = buscarPeloCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}

	public Pessoa atualizarEndeco(Long codigo, Endereco endereco) {
		Pessoa pessoa = buscarPeloCodigo(codigo);
		pessoa.setEndereco(endereco);
		return pessoaRepository.save(pessoa);
	}


	public Pessoa salvar(Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		return pessoaSalva;
	}


	public void deletar(Long codigo) {
		Pessoa pessoa = buscarPeloCodigo(codigo);
		pessoaRepository.delete(pessoa);
	}
	
	
}
