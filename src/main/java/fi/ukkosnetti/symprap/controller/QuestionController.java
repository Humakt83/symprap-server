package fi.ukkosnetti.symprap.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.ukkosnetti.symprap.auth.Authorities;
import fi.ukkosnetti.symprap.dto.QuestionCreate;
import fi.ukkosnetti.symprap.dto.QuestionGet;
import fi.ukkosnetti.symprap.dto.QuestionUpdate;
import fi.ukkosnetti.symprap.service.QuestionService;

@RestController
@RequestMapping(value = "/question")
public class QuestionController {

	@Autowired
	private QuestionService service;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody List<QuestionGet> getAll() {
		return service.getQuestions();
    }
	
	@RequestMapping(value = "/fordisease/{diseaseid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody List<QuestionGet> getAllForDisease(@PathVariable("diseaseid") Long diseaseId) {
		return service.getQuestionsForDisease(diseaseId);
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody QuestionGet getQuestion(@PathVariable("id") Long id) {
		return service.getQuestion(id);
	}
	
	@PreAuthorize(Authorities.ADMIN)
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody QuestionGet update(@RequestBody QuestionUpdate question) {
		return service.updateQuestion(question);
	}
	
	@PreAuthorize(Authorities.ADMIN)
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody QuestionGet create(@RequestBody QuestionCreate question) {
		return service.createQuestion(question);
	}
}
