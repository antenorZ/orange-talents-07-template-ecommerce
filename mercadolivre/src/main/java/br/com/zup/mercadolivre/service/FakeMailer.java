package br.com.zup.mercadolivre.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.zup.mercadolivre.model.Usuario;

@Component
@Primary
public class FakeMailer implements Mailer{

	@Override
	public void send(String corpoEmail, String titulo, String usrAutorPergunta, Usuario usrDonoProduto) {
		System.out.println(corpoEmail);
		System.out.println(titulo);
		System.out.println(usrAutorPergunta);
		System.out.println(usrDonoProduto);
	}
}
