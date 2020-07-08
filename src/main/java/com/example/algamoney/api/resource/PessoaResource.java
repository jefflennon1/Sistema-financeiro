package com.example.algamoney.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping
	public List<Pessoa> trazerTodos(){
		return pessoaRepository.findAll();
	}
	
	
	@PostMapping
	public ResponseEntity<Pessoa> salvar(@RequestBody @Valid Pessoa pessoa, HttpServletResponse response){
		 Pessoa pessoaCriada = pessoaRepository.save(pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(pessoaCriada.getCodigo()).toUri();
		 response.setHeader("locale", uri.toASCIIString());
		 
		 
		return ResponseEntity.created(uri).body(pessoaCriada);
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
