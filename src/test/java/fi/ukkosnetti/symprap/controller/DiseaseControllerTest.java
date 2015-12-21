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

import fi.ukkosnetti.symprap.SymprapTestApplication;
import fi.ukkosnetti.symprap.dto.DiseaseCreate;
import fi.ukkosnetti.symprap.dto.DiseaseGet;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SymprapTestApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0",
        "spring.datasource.url:jdbc:h2:mem:symprap;DB_CLOSE_ON_EXIT=TRUE", "testAuthDisabled:true"})
public class DiseaseControllerTest {

	private static final String DISEASE = "diabetes";
	
	@Value("${local.server.port}")
	private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }
    
	@Test
	public void insertsDisease() throws Exception {
		insertDisease("diarrhea").statusCode(Status.OK.getStatusCode());
	}
	
	@Test
	public void returnsDisease() throws Exception {
		Long id = insertDisease(DISEASE).extract().as(DiseaseGet.class).id;
		DiseaseGet disease = given().contentType(MediaType.APPLICATION_JSON)
			.get("/disease/" + id).then().extract().as(DiseaseGet.class);
		assertEquals(id, disease.id);
		assertEquals(DISEASE, disease.disease);
	}
	
	private ValidatableResponse insertDisease(String disease) {
		return given().contentType(MediaType.APPLICATION_JSON)
			.body(new DiseaseCreate(disease))
			.post("/disease/create")
			.then();
	}
}
