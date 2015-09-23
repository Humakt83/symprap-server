package fi.ukkosnetti.symprap.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public @Data class AnswerCreate {

	private final Long questionId;
	
	private final String answer;

	public AnswerCreate(Long question, String answer) {
		this.questionId = question;
		this.answer = answer;
	}
	
	@SuppressWarnings("unused")
	private AnswerCreate() {
		this(null, null);
	}
}
