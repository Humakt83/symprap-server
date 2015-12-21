package fi.ukkosnetti.symprap.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String question;
	
	@Enumerated(EnumType.STRING)
	private AnswerType answerType;
	
	private Date created;
	
	private Date updated;	
	
	@ManyToOne
	private Disease disease;
	
	@ElementCollection
	private Set<String> selectableAnswers;
	
	@PrePersist
	private void setCreated() {
		created = new Date();
	}
	
	@PreUpdate
	private void setUpdated() {
		updated = new Date();
	}
	
	@JsonProperty("diseaseId")
	public Long getDiseaseId() {
		return disease != null ? disease.getId() : null;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public AnswerType getAnswerType() {
		return answerType;
	}
	
	public void setAnswerType(AnswerType answerType) {
		this.answerType = answerType;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public Date getUpdated() {
		return updated;
	}
	
	public Disease getDisease() {
		return disease;
	}
	
	public void setDisease(Disease disease) {
		this.disease = disease;
	}
	
	public Set<String> getSelectableAnswers() {
		return selectableAnswers;
	}
	
	public void setSelectableAnswers(Set<String> selectableAnswers) {
		this.selectableAnswers = selectableAnswers;
	}
	
}
