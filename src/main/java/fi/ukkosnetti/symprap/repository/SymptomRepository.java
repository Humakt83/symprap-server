package fi.ukkosnetti.symprap.repository;

import org.springframework.data.repository.CrudRepository;

import fi.ukkosnetti.symprap.model.Symptom;

public interface SymptomRepository extends CrudRepository<Symptom, Long> {

}
