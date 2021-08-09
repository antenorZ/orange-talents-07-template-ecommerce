package br.com.zup.mercadolivre.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.config.security.TokenService;
import br.com.zup.mercadolivre.form.ImagemForm;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.repository.CategoriaRepository;
import br.com.zup.mercadolivre.repository.OpiniaoRepository;
import br.com.zup.mercadolivre.repository.ProdutoRepository;
import br.com.zup.mercadolivre.repository.UsuarioRepository;
import br.com.zup.mercadolivre.dto.DetalhesDoProdutoDto;

@RestController
public class DetalhesProduto {
	
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
	
	@GetMapping(value="produtos/{id}/detalhes")
	public ResponseEntity<?> exibeDetalhesProduto(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
		Long idConsumidorLogado = tokenService.getIdUsuario(token.substring(7,token.length()));
		Usuario usrLogado = usuarioRepository.getById(idConsumidorLogado);
		Optional<Produto> produto = produtoRepository.findById(id);
		if(produto.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoProdutoDto(produto.get()));
		}
		return ResponseEntity.notFound().build();
	}
}
