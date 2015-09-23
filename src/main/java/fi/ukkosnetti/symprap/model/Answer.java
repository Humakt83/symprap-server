package fi.ukkosnetti.symprap.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private Long id;
	
	@ManyToOne
	@Getter
	@Setter
	private Question question;
	
	@Getter
	@Setter
	private String answer;
	
	@Getter
	private Date created;
	
	@PrePersist
	private void setCreated() {
		created = new Date();
	}
}
