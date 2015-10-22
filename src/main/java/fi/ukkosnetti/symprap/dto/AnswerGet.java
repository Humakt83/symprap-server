package fi.ukkosnetti.symprap.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public @Data class AnswerGet {
	
	private final String answer;
	
	private final Long created;
	
	private final Long questionId;
	
}
