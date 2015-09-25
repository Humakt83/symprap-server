package fi.ukkosnetti.symprap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.ukkosnetti.symprap.conversion.LombokMapper;
import fi.ukkosnetti.symprap.dto.UserCreate;
import fi.ukkosnetti.symprap.dto.UserGet;
import fi.ukkosnetti.symprap.model.User;
import fi.ukkosnetti.symprap.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private LombokMapper mapper;
	
	public UserGet createUser(UserCreate user) {
		User entity = repository.save(mapper.convertValue(user, User.class));
		return mapper.convertValue(entity, UserGet.class);
	}
	
}
