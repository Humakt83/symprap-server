package fi.ukkosnetti.symprap.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import java.util.Arrays;
import java.util.Date;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;

import fi.ukkosnetti.symprap.SymprapApplication;
import fi.ukkosnetti.symprap.dto.SymptomCreate;
import fi.ukkosnetti.symprap.dto.SymptomGet;
import fi.ukkosnetti.symprap.dto.UserCreate;
import fi.ukkosnetti.symprap.model.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SymprapApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0",
        "spring.datasource.url:jdbc:h2:mem:symprap;DB_CLOSE_ON_EXIT=TRUE"})
public class UserControllerTest {

	private static final String SYMPTOM = "leuchomy";
	@Value("${local.server.port}")
	private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }
    
	@Test
	public void createsUser() throws Exception {
		final String userName = "todDil", firstName = "Tom", lastName = "Bombadil";
		given().contentType(MediaType.APPLICATION_JSON)
			.body(new UserCreate(userName, "pass", firstName, lastName, new Date(0), 2312312l, Arrays.asList(UserRole.TEEN), Arrays.asList(insertSymptom())))
			.post("/user/create")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.body(containsString(userName))
			.body(containsString(firstName))
			.body(containsString(lastName))
			.body(containsString(UserRole.TEEN.toString()))
			.body(containsString(SYMPTOM));
	}
	
	private SymptomGet insertSymptom() {
		return given().contentType(MediaType.APPLICATION_JSON)
			.body(new SymptomCreate(SYMPTOM))
			.post("/symptom/create")
			.then().extract().as(SymptomGet.class);
	}
}
