package fi.ukkosnetti.symprap.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

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
import com.jayway.restassured.response.ValidatableResponse;

import fi.ukkosnetti.symprap.SymprapApplication;
import fi.ukkosnetti.symprap.dto.SymptomCreate;
import fi.ukkosnetti.symprap.dto.SymptomGet;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SymprapApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0",
        "spring.datasource.url:jdbc:h2:mem:symprap;DB_CLOSE_ON_EXIT=FALSE"})
public class SymptomControllerTest {

	private static final String SYMPTOM = "diabetes";
	
	@Value("${local.server.port}")
	private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }
    
	@Test
	public void insertsSymptom() throws Exception {
		insertSymptom("diarrhea").statusCode(Status.OK.getStatusCode());
	}
	
	@Test
	public void returnsSymptom() throws Exception {
		Long id = insertSymptom(SYMPTOM).extract().as(SymptomGet.class).getId();
		SymptomGet symptom = given().contentType(MediaType.APPLICATION_JSON)
			.get("/symptom/" + id).then().extract().as(SymptomGet.class);
		assertEquals(id, symptom.getId());
		assertEquals(SYMPTOM, symptom.getSymptom());
	}
	
	private ValidatableResponse insertSymptom(String symptom) {
		return given().contentType(MediaType.APPLICATION_JSON)
			.body(new SymptomCreate(symptom))
			.post("/symptom/create")
			.then();
	}
}
