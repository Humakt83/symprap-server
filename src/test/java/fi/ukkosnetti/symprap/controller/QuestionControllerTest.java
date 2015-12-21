package fi.ukkosnetti.symprap.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
import fi.ukkosnetti.symprap.dto.DiseaseCreate;
import fi.ukkosnetti.symprap.dto.DiseaseGet;
import fi.ukkosnetti.symprap.dto.QuestionCreate;
import fi.ukkosnetti.symprap.model.AnswerType;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SymprapApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0",
        "spring.datasource.url:jdbc:h2:mem:symprap;DB_CLOSE_ON_EXIT=TRUE"})
public class QuestionControllerTest {

	private static final String QUESTION = "Is this a question?";
	private static Long diseaseId; 
	
	@Value("${local.server.port}")
	private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
        if (diseaseId == null) diseaseId = insertDisease();
    }
    
	@Test
	public void insertsQuestion() throws Exception {
		given().contentType(MediaType.APPLICATION_JSON)
			.body(new QuestionCreate(QUESTION, AnswerType.TEXT, diseaseId))
			.post("/question/create")
			.then()
			.statusCode(Status.OK.getStatusCode());
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void returnsQuestions() throws Exception {
		insertsQuestion();
		insertsQuestion();
		List questions = given().contentType(MediaType.APPLICATION_JSON)
			.get("/question/all").then().extract().as(List.class);
		assertTrue(questions.size() > 1);
	}
	
	private Long insertDisease() {
		return given().contentType(MediaType.APPLICATION_JSON)
			.body(new DiseaseCreate("flu"))
			.post("/disease/create")
			.then().extract().as(DiseaseGet.class).id;
	}
	
}
