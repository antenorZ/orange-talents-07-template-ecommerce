package br.com.zup.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.dto.CategoriaDto;
import br.com.zup.mercadolivre.form.CategoriaForm;
import br.com.zup.mercadolivre.model.Categoria;
import br.com.zup.mercadolivre.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class Categorias{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping
	public ResponseEntity<CategoriaDto> criar(@RequestBody @Valid CategoriaForm categoriaForm){
		Categoria categoria = categoriaForm.toModel(categoriaRepository);
		categoriaRepository.save(categoria);
		return ResponseEntity.status(HttpStatus.OK).body(categoria.toDto());
	}
}
