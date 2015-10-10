package fi.ukkosnetti.symprap.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Symptom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private Long id;
	
	@Column(unique = true, nullable = false)
	@Getter
	@Setter
	private String symptom;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "symptom", fetch = FetchType.LAZY)
	@Getter
	@Setter
	@JsonIgnore
	private Set<Question> questions;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "symptoms")
	@Getter
	@Setter
	@JsonIgnore
	private Set<User> users;
}
