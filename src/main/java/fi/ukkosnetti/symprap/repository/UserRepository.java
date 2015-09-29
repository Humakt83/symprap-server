package fi.ukkosnetti.symprap.repository;

import org.springframework.data.repository.CrudRepository;

import fi.ukkosnetti.symprap.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User getUserByUserName(String userName);
}
