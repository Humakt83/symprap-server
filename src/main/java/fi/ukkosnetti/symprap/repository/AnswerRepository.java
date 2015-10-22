package fi.ukkosnetti.symprap.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.ukkosnetti.symprap.model.Answer;
import fi.ukkosnetti.symprap.model.Question;
import fi.ukkosnetti.symprap.model.User;

public interface AnswerRepository extends CrudRepository<Answer, Long> {

	List<Answer> findByQuestion(Question question);
	
	List<Answer> findByUser(User user);
}
