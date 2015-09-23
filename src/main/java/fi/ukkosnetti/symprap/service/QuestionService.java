package fi.ukkosnetti.symprap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.ukkosnetti.symprap.conversion.LombokMapper;
import fi.ukkosnetti.symprap.dto.QuestionCreate;
import fi.ukkosnetti.symprap.dto.QuestionGet;
import fi.ukkosnetti.symprap.model.Question;
import fi.ukkosnetti.symprap.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository repository;
	
	@Autowired
	private LombokMapper mapper;
	
	public List<QuestionGet> getQuestions() {
		List<QuestionGet> questions = new ArrayList<>();
		repository.findAll().forEach(question -> questions.add(mapper.convertValue(question, QuestionGet.class)));
		return questions;
	}
	
	public QuestionGet createQuestion(QuestionCreate question) {
		Question entity = repository.save(mapper.convertValue(question, Question.class));
		return mapper.convertValue(entity, QuestionGet.class);
	}
}
