package fi.ukkosnetti.symprap.dto;

import java.util.Date;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public @Data class AnswerGet {
	
	private final String answer;
	
	private final Date created;
	
}
