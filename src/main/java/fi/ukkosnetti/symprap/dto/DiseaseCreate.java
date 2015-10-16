package fi.ukkosnetti.symprap.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public @Data class DiseaseCreate {

	private final String disease;

	public DiseaseCreate(String disease) {
		this.disease = disease;
	}
	
	@SuppressWarnings("unused")
	private DiseaseCreate() {
		this(null);
	}
}
