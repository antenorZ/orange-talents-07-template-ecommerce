package br.com.zup.mercadolivre.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.zup.mercadolivre.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//autorizacao
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().
		antMatchers(HttpMethod.POST, "/categorias").permitAll()
		.antMatchers(HttpMethod.POST, "/usuarios/**").permitAll()
		.antMatchers("/h2-console/**").permitAll()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
	}
	
	//recursos estáticos: css, imagens etc
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/produtos/**")
        .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**")
        .antMatchers("/h2-console", "/h2-console/*", "/favicon.ico", "/favicon.ico/**");
	}
}
