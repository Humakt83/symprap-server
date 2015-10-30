package fi.ukkosnetti.symprap.controller;

import java.security.Principal;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.ukkosnetti.symprap.auth.AuthorizationVerifier;
import fi.ukkosnetti.symprap.dto.AnswerCreate;
import fi.ukkosnetti.symprap.dto.AnswerGet;
import fi.ukkosnetti.symprap.service.AnswerService;

@RestController
@RequestMapping(value = "/answer")
public class AnswerController {

	@Autowired
	private AnswerService service;
	
	@Autowired
	private AuthorizationVerifier authorizationVerifier;
	
	@RequestMapping(value = "/byquestion/{questionid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody List<AnswerGet> getAllAnswerForQuestion(@PathVariable("questionid") Long questionId) {
		return service.getAnswersForQuestion(questionId);
    }
	
	@RequestMapping(value = "/byuser/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody List<AnswerGet> getAllAnswersByUser(@PathVariable("username") String username, Principal principal) {
		authorizationVerifier.validateAuthorizationAsAUserOrFollower(username, principal);
		return service.getAnswersByUser(username);		
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
