package br.com.zup.mercadolivre.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutrosSistemasController {
	
	@PostMapping(value="nota-fiscal")
	public String enviaNotaFiscal() {
		return "a";
	}
	
	@PostMapping(value="ranking-vendedores")
	public String rankingVendedores() {
		return "b";
	}
}
