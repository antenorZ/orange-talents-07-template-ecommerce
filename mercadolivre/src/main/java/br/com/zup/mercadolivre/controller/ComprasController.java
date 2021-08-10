package br.com.zup.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.config.security.TokenService;
import br.com.zup.mercadolivre.form.CompraForm;
import br.com.zup.mercadolivre.model.Compra;
import br.com.zup.mercadolivre.model.GatewayPagamento;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.repository.CompraRepository;
import br.com.zup.mercadolivre.repository.ProdutoRepository;
import br.com.zup.mercadolivre.repository.UsuarioRepository;
import br.com.zup.mercadolivre.service.Email;

@RestController
@RequestMapping("/compras")
public class ComprasController {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private Email email;
	
	
	@PostMapping
	public String criar(@RequestBody @Valid CompraForm compraForm, @RequestHeader("Authorization") String token, UriComponentsBuilder uriBuilder){
		Long idUsrComprador = tokenService.getIdUsuario(token.substring(7,token.length()));
		Usuario usrComprador = usuarioRepository.getById(idUsrComprador);
		Produto produtoSelecionado = produtoRepository.getById(compraForm.getIdProdutoSelecionado());
		Integer qtdAComprar = compraForm.getQtdProdutoSelecionado();
		
		boolean abateu = produtoSelecionado.abateEstoque(qtdAComprar);
		
		if(abateu) {
			GatewayPagamento gatewaySelecionado = compraForm.getGateway();
			Compra compra = compraForm.toModel(produtoSelecionado, usrComprador, gatewaySelecionado);
			compraRepository.save(compra);
			email.novaCompra(compra);
			return compra.urlRedirecionamento(uriBuilder);
		}
		else {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build().toString();
		}
	}
}
