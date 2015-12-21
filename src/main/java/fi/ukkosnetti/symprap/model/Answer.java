package fi.ukkosnetti.symprap.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Question question;
	
	private String answer;
	
	private Date created;
	
	@ManyToOne
	private User user;
	
	private boolean answerIsPrivate;
	
	@PrePersist
	private void setCreated() {
		created = new Date();
	}
	
	@JsonProperty("questionId")
	public Long getQuestionId() {
		return question != null ? question.getId() : null;
	}
	
	public Long getId() {
		return id;
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean isAnswerIsPrivate() {
		return answerIsPrivate;
	}
	
	public void setAnswerIsPrivate(boolean answerIsPrivate) {
		this.answerIsPrivate = answerIsPrivate;
	}
}
