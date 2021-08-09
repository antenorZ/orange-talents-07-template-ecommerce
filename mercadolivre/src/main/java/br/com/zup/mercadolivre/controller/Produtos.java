package br.com.zup.mercadolivre.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
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
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.mercadolivre.config.security.TokenService;
import br.com.zup.mercadolivre.dto.ProdutoDto;
import br.com.zup.mercadolivre.dto.UsuarioDto;
import br.com.zup.mercadolivre.form.ImagemForm;
import br.com.zup.mercadolivre.form.OpiniaoForm;
import br.com.zup.mercadolivre.form.ProdutoForm;
import br.com.zup.mercadolivre.form.UploaderFake;
import br.com.zup.mercadolivre.form.UsuarioForm;
import br.com.zup.mercadolivre.model.ImagemProduto;
import br.com.zup.mercadolivre.model.Opiniao;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.repository.CategoriaRepository;
import br.com.zup.mercadolivre.repository.OpiniaoRepository;
import br.com.zup.mercadolivre.repository.ProdutoRepository;
import br.com.zup.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class Produtos{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private OpiniaoRepository opiniaoRepository;

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UploaderFake  uploaderFake;
	
	@Autowired
	private EntityManager manager;
	
	@PostMapping
	public ResponseEntity<ProdutoDto> criar(@RequestBody @Valid ProdutoForm produtoForm, @RequestHeader("Authorization") String token){
		Long idUsrDonoLogado = tokenService.getIdUsuario(token.substring(7,token.length()));
		Usuario usrDono = usuarioRepository.getById(idUsrDonoLogado);
		Produto produto = produtoForm.toModel(categoriaRepository, usrDono);
		produtoRepository.save(produto);
		return ResponseEntity.status(HttpStatus.OK).body(produto.toDto());
	}
	
	@PostMapping(value = "/{id}/imagens")
	@Transactional
	public ResponseEntity<ProdutoDto> addImagens(@PathVariable("id") Long id, @Valid ImagemForm imagemForm, @RequestHeader("Authorization") String token) {
		Long idUsrDonoLogado = tokenService.getIdUsuario(token.substring(7,token.length()));
		Usuario usrDono = usuarioRepository.getById(idUsrDonoLogado);
		Set<String> links = uploaderFake.envia(imagemForm.getImagens());
		Produto produto = produtoRepository.getById(id);
		if(produto.pertenceAoUsuario(usrDono)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		produto.associaImagens(links);
		manager.merge(produto);
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(produto, link)).collect(Collectors.toSet());
		imagens.forEach(manager::merge);
		return ResponseEntity.status(HttpStatus.OK).body(produto.toDto());
	}
}