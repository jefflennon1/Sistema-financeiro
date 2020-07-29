package com.example.algamoney.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.model.Endereco;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;
import com.example.algamoney.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private PessoaService pessoaService;
	
	
	@GetMapping
	public List<Pessoa> trazerTodos(){
		return pessoaRepository.findAll();
	}

	@RequestMapping("/{codigo}")
	public ResponseEntity<Pessoa> trazerPeloCodigo(@PathVariable Long codigo){
		Pessoa pessoaCodigo = pessoaService.buscarPeloCodigo(codigo);	
		return ResponseEntity.ok(pessoaCodigo);
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> salvar(@RequestBody @Valid Pessoa pessoa, HttpServletResponse response){
		 Pessoa pessoaCriada = pessoaService.salvar(pessoa);		
		 publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaCriada.getCodigo()));		 
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaCriada);
	}
	
	@PutMapping("/{codigo}/endereco")
	public ResponseEntity<Pessoa> atualizaEndereco(@PathVariable Long codigo, @RequestBody Endereco endereco){
		Pessoa pessoa = pessoaService.atualizarEndeco(codigo, endereco);
		return ResponseEntity.ok(pessoa);
	}	
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @RequestBody @Valid Pessoa pessoa){		
		Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);	
		return ResponseEntity.ok(pessoaSalva);
	}
	
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizaAtivo(@PathVariable Long codigo,@RequestBody Boolean ativo) {	
		pessoaService.atualizaPropriedadeAtivo(codigo, ativo); 
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		pessoaRepository.delete(codigo); 
	}
	
}
