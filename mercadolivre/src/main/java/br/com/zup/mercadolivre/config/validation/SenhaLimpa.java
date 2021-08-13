//package br.com.zup.mercadolivre.config.validation;
//
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//
//import org.hibernate.validator.constraints.Length;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.util.Assert;
//
//public class SenhaLimpa {
//	
//	@NotNull @NotBlank @Length(min = 6)
//	private String senha;
//
//	public SenhaLimpa(@NotNull @NotBlank @Length(min = 6) String senha) {
//		Assert.hasLength(senha, "senha não pode ser em branco");
//		Assert.isTrue(senha.length()>6, "senha tem que ter no minimo 6 caracteres");
//		this.senha = senha;
//	}	
//	
//	public String hash() {
//			return new BCryptPasswordEncoder().encode(senha);
//	}
//	
//}
