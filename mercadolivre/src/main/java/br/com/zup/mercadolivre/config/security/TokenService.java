package br.com.zup.mercadolivre.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.zup.mercadolivre.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${mercadolivre.jwt.expiration}")
	private String exp;
	
	@Value("${mercadolivre.jwt.secret}")
	private String segredo;
	
	public String gerarToken(Authentication authentication) {
		Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(exp));
		
		return Jwts.builder()
				.setIssuer("API Mercado Livre")
				.setSubject(usuarioLogado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, segredo)
				.compact();
	}

	public boolean isValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.segredo).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.segredo).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}
