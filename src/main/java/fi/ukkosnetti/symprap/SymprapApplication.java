package fi.ukkosnetti.symprap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Import;

import fi.ukkosnetti.symprap.auth.OAuth2SecurityConfiguration;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan
@Import(OAuth2SecurityConfiguration.class)
public class SymprapApplication {

	public static void main(String ... args) {
		SpringApplication.run(SymprapApplication.class, args);
	}
}
