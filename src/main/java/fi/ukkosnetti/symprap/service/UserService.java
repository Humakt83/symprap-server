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
import fi.ukkosnetti.symprap.repository.SymptomRepository;
import fi.ukkosnetti.symprap.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private SymptomRepository symptomRepository;
	
	@Autowired
	private LombokMapper mapper;
	
	public UserGet createUser(UserCreate user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		User entity = mapper.convertValue(user, User.class);
		entity.setPassword(hashed);
		repository.save(entity);
		return mapper.convertValue(entity, UserGet.class);
	}
	
	public UserGet updateUser(UserUpdate user) {
		User entity = repository.save(mapper.convertValue(user, User.class));
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
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.getUserByUserName(username);
		return new SymprapUserDetails(user.getUserName(), user.getPassword(), user.getRoles());
	}
	
}
