package com.example.algamoney.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.CategoriaRepository;
import com.example.algamoney.api.repository.filter.CategoriaFilter;
import com.example.algamoney.api.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CategoriaService categoriaService;
		
		
	@GetMapping
	public Page<Categoria> listarTodos(CategoriaFilter categoriaFilter, Pageable pageable){		
		return categoriaRepository.filtrar(categoriaFilter,pageable);
	}
	
	@RequestMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable long codigo) {
		Categoria categoria = categoriaService.buscarPeloCodigo(codigo);		
		return ResponseEntity.ok(categoria);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> criar(@Valid @RequestBody  Categoria categoria, HttpServletResponse response) {	
	Categoria categoriaJaSalva = categoriaService.salvar(categoria);
	
	publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaJaSalva.getCodigo()));
	
	return ResponseEntity.status(HttpStatus.CREATED).body(categoriaJaSalva);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, @RequestBody @Valid Categoria categoria){
		Categoria categoriaAtt = categoriaService.atualizar(codigo, categoria);
		return ResponseEntity.ok(categoriaAtt);
	}
	
	@DeleteMapping("{codigo}")
	public ResponseEntity<Categoria> deletar(@PathVariable Long codigo){
		categoriaService.deletar(codigo);
		return ResponseEntity.noContent().build();
	}
	
}
