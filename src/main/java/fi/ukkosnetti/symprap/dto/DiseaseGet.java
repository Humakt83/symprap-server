package fi.ukkosnetti.symprap.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public @Data class DiseaseGet {

	private final Long id;
	
	private final String disease;
	
	public DiseaseGet(Long id, String disease) {
		this.id = id;
		this.disease = disease;
	}
	
	@SuppressWarnings("unused")
	private DiseaseGet() {
		this(null, null);
	}
}