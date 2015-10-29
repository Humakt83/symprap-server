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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	
}
