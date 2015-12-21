package fi.ukkosnetti.symprap.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AnswerGet {
	
	public final String answer;
	
	public final Long created;
	
	public final Long questionId;

	public AnswerGet(String answer, Long created, Long questionId) {
		this.answer = answer;
		this.created = created;
		this.questionId = questionId;
	}

	@SuppressWarnings("unused")
	private AnswerGet() {
		this(null, null, null);
	}
		
}
