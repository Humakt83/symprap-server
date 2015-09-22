package fi.ukkosnetti.symprap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan
public class SymprapApplication {

	public static void main(String ... args) {
		SpringApplication.run(SymprapApplication.class, args);
	}
}
