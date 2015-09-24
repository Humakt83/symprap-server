package fi.ukkosnetti.symprap.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString 
@EqualsAndHashCode 
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private Long id;
	
	@Column(unique = true, updatable = false, nullable = false)
	@Getter
	@Setter
	private String userName;
	
	@Getter
	@Setter
	private String firstName;
	
	@Getter
	@Setter
	private String lastName;
	
	@Getter
	@Setter
	private Date dateOfBirth;
	
	@Column(unique = true, nullable = true)
	@Getter
	@Setter
	private Long medicalRecordNumber;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@Getter
	@Setter
	private Set<Symptom> symptoms;
	
	
}
