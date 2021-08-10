package br.com.zup.mercadolivre.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.zup.mercadolivre.model.Compra;
import br.com.zup.mercadolivre.model.Usuario;

@Component
@Primary
public class FakeGeradorNotaFiscal implements GeradorNotaFiscal{

	@Override
	public void geraNota(Long idCompraEfetuada, Long idUsrComprado) {
		System.out.println(idCompraEfetuada);
		System.out.println(idUsrComprado);
	}

}
