package fi.ukkosnetti.symprap.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fi.ukkosnetti.symprap.conversion.LombokMapper;
import fi.ukkosnetti.symprap.dto.QuestionCreate;
import fi.ukkosnetti.symprap.dto.QuestionGet;
import fi.ukkosnetti.symprap.model.AnswerType;
import fi.ukkosnetti.symprap.model.Disease;
import fi.ukkosnetti.symprap.model.Question;
import fi.ukkosnetti.symprap.repository.DiseaseRepository;
import fi.ukkosnetti.symprap.repository.QuestionRepository;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

	@Mock
	private QuestionRepository repo;
	
	@Mock
	private DiseaseRepository diseaseRepo;
	
	@Mock
	private LombokMapper mapper;
	
	@InjectMocks
	private QuestionService service;
	
	@Before
	public void init() {
		when(diseaseRepo.findOne(any())).thenReturn(new Disease());
	}
	
	@Test
	public void returnsAllQuestions() {
		when(repo.findAll()).thenReturn(Arrays.asList(new Question(), new Question()));
		List<QuestionGet> questions = service.getQuestions();
		assertEquals(2, questions.size());
	}
	
	@Test
	public void returnsQuestionsForDisease() {
		when(repo.findByDisease(isA(Disease.class))).thenReturn(Arrays.asList(new Question(), new Question(), new Question()));
		List<QuestionGet> questions = service.getQuestionsForDisease(53l);
		assertEquals(3, questions.size());
	}
	
	@Test
	public void createsQuestion() {
		when(mapper.convertValue(any(), eq(Question.class))).thenReturn(new Question());
		service.createQuestion(new QuestionCreate("who are you?", AnswerType.TEXT, 5l));
		verify(repo).save(isA(Question.class));
	}
	
}
