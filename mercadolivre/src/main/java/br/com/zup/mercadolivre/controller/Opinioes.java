package br.com.zup.mercadolivre.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.config.security.TokenService;
import br.com.zup.mercadolivre.dto.ProdutoDto;
import br.com.zup.mercadolivre.form.OpiniaoForm;
import br.com.zup.mercadolivre.model.Opiniao;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.repository.CategoriaRepository;
import br.com.zup.mercadolivre.repository.OpiniaoRepository;
import br.com.zup.mercadolivre.repository.ProdutoRepository;
import br.com.zup.mercadolivre.repository.UsuarioRepository;

@RestController
public class Opinioes {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private OpiniaoRepository opiniaoRepository;
	
	@Transactional
	@PostMapping (value = "/produtos/{id}/opiniao")
	public ResponseEntity<ProdutoDto> addOpinao(@PathVariable("id") Long id, @Valid @RequestBody OpiniaoForm opiniaoForm, @RequestHeader("Authorization") String token){
		Long idConsumidorLogado = tokenService.getIdUsuario(token.substring(7,token.length()));
		Usuario consumidor = usuarioRepository.getById(idConsumidorLogado);
		Produto produtoSelecionado = produtoRepository.getById(id);
		Opiniao opiniao = opiniaoForm.toModel(produtoSelecionado, consumidor);
		opiniaoRepository.save(opiniao);
		return ResponseEntity.status(HttpStatus.OK).body(produtoSelecionado.toDto());
	}

}
