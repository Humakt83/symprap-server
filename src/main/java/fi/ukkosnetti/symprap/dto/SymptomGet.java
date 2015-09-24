package fi.ukkosnetti.symprap.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public @Data class SymptomGet {

	private final Long id;
	
	private final String symptom;
	
	public SymptomGet(Long id, String symptom) {
		this.id = id;
		this.symptom = symptom;
	}
	
	@SuppressWarnings("unused")
	private SymptomGet() {
		this(null, null);
	}
}