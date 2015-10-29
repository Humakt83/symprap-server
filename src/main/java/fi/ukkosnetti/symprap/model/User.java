package fi.ukkosnetti.symprap.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@ToString 
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private Long id;
	
	@Column(unique = true, updatable = false, nullable = false)
	@Getter
	@Setter
	private String userName;
	
	@Column(length = 100)
	@Getter
	@Setter
	private String password;
	
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
	private Set<Disease> diseases = new HashSet<>();
	
	@ElementCollection
	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	@Fetch(FetchMode.JOIN)
	private Set<UserRole> roles = new HashSet<>();
	
	@ElementCollection
	@Getter
	@Setter
	@Fetch(FetchMode.JOIN)
	private Set<String> followers = new HashSet<>();
	
	public void addFollower(String follower) {
		followers.add(follower);
	}
}
