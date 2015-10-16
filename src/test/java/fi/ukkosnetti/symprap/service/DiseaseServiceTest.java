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
import fi.ukkosnetti.symprap.dto.DiseaseCreate;
import fi.ukkosnetti.symprap.dto.DiseaseGet;
import fi.ukkosnetti.symprap.model.Disease;
import fi.ukkosnetti.symprap.repository.DiseaseRepository;

@RunWith(MockitoJUnitRunner.class)
public class DiseaseServiceTest {

	@Mock
	private DiseaseRepository repo;
	
	@Mock
	private LombokMapper mapper;
	
	@InjectMocks
	private DiseaseService service;
	
	@Test
	public void returnsDisease() {
		final Long id = 513l;
		when(mapper.convertValue(any(), eq(DiseaseGet.class))).thenReturn(new DiseaseGet(id, "diabetes"));
		when(repo.findOne(id)).thenReturn(new Disease());
		DiseaseGet disease = service.getDisease(id);
		assertNotNull(disease);
		assertEquals(id, disease.getId());
	}
	
	@Test
	public void createsAndReturnsDisease() {
		when(mapper.convertValue(any(), eq(DiseaseGet.class))).thenReturn(new DiseaseGet(5l, "diabetes"));
		when(repo.save(any(Disease.class))).thenReturn(new Disease());
		DiseaseGet disease = service.createDisease(new DiseaseCreate("diabeters"));
		assertNotNull(disease);
	}

}
