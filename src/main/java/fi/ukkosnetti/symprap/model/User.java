package fi.ukkosnetti.symprap.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, updatable = false, nullable = false)
	private String userName;

	@Column(length = 100)
	private String password;

	private String firstName;

	private String lastName;

	private Date dateOfBirth;

	@Column(unique = true, nullable = true)
	private Long medicalRecordNumber;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Disease> diseases = new HashSet<>();

	@ElementCollection
	@Enumerated(EnumType.STRING)
	@Fetch(FetchMode.JOIN)
	private Set<UserRole> roles = new HashSet<>();

	@JoinTable(name = "followers", joinColumns = {
			@JoinColumn(name = "follower", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
			@JoinColumn(name = "followee", referencedColumnName = "id", nullable = false)})
	@ManyToMany
	@Fetch(FetchMode.JOIN)
	private Set<User> followerUsers = new HashSet<>();

	@ManyToMany(mappedBy="followerUsers")
	@Fetch(FetchMode.JOIN)
	private Set<User> followeeUsers = new HashSet<>();
	
	@JsonProperty("followers")
	public List<String> getFollowers() {
		return followerUsers.stream().map(user -> user.getUserName()).collect(Collectors.toList());
	}
	
	@JsonProperty("followees")
	public List<String> getFollowees() {
		return followeeUsers.stream().map(user -> user.getUserName()).collect(Collectors.toList());
	}

	public void addFollower(User follower) {
		followerUsers.add(follower);
		follower.followeeUsers.add(this);
	}

	public void removeFollower(User follower) {
		followerUsers.remove(follower);
		follower.followeeUsers.remove(this);
	}
	
	public Long getId() {
		return id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public Long getMedicalRecordNumber() {
		return medicalRecordNumber;
	}
	
	public void setMedicalRecordNumber(Long medicalRecordNumber) {
		this.medicalRecordNumber = medicalRecordNumber;
	}
	
	public Set<UserRole> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
	
	public Set<Disease> getDiseases() {
		return diseases;
	}
	
	public void setDiseases(Set<Disease> diseases) {
		this.diseases = diseases;
	}
	
	public Set<User> getFolloweeUsers() {
		return followeeUsers;
	}
	
	public void setFolloweeUsers(Set<User> followeeUsers) {
		this.followeeUsers = followeeUsers;
	}
	
	public Set<User> getFollowerUsers() {
		return followerUsers;
	}
	
	public void setFollowerUsers(Set<User> followerUsers) {
		this.followerUsers = followerUsers;
	}
	
}
