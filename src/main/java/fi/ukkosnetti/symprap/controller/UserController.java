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

import fi.ukkosnetti.symprap.dto.UserCreate;
import fi.ukkosnetti.symprap.dto.UserGet;
import fi.ukkosnetti.symprap.dto.UserUpdate;
import fi.ukkosnetti.symprap.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody UserGet create(@RequestBody UserCreate user) {
		return service.createUser(user);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody UserGet update(@RequestBody UserUpdate user) {
		return service.updateUser(user);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody List<UserGet> allUsers() {
		return service.getUsers();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody UserGet getUser(@PathVariable("id") Long id) {
		return service.getUser(id);
	}
	
	@RequestMapping(value = "/byusername/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody UserGet getUserByUserName(@PathVariable("username") String userName) {
		//TODO: Implement proper login once OAUTH2 is in place"
		return service.getUserByUserName(userName);
	}
	
}
