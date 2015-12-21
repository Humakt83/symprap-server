package fi.ukkosnetti.symprap.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fi.ukkosnetti.symprap.model.AnswerType;

@JsonIgnoreProperties(ignoreUnknown=true)
public class QuestionGet {
	
	public final Long id;
	
	public final String question;
	
	public final AnswerType answerType;
	
	public final Long diseaseId;

	public QuestionGet(Long id, String question, AnswerType answerType, Long diseaseId) {
		this.id = id;
		this.question = question;
		this.answerType = answerType;
		this.diseaseId = diseaseId;
	}
	
	@SuppressWarnings("unused")
	private QuestionGet() {
		this(null, null, null, null);
	}

}
