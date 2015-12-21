package fi.ukkosnetti.symprap.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import fi.ukkosnetti.symprap.dto.AnswerCreate;
import fi.ukkosnetti.symprap.dto.AnswerGet;
import fi.ukkosnetti.symprap.model.Answer;
import fi.ukkosnetti.symprap.model.AnswerType;
import fi.ukkosnetti.symprap.model.Question;
import fi.ukkosnetti.symprap.model.User;
import fi.ukkosnetti.symprap.repository.AnswerRepository;
import fi.ukkosnetti.symprap.repository.QuestionRepository;
import fi.ukkosnetti.symprap.repository.UserRepository;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository repository;

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ObjectMapper mapper;

	public List<AnswerGet> getAnswersForQuestion(long questionId) {
		Question question = questionRepository.findOne(questionId);
		return repository.findByQuestion(question)
				.stream()
				.map(answer -> mapper.convertValue(answer, AnswerGet.class))
				.collect(Collectors.toList());
	}
	
	public List<AnswerGet> getAnswersByUser(String username, boolean includePrivate) {
		User user = userRepository.getUserByUserName(username);
		return repository.findByUser(user)
				.stream()
				.filter(answer -> !answer.isAnswerIsPrivate() || includePrivate)
				.map(answer -> mapper.convertValue(answer, AnswerGet.class))
				.collect(Collectors.toList());
	}

	public void createAnswer(AnswerCreate answer) {
		Question question = questionRepository.findOne(answer.questionId);
		verifyAnswer(question.getAnswerType(), answer.answer);
		Answer entity = new Answer();
		entity.setQuestion(question);
		entity.setAnswer(answer.answer);
		entity.setAnswerIsPrivate(answer.answerIsPrivate);
		setUser(userRepository.findOne(answer.userId), entity);
		repository.save(entity);
	}

	private void setUser(User user, Answer entity) {
		if (user == null) throw new NullPointerException("User must exist");
		entity.setUser(user);
	}

	private void verifyAnswer(AnswerType answerType, String answer) {
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
