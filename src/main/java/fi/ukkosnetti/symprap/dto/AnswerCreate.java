package fi.ukkosnetti.symprap.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public @Data class AnswerCreate {

	private final Long questionId;
	
	private final Long userId;
	
	private final String answer;
	
	private final boolean answerIsPrivate;

	public AnswerCreate(Long question, String answer, Long userId, boolean answerIsPrivate) {
		this.questionId = question;
		this.answer = answer;
		this.userId = userId;
		this.answerIsPrivate = answerIsPrivate;
	}
	
	@SuppressWarnings("unused")
	private AnswerCreate() {
		this(null, null, null, false);
	}
}
