package com.example.algamoney.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.LancamentoRepository;
import com.example.algamoney.api.repository.PessoaRepository;
import com.example.algamoney.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	

	
	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
		if(pessoa == null || pessoa.isInativo()  ) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		
		return lancamentoRepository.save(lancamento);
	}



	public Lancamento buscar(Long codigo) {
		Lancamento lancamento = lancamentoRepository.findOne(codigo);
		if(StringUtils.isEmpty(lancamento)) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return lancamento;
	}



	public void deletar(Lancamento lancamento) {
		lancamentoRepository.delete(lancamento);
		
	}

	
	
	
	
	

}
