package fi.ukkosnetti.symprap.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fi.ukkosnetti.symprap.model.AnswerType;

@JsonIgnoreProperties(ignoreUnknown=true)
public @Data class QuestionGet {
	
	private final Long id;
	
	private final String question;
	
	private final AnswerType answerType;
	
	private final Long diseaseId;

}
