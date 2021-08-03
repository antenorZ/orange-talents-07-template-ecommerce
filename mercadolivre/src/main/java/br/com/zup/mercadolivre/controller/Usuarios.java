package br.com.zup.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.dto.UsuarioDto;
import br.com.zup.mercadolivre.form.UsuarioForm;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.repository.UsuarioRepository;


@RestController
@RequestMapping("/usuarios")
public class Usuarios{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public ResponseEntity<UsuarioDto> criar(@RequestBody @Valid UsuarioForm usuarioForm){
		Usuario usuario = usuarioForm.toModel();
		usuarioRepository.save(usuario);
		return ResponseEntity.status(HttpStatus.OK).body(usuario.toDto());
	}
}
