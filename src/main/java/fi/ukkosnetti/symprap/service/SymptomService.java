package fi.ukkosnetti.symprap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.ukkosnetti.symprap.conversion.LombokMapper;
import fi.ukkosnetti.symprap.dto.SymptomCreate;
import fi.ukkosnetti.symprap.dto.SymptomGet;
import fi.ukkosnetti.symprap.model.Symptom;
import fi.ukkosnetti.symprap.repository.SymptomRepository;

@Service
public class SymptomService {

	@Autowired
	private SymptomRepository repository;
	
	@Autowired
	private LombokMapper mapper;

	public SymptomGet getSymptom(Long id) {
		return mapper.convertValue(repository.findOne(id), SymptomGet.class);
	}
	
	public SymptomGet createSymptom(SymptomCreate symptom) {
		Symptom entity = repository.save(mapper.convertValue(symptom, Symptom.class));
		return mapper.convertValue(entity, SymptomGet.class);
	}
}
