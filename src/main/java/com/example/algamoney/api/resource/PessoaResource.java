package com.example.algamoney.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	public List<Pessoa> trazerTodos(){
		return pessoaRepository.findAll();
	}
	
	
	@PostMapping
	public ResponseEntity<Pessoa> salvar(@RequestBody @Valid Pessoa pessoa, HttpServletResponse response){
		 Pessoa pessoaCriada = pessoaRepository.save(pessoa);
		
		 publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaCriada.getCodigo()));
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaCriada);
	}
	
	
	@RequestMapping("/{codigo}")
	public ResponseEntity<Pessoa> trazerPeloCodigo(@PathVariable Long codigo){
		Pessoa pessoaCodigo = pessoaRepository.findOne(codigo);
		if(pessoaCodigo == null) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(pessoaCodigo);
	}
	
}
