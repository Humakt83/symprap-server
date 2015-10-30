package fi.ukkosnetti.symprap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import fi.ukkosnetti.symprap.auth.SymprapUserDetails;
import fi.ukkosnetti.symprap.conversion.LombokMapper;
import fi.ukkosnetti.symprap.dto.UserCreate;
import fi.ukkosnetti.symprap.dto.UserGet;
import fi.ukkosnetti.symprap.dto.UserUpdate;
import fi.ukkosnetti.symprap.model.User;
import fi.ukkosnetti.symprap.repository.DiseaseRepository;
import fi.ukkosnetti.symprap.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private DiseaseRepository diseaseRepository;
	
	@Autowired
	private LombokMapper mapper;
	
	public UserGet createUser(UserCreate user) {
		User entity = mapper.convertValue(user, User.class);
		entity.setPassword(hashAndSaltPass(user));
		repository.save(entity);
		return mapper.convertValue(entity, UserGet.class);
	}

	public UserGet updateUser(UserUpdate user) {
		User entity = repository.save(mapper.convertValue(user, User.class));
		entity.setPassword(hashAndSaltPass(user));
		return mapper.convertValue(entity, UserGet.class);
	}
	
	public List<UserGet> getUsers() {
		List<UserGet> users = new ArrayList<>();
		repository.findAll().forEach(user -> users.add(mapper.convertValue(user, UserGet.class)));
		return users;
	}
	
	public UserGet getUser(Long id) {
		User user = repository.findOne(id);
		return mapper.convertValue(user, UserGet.class);
	}
	
	public UserGet getUserByUserName(String userName) {
		User user = repository.getUserByUserName(userName);
		return mapper.convertValue(user, UserGet.class);
	}
	
	public void addFollower(String username, String follower) {
		User followerUser = repository.getUserByUserName(follower);
		if (followerUser == null) {
			throw new IllegalArgumentException("User " + follower + " does not exist");
		}
		User user = repository.getUserByUserName(username);
		user.addFollower(followerUser);
		repository.save(user);
		repository.save(followerUser);
	}

	public void removeFollower(String username, String follower) {
		User user = repository.getUserByUserName(username);
		User followerUser = repository.getUserByUserName(follower);
		if (!user.getFollowers().contains(follower)) {
			throw new IllegalArgumentException(String.format("User %s does not have follower %s", username, follower));
		}
		user.removeFollower(followerUser);
		repository.save(user);
		repository.save(followerUser);
	}
	
	public boolean isFollower(String username, String follower) {
		return repository.getUserByUserName(username).getFollowers().contains(follower);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.getUserByUserName(username);
		return new SymprapUserDetails(user.getUserName(), user.getPassword(), user.getRoles());
	}
	
	private String hashAndSaltPass(UserCreate user) {
		return BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
	}
	
}
