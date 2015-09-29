package fi.ukkosnetti.symprap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.ukkosnetti.symprap.conversion.LombokMapper;
import fi.ukkosnetti.symprap.dto.UserCreate;
import fi.ukkosnetti.symprap.dto.UserGet;
import fi.ukkosnetti.symprap.model.User;
import fi.ukkosnetti.symprap.repository.SymptomRepository;
import fi.ukkosnetti.symprap.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private SymptomRepository symptomRepository;
	
	@Autowired
	private LombokMapper mapper;
	
	public UserGet createUser(UserCreate user) {
		User entity = repository.save(mapper.convertValue(user, User.class));
		return mapper.convertValue(entity, UserGet.class);
	}
	
	public List<UserGet> getUsers() {
		List<UserGet> users = new ArrayList<>();
		repository.findAll().forEach(user -> users.add(mapper.convertValue(user, UserGet.class)));
		return users;
	}

	//Most likely a temporary method
	public UserGet getUser(String userName) {
		User user = repository.getUserByUserName(userName);
		return mapper.convertValue(user, UserGet.class);
	}
	
}
