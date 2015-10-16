package fi.ukkosnetti.symprap.support;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fi.ukkosnetti.symprap.dto.QuestionCreate;
import fi.ukkosnetti.symprap.dto.SymptomCreate;
import fi.ukkosnetti.symprap.dto.SymptomGet;
import fi.ukkosnetti.symprap.dto.UserCreate;
import fi.ukkosnetti.symprap.model.AnswerType;
import fi.ukkosnetti.symprap.model.UserRole;
import fi.ukkosnetti.symprap.service.QuestionService;
import fi.ukkosnetti.symprap.service.SymptomService;
import fi.ukkosnetti.symprap.service.UserService;

@Component
public class TestDataInjector {
		
	@Value("${testDataEnabled:false}")
	private boolean testDataEnabled;
	
	@Autowired
	private SymptomService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private QuestionService questionService;
	
	@PostConstruct
	public void injectTestData() {
		if (!testDataEnabled) return;
		final SymptomGet symptom = injectSymptom();
		injectUser(symptom);
		injectQuestions(symptom.getId());
	}

	private void injectQuestions(Long symptomId) {
		questionService.createQuestion(new QuestionCreate("Did you administer insulin?", AnswerType.BOOLEAN, symptomId));
		questionService.createQuestion(new QuestionCreate("What was your blood sugar level last you took it?", 
				AnswerType.DOUBLE, symptomId));
		questionService.createQuestion(new QuestionCreate("What did you eat at meal time?", AnswerType.TEXT, symptomId));
	}

	private SymptomGet injectSymptom() {
		return service.createSymptom(new SymptomCreate("Diabetes"));
	}
	
	private void injectUser(SymptomGet symptom) {
		userService.createUser(new UserCreate("tester", "test", "First", "Last", null, 1234543l, 
				Arrays.asList(UserRole.TEEN), Arrays.asList(symptom)));
	}
}
