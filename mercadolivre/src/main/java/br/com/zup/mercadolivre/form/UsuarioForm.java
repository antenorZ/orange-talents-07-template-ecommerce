package br.com.zup.mercadolivre.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import br.com.zup.mercadolivre.config.validation.SenhaLimpa;
import br.com.zup.mercadolivre.config.validation.Unique;
import br.com.zup.mercadolivre.model.Usuario;

public class UsuarioForm{
	@NotNull @NotBlank
	@Unique(domainClass = Usuario.class, fieldName = "login")
	@Email
	private String login;
	
	@NotNull @NotBlank @Length(min=6)
	private String senha;
	
	public UsuarioForm(@NotNull @NotBlank @Email String login, @NotNull @NotBlank @Length(min = 6) String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public Usuario toModel(){
		return new Usuario(login, senha);
	}
	
}
