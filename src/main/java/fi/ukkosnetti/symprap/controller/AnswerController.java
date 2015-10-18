package fi.ukkosnetti.symprap.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.ukkosnetti.symprap.dto.AnswerCreate;
import fi.ukkosnetti.symprap.dto.AnswerGet;
import fi.ukkosnetti.symprap.service.AnswerService;

@RestController
@RequestMapping(value = "/answer")
public class AnswerController {

	@Autowired
	private AnswerService service;
	
	@RequestMapping(value = "/byquestion/{questionid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody List<AnswerGet> getAllAnswerForQuestion(@PathVariable("questionid") Long questionId) {
		return service.getAnswersForQuestion(questionId);
    }
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public void create(@RequestBody AnswerCreate answer) {
		service.createAnswer(answer);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public void create(@RequestBody List<AnswerCreate> answers) {
		answers.forEach(service::createAnswer);
	}
}
