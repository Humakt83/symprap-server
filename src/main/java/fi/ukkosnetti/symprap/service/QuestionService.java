package fi.ukkosnetti.symprap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xebia.jacksonlombok.JacksonLombokAnnotationIntrospector;

import fi.ukkosnetti.symprap.dto.QuestionCreate;
import fi.ukkosnetti.symprap.dto.QuestionGet;
import fi.ukkosnetti.symprap.model.Question;
import fi.ukkosnetti.symprap.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository repository;
	
	public List<QuestionGet> getQuestions() {
		ObjectMapper mapper = getObjectMapper();
		List<QuestionGet> questions = new ArrayList<>();
		repository.findAll().forEach(question -> questions.add(mapper.convertValue(question, QuestionGet.class)));
		return questions;
	}
	
	public void createQuestion(QuestionCreate question) {
		repository.save(getObjectMapper().convertValue(question, Question.class));
	}

	private ObjectMapper getObjectMapper() {
		return new ObjectMapper().setAnnotationIntrospector(new JacksonLombokAnnotationIntrospector());
	}
}
