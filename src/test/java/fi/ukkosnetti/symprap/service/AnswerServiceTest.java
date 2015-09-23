package fi.ukkosnetti.symprap.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fi.ukkosnetti.symprap.conversion.LombokMapper;
import fi.ukkosnetti.symprap.dto.AnswerCreate;
import fi.ukkosnetti.symprap.dto.AnswerGet;
import fi.ukkosnetti.symprap.model.Answer;
import fi.ukkosnetti.symprap.model.AnswerType;
import fi.ukkosnetti.symprap.model.Question;
import fi.ukkosnetti.symprap.repository.AnswerRepository;
import fi.ukkosnetti.symprap.repository.QuestionRepository;

@RunWith(MockitoJUnitRunner.class)
public class AnswerServiceTest {

	@Mock
	private AnswerRepository repository;
	
	@Mock
	private QuestionRepository questionRepository;
	
	@Mock
	private LombokMapper mapper;
	
	@InjectMocks
	private AnswerService service;
	
	@Test
	public void returnsAllAnswersForQuestion() {
		final Long questionId = 52341l;
		when(questionRepository.findOne(questionId)).thenReturn(new Question());
		when(mapper.convertValue(any(), eq(AnswerGet.class))).thenReturn(Mockito.mock(AnswerGet.class));
		when(repository.findByQuestion(any(Question.class))).thenReturn(Arrays.asList(new Answer(), new Answer()));
		List<AnswerGet> answers = service.getAnswersForQuestion(5l);
		assertEquals(2, answers.size());
	}
	
	@Test(expected = NullPointerException.class)
	public void doesNotAllowNullAnswer() {
		service.createAnswer(givenAnswer(AnswerType.BOOLEAN, null));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void doesNotAcceptNonBooleanAnswerForAnswerTypeBoolean() {
		service.createAnswer(givenAnswer(AnswerType.BOOLEAN, "NOTABOOLEAN"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void doesNotAllowEmptyStringForAnswerTypeText() {
		service.createAnswer(givenAnswer(AnswerType.TEXT, ""));
	}
	
	@Test(expected = NumberFormatException.class)
	public void doesNotAllowNonNumericAnswerForAnswerTypeDouble() {
		service.createAnswer(givenAnswer(AnswerType.DOUBLE, "NOTANUMBER"));
	}
	
	@Test
	public void createsBooleanAnswerForTrue() {
		service.createAnswer(givenAnswer(AnswerType.BOOLEAN, "TRUE"));
		verify(repository).save(isA(Answer.class));
	}
	
	@Test
	public void createsBooleanAnswerForFalse() {
		service.createAnswer(givenAnswer(AnswerType.BOOLEAN, "false"));
		verify(repository).save(isA(Answer.class));
	}
	
	@Test
	public void createsTextAnswer() {
		service.createAnswer(givenAnswer(AnswerType.TEXT, "A very nice answer"));
		verify(repository).save(isA(Answer.class));
	}
	
	@Test
	public void createsAnswerOfTypeDouble() {
		service.createAnswer(givenAnswer(AnswerType.DOUBLE, "12.34"));
		verify(repository).save(isA(Answer.class));
	}
	
	private AnswerCreate givenAnswer(AnswerType answerType, String answer) {
		Question question = new Question();
		question.setAnswerType(answerType);
		when(questionRepository.findOne(any())).thenReturn(question);
		return new AnswerCreate(1l, answer);
	}
	
	
}
