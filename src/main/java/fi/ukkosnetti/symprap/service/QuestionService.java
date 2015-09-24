package fi.ukkosnetti.symprap.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.ukkosnetti.symprap.conversion.LombokMapper;
import fi.ukkosnetti.symprap.dto.QuestionCreate;
import fi.ukkosnetti.symprap.dto.QuestionGet;
import fi.ukkosnetti.symprap.model.Question;
import fi.ukkosnetti.symprap.model.Symptom;
import fi.ukkosnetti.symprap.repository.QuestionRepository;
import fi.ukkosnetti.symprap.repository.SymptomRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository repository;
	
	@Autowired
	private SymptomRepository symptomRepository;
	
	@Autowired
	private LombokMapper mapper;
	
	public List<QuestionGet> getQuestions() {
		List<QuestionGet> questions = new ArrayList<>();
		repository.findAll().forEach(question -> questions.add(mapper.convertValue(question, QuestionGet.class)));
		return questions;
	}
	
	public List<QuestionGet> getQuestionsForSymptom(Long symptomId) {
		Symptom symptom = symptomRepository.findOne(symptomId);
		return repository.findBySymptom(symptom)
				.stream()
				.map(question -> mapper.convertValue(question, QuestionGet.class))
				.collect(Collectors.toList());
	}
	
	public QuestionGet createQuestion(QuestionCreate question) {
		Symptom symptom = symptomRepository.findOne(question.getSymptomId());
		Question entity = mapper.convertValue(question, Question.class);
		entity.setSymptom(symptom);
		entity = repository.save(entity);
		return mapper.convertValue(entity, QuestionGet.class);
	}
}
