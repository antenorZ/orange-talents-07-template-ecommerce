package br.com.zup.mercadolivre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.mercadolivre.model.Compra;
import br.com.zup.mercadolivre.model.Transacao;

@Service
public class NotaFiscal {
	@Autowired
	GeradorNotaFiscal geradorNotaFiscal;
	
	public void geraNota(Compra compraProcessada) {
		geradorNotaFiscal.geraNota(compraProcessada.getId(), compraProcessada.getUsrComprador().getId());
	}
}
