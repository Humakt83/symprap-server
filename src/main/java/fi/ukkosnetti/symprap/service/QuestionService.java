package fi.ukkosnetti.symprap.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import fi.ukkosnetti.symprap.dto.QuestionCreate;
import fi.ukkosnetti.symprap.dto.QuestionGet;
import fi.ukkosnetti.symprap.dto.QuestionUpdate;
import fi.ukkosnetti.symprap.model.Disease;
import fi.ukkosnetti.symprap.model.Question;
import fi.ukkosnetti.symprap.repository.DiseaseRepository;
import fi.ukkosnetti.symprap.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository repository;
	
	@Autowired
	private DiseaseRepository diseaseRepository;
	
	@Autowired
	private ObjectMapper mapper;
	
	public List<QuestionGet> getQuestions() {
		List<QuestionGet> questions = new ArrayList<>();
		repository.findAll().forEach(question -> questions.add(mapper.convertValue(question, QuestionGet.class)));
		return questions;
	}
	
	public List<QuestionGet> getQuestionsForDisease(Long diseaseId) {
		Disease disease = diseaseRepository.findOne(diseaseId);
		return repository.findByDisease(disease)
				.stream()
				.map(question -> mapper.convertValue(question, QuestionGet.class))
				.collect(Collectors.toList());
	}
	
	public QuestionGet getQuestion(Long id) {
		Question question = repository.findOne(id);
		return mapper.convertValue(question, QuestionGet.class);
	}
	
	public QuestionGet createQuestion(QuestionCreate question) {
		Disease disease = diseaseRepository.findOne(question.diseaseId);
		Question entity = mapper.convertValue(question, Question.class);
		entity.setDisease(disease);
		entity = repository.save(entity);
		return mapper.convertValue(entity, QuestionGet.class);
	}
	
	public QuestionGet updateQuestion(QuestionUpdate question) {
		Disease disease = diseaseRepository.findOne(question.diseaseId);
		Question entity = mapper.convertValue(question, Question.class);
		entity.setDisease(disease);
		entity = repository.save(entity);
		return mapper.convertValue(entity, QuestionGet.class);
	}
}
