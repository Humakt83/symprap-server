package fi.ukkosnetti.symprap.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fi.ukkosnetti.symprap.conversion.LombokMapper;
import fi.ukkosnetti.symprap.dto.SymptomCreate;
import fi.ukkosnetti.symprap.dto.SymptomGet;
import fi.ukkosnetti.symprap.model.Symptom;
import fi.ukkosnetti.symprap.repository.SymptomRepository;

@RunWith(MockitoJUnitRunner.class)
public class SymptomServiceTest {

	@Mock
	private SymptomRepository repo;
	
	@Mock
	private LombokMapper mapper;
	
	@InjectMocks
	private SymptomService service;
	
	@Test
	public void returnsSymptom() {
		final Long id = 513l;
		when(mapper.convertValue(any(), eq(SymptomGet.class))).thenReturn(new SymptomGet(id, "diabetes"));
		when(repo.findOne(id)).thenReturn(new Symptom());
		SymptomGet symptom = service.getSymptom(id);
		assertNotNull(symptom);
		assertEquals(id, symptom.getId());
	}
	
	@Test
	public void createsAndReturnsSymptom() {
		when(mapper.convertValue(any(), eq(SymptomGet.class))).thenReturn(new SymptomGet(5l, "diabetes"));
		when(repo.save(any(Symptom.class))).thenReturn(new Symptom());
		SymptomGet symptom = service.createSymptom(new SymptomCreate("diabeters"));
		assertNotNull(symptom);
	}

}
