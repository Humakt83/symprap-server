package fi.ukkosnetti.symprap.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DiseaseGet {

	public final Long id;
	
	public final String disease;
	
	public DiseaseGet(Long id, String disease) {
		this.id = id;
		this.disease = disease;
	}
	
	@SuppressWarnings("unused")
	private DiseaseGet() {
		this(null, null);
	}
}