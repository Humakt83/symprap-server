package fi.ukkosnetti.symprap.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DiseaseCreate {

	public final String disease;

	public DiseaseCreate(String disease) {
		this.disease = disease;
	}
	
	@SuppressWarnings("unused")
	private DiseaseCreate() {
		this(null);
	}
}
