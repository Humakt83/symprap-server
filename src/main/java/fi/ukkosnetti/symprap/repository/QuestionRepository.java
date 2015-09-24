package fi.ukkosnetti.symprap.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fi.ukkosnetti.symprap.model.Question;
import fi.ukkosnetti.symprap.model.Symptom;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

	List<Question> findBySymptom(Symptom symptom);
}
