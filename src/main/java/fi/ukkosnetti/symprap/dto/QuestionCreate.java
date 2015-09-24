package fi.ukkosnetti.symprap.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fi.ukkosnetti.symprap.model.AnswerType;

@JsonIgnoreProperties(ignoreUnknown=true)
public @Data class QuestionCreate {

	private final Long symptomId;
	
	private final String question;
	
	private final AnswerType answerType;
	
	public QuestionCreate(String question, AnswerType answerType, Long symptomId) {
		this.question = question;
		this.answerType = answerType;
		this.symptomId = symptomId;
	}
	
	@SuppressWarnings("unused")
	private QuestionCreate() {
		this(null, null, null);
	}
}
