package fi.ukkosnetti.symprap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.ukkosnetti.symprap.conversion.LombokMapper;
import fi.ukkosnetti.symprap.dto.DiseaseCreate;
import fi.ukkosnetti.symprap.dto.DiseaseGet;
import fi.ukkosnetti.symprap.dto.DiseaseUpdate;
import fi.ukkosnetti.symprap.model.Disease;
import fi.ukkosnetti.symprap.repository.DiseaseRepository;

@Service
public class DiseaseService {

	@Autowired
	private DiseaseRepository repository;
	
	@Autowired
	private LombokMapper mapper;

	public DiseaseGet getDisease(Long id) {
		return mapper.convertValue(repository.findOne(id), DiseaseGet.class);
	}
	
	public DiseaseGet createDisease(DiseaseCreate disease) {
		Disease entity = repository.save(mapper.convertValue(disease, Disease.class));
		return mapper.convertValue(entity, DiseaseGet.class);
	}
	
	public DiseaseGet updateDisease(DiseaseUpdate disease) {
		Disease entity = repository.save(mapper.convertValue(disease, Disease.class));
		return mapper.convertValue(entity, DiseaseGet.class);
	}

	public List<DiseaseGet> getDiseases() {
		List<DiseaseGet> diseases = new ArrayList<>();
		repository.findAll().forEach(disease -> diseases.add(mapper.convertValue(disease, DiseaseGet.class)));
		return diseases;
	}
}
