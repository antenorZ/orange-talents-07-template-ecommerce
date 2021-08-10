package br.com.zup.mercadolivre.service;

import br.com.zup.mercadolivre.model.Compra;
import br.com.zup.mercadolivre.model.Usuario;

public interface GeradorNotaFiscal{
	void geraNota(Long idCompraEfetuada, Long idUsrComprador);
}
