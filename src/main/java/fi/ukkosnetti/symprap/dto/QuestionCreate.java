package fi.ukkosnetti.symprap.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fi.ukkosnetti.symprap.model.AnswerType;

@JsonIgnoreProperties(ignoreUnknown=true)
public class QuestionCreate {

	public final Long diseaseId;
	
	public final String question;
	
	public final AnswerType answerType;
	
	public QuestionCreate(String question, AnswerType answerType, Long diseaseId) {
		this.question = question;
		this.answerType = answerType;
		this.diseaseId = diseaseId;
	}
	
	@SuppressWarnings("unused")
	private QuestionCreate() {
		this(null, null, null);
	}
}
