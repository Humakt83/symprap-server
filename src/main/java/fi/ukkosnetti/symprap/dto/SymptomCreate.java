package fi.ukkosnetti.symprap.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public @Data class SymptomCreate {

	private final String symptom;

	public SymptomCreate(String symptom) {
		this.symptom = symptom;
	}
	
	@SuppressWarnings("unused")
	private SymptomCreate() {
		this(null);
	}
}
