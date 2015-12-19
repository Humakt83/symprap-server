package fi.ukkosnetti.symprap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import fi.ukkosnetti.symprap.auth.OAuth2SecurityConfiguration;

@EnableAutoConfiguration(exclude = { OAuth2SecurityConfiguration.class, SymprapApplication.class})
@EntityScan("fi.ukkosnetti.symprap.model")
@EnableGlobalMethodSecurity(securedEnabled=false, prePostEnabled=false)
@ComponentScan(basePackages = {"fi.ukkosnetti.symprap.controller", "fi.ukkosnetti.symprap.service", 
		"fi.ukkosnetti.symprap.conversion", "fi.ukkosnetti.symprap.repository, fi.ukkosnetti.symprap.auth.verifier"})
public class SymprapTestApplication {

	public static void main(String ... args) {
		SpringApplication.run(SymprapTestApplication.class, args);
	}
}
