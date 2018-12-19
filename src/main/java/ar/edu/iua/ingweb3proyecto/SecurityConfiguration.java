package ar.edu.iua.ingweb3proyecto;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import ar.edu.iua.ingweb3proyecto.web.services.Constantes;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true, 
		securedEnabled = true, 
		jsr250Enabled = true) 
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Qualifier("persistenceUserDetailService")
	@Autowired
	private UserDetailsService userDetailService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService);
	}

	@Bean
	public PasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Value("${recursos.estaticos}")
	private String recursosEstaticos;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String[] resources = recursosEstaticos.split(",");

		http.authorizeRequests().antMatchers(resources).permitAll().anyRequest().authenticated();
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/dologin").permitAll().anyRequest()
				.authenticated();

		http.formLogin().loginPage(Constantes.URL_DENY).defaultSuccessUrl(Constantes.URL_LOGINOK)
				.loginProcessingUrl("/dologin").permitAll().failureForwardUrl(Constantes.URL_DENY);

		http.logout().logoutSuccessUrl(Constantes.URL_LOGOUTOK).deleteCookies("SESSION", "rmiw3")
				.clearAuthentication(true);

		http.csrf().disable();

	}

	@Autowired
	private DataSource ds;

	public PersistentTokenRepository rmRepository() {
		JdbcTokenRepositoryImpl r = new JdbcTokenRepositoryImpl();
		r.setDataSource(ds);
		return r;
	}
}

/*
 * CREATE TABLE `persistent_logins` ( `username` varchar(100) NOT NULL, `series`
 * varchar(64) NOT NULL, `token` varchar(64) NOT NULL, `last_used` timestamp NOT
 * NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY
 * (`series`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 * 
 */