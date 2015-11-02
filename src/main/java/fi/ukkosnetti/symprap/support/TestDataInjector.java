package fi.ukkosnetti.symprap.support;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fi.ukkosnetti.symprap.dto.DiseaseCreate;
import fi.ukkosnetti.symprap.dto.DiseaseGet;
import fi.ukkosnetti.symprap.dto.QuestionCreate;
import fi.ukkosnetti.symprap.dto.UserCreate;
import fi.ukkosnetti.symprap.dto.UserGet;
import fi.ukkosnetti.symprap.model.AnswerType;
import fi.ukkosnetti.symprap.model.UserRole;
import fi.ukkosnetti.symprap.service.DiseaseService;
import fi.ukkosnetti.symprap.service.QuestionService;
import fi.ukkosnetti.symprap.service.UserService;

@Component
public class TestDataInjector {
		
	@Value("${testDataEnabled:false}")
	private boolean testDataEnabled;
	
	@Autowired
	private DiseaseService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private QuestionService questionService;
	
	@PostConstruct
	public void injectTestData() {
		if (!testDataEnabled) return;
		final DiseaseGet disease = injectDisease();
		final UserGet user = injectUser(disease);
		injectFollower(user);
		injectQuestions(disease.getId());
	}

	private void injectQuestions(Long diseaseId) {
		questionService.createQuestion(new QuestionCreate("Did you administer insulin?", AnswerType.BOOLEAN, diseaseId));
		questionService.createQuestion(new QuestionCreate("What was your blood sugar level last time you took it?", 
				AnswerType.DOUBLE, diseaseId));
		questionService.createQuestion(new QuestionCreate("What did you eat at meal time?", AnswerType.TEXT, diseaseId));
	}

	private DiseaseGet injectDisease() {
		return service.createDisease(new DiseaseCreate("Diabetes"));
	}
	
	private UserGet injectUser(DiseaseGet disease) {
		return userService.createUser(new UserCreate("tester", "test", "First", "Last", null, 1234543l, 
				Arrays.asList(UserRole.TEEN), Arrays.asList(disease)));
	}
	
	private void injectFollower(UserGet user) {
		final String username = "follower"; 
		userService.createUser(new UserCreate(username, "test", "First", "Last", null, 1234541l, 
				Arrays.asList(UserRole.FOLLOWER), null));
		userService.addFollower(user.getUserName(), username);
	}
}
