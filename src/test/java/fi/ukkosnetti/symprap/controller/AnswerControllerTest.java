package fi.ukkosnetti.symprap.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
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
import fi.ukkosnetti.symprap.dto.AnswerCreate;
import fi.ukkosnetti.symprap.dto.QuestionCreate;
import fi.ukkosnetti.symprap.dto.UserCreate;
import fi.ukkosnetti.symprap.dto.UserGet;
import fi.ukkosnetti.symprap.model.AnswerType;
import fi.ukkosnetti.symprap.model.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SymprapApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0",
        "spring.datasource.url:jdbc:h2:mem:symprap;DB_CLOSE_ON_EXIT=TRUE"})
public class AnswerControllerTest {

	@Value("${local.server.port}")
	private int port;

	private Long questionId;
	private static Long userId;
	
    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
        insertQuestion();
        if (userId == null) userId = insertUser();
    }
    
	@Test
	public void insertsAnAnswerToQuestion() throws Exception {
		given().contentType(MediaType.APPLICATION_JSON)
			.body(new AnswerCreate(questionId, "This is answer", userId, false))
			.post("/answer/create")
			.then()
			.statusCode(Status.OK.getStatusCode());
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void returnsAnswersForQuestion() throws Exception {
		insertsAnAnswerToQuestion();
		insertsAnAnswerToQuestion();
		insertsAnAnswerToQuestion();
		List answers = given().contentType(MediaType.APPLICATION_JSON)
			.get("/answer/byquestion/" + questionId).then().statusCode(Status.OK.getStatusCode()).extract().as(List.class);
		assertTrue(answers.size() > 1);
	}
	
	private void insertQuestion() {
		questionId = Long.parseLong(given().contentType(MediaType.APPLICATION_JSON)
			.body(new QuestionCreate("Is this a question?", AnswerType.TEXT, 5l))
			.post("/question/create")
			.then()
			.extract()
			.path("id")
			.toString());
	}
	
	private Long insertUser() {
		return given().contentType(MediaType.APPLICATION_JSON)
		.body(new UserCreate("tomDil", "pass", "Tom", "Bombadil", new Date(0), 2312321l, Arrays.asList(UserRole.TEEN), null))
		.post("/user/create")
		.then()
		.extract()
		.as(UserGet.class)
		.getId();
	}
}
