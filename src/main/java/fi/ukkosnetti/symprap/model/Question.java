package fi.ukkosnetti.symprap.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString 
@EqualsAndHashCode 
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private Long id;
	
	@Getter
	@Setter
	private String question;
	
	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	private AnswerType answerType;
	
	@Getter
	private Date created;
	
	@Getter
	private Date updated;	
	
	@PrePersist
	private void setCreated() {
		created = new Date();
	}
	
	@PreUpdate
	private void setUpdated() {
		updated = new Date();
	}
	
}
