package br.com.zup.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.config.security.TokenService;
import br.com.zup.mercadolivre.form.RetornoPagSeguroForm;
import br.com.zup.mercadolivre.form.RetornoPayPalForm;
import br.com.zup.mercadolivre.model.Compra;
import br.com.zup.mercadolivre.model.RetornoGatewayPagamento;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.repository.CompraRepository;
import br.com.zup.mercadolivre.repository.UsuarioRepository;

@RestController
public class ComprasPt2Controller {
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;	
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping(value="/retorno-pagseguro/{id}")
	public String processamentoPagSeguro(@PathVariable("id") Long id, @RequestBody @Valid RetornoPagSeguroForm retornoPagSeguroForm, @RequestHeader("Authorization") String token, UriComponentsBuilder uriBuilder){
		Long idUsrComprador = tokenService.getIdUsuario(token.substring(7,token.length()));
		Usuario usrComprador = usuarioRepository.getById(idUsrComprador);
		return processa(id, retornoPagSeguroForm, token, uriBuilder);
	}
	
	@PostMapping(value="/retorno-paypal/{id}")
	public String processamentoPayPal(@PathVariable("id") Long id, @RequestBody @Valid RetornoPayPalForm retornoPayPalForm, @RequestHeader("Authorization") String token, UriComponentsBuilder uriBuilder){
		Long idUsrComprador = tokenService.getIdUsuario(token.substring(7,token.length()));
		Usuario usrComprador = usuarioRepository.getById(idUsrComprador);
		return processa(id, retornoPayPalForm, token, uriBuilder);
	}

	private String processa(@PathVariable("id") Long id, @RequestBody @Valid RetornoGatewayPagamento retornoGatewayPagamento, @RequestHeader("Authorization") String token, UriComponentsBuilder uriBuilder) {
		Compra compraProcessada = compraRepository.getById(id);
		compraProcessada.adicionaTransacao(retornoGatewayPagamento);
		compraRepository.save(compraProcessada);
		return "pagamento recebido";
	}
}
