package fi.ukkosnetti.symprap.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AnswerCreate {

	public final Long questionId;
	
	public final Long userId;
	
	public final String answer;
	
	public final boolean answerIsPrivate;

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
