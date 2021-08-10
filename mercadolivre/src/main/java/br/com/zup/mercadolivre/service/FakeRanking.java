package br.com.zup.mercadolivre.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FakeRanking implements AdicionaAoRanking{

	@Override
	public void adiciona(Long idCompra, Long idVendedor) {
		System.out.println(idCompra);
		System.out.println(idVendedor);
	}

}
