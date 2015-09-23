package fi.ukkosnetti.symprap.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.NonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.ukkosnetti.symprap.conversion.LombokMapper;
import fi.ukkosnetti.symprap.dto.AnswerCreate;
import fi.ukkosnetti.symprap.dto.AnswerGet;
import fi.ukkosnetti.symprap.model.Answer;
import fi.ukkosnetti.symprap.model.AnswerType;
import fi.ukkosnetti.symprap.model.Question;
import fi.ukkosnetti.symprap.repository.AnswerRepository;
import fi.ukkosnetti.symprap.repository.QuestionRepository;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository repository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private LombokMapper mapper;

	public List<AnswerGet> getAnswersForQuestion(long questionId) {
		Question question = questionRepository.findOne(questionId);
		return repository.findByQuestion(question)
				.stream()
				.map(answer -> mapper.convertValue(answer, AnswerGet.class))
				.collect(Collectors.toList());
	}

	public void createAnswer(AnswerCreate answer) {
		Question question = questionRepository.findOne(answer.getQuestionId());
		verifyAnswer(question.getAnswerType(), answer.getAnswer());
		Answer entity = new Answer();
		entity.setQuestion(question);
		entity.setAnswer(answer.getAnswer());
		repository.save(entity);
	}

	private void verifyAnswer(@NonNull AnswerType answerType, @NonNull String answer) {
		switch (answerType) {
		case BOOLEAN:
			if (!(answer.equalsIgnoreCase(Boolean.FALSE.toString()) || answer.equalsIgnoreCase(Boolean.TRUE.toString()))) {
				throw new IllegalArgumentException(String.format("Answer of type %s must be %s or %s", AnswerType.BOOLEAN, Boolean.TRUE, Boolean.FALSE));
			}
			break;
		case DOUBLE:
			Double.parseDouble(answer);
			break;
		case TEXT:
			if (answer.isEmpty()) throw new IllegalArgumentException("Answer cannot be empty");
			break;
		default:
			throw new IllegalArgumentException("Unsupported answer type: " + answerType);
		}
	}


}
