package com.example.algamoney.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		
		String mensagemUsuario = messageSource.getMessage("mensagem.invalida",null,  LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause() != null ? ex.toString() : ex.toString();
		
		
		return handleExceptionInternal(ex, new Erros(mensagemUsuario, mensagemDesenvolvedor), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erros> erros = listaDeErros(ex.getBindingResult());		
		return handleExceptionInternal(ex, erros, headers, status, request);
	}
	
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
	
		String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado",null,  LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erros> erros = Arrays.asList(new Erros(mensagemUsuario, mensagemDesenvolvedor));		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	
	public List<Erros> listaDeErros(BindingResult result){
		List<Erros> erros = new ArrayList<>();
		for(FieldError fielderror : result.getFieldErrors()) {
			String mensagemUsuario = messageSource.getMessage(fielderror, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor=fielderror.toString();
			
			erros.add(new Erros(mensagemUsuario, mensagemDesenvolvedor));
		}
		
		
		return erros;
	}
	
	
	
	
	public static class Erros {
		
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;
		public Erros(String mensagemUsuario, String mensagemDesenvolvedor) {
			
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}
		public String getMensagemUsuario() {
			return mensagemUsuario;
		}
		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}
		
		
		
		
	}

}
