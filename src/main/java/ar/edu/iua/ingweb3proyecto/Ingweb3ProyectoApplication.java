package ar.edu.iua.ingweb3proyecto;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
public class Ingweb3ProyectoApplication implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DataSource dataSource; 
	
	public static void main(String[] args) {
		SpringApplication.run(Ingweb3ProyectoApplication.class, args);
	}

	@Autowired
	private PasswordEncoder pe;

	@Override
	public void run(String... args) throws Exception {
		log.debug("DataSource actual = " + dataSource);
	}
}
