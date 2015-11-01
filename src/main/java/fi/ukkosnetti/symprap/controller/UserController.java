package fi.ukkosnetti.symprap.controller;

import java.security.Principal;
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
import fi.ukkosnetti.symprap.dto.UserCreate;
import fi.ukkosnetti.symprap.dto.UserGet;
import fi.ukkosnetti.symprap.dto.UserUpdate;
import fi.ukkosnetti.symprap.model.UserRole;
import fi.ukkosnetti.symprap.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@PreAuthorize(Authorities.ADMIN)
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody UserGet create(@RequestBody UserCreate user) {
		return service.createUser(user);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody UserGet register(@RequestBody UserCreate user) {
		if (user.getRoles().contains(UserRole.ADMIN)) {
			throw new IllegalArgumentException("Admin creation is not allowed");
		}
		return service.createUser(user);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody UserGet update(@RequestBody UserUpdate user) {
		return service.updateUser(user);
	}
	
	@PreAuthorize(Authorities.ADMIN)
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody List<UserGet> allUsers() {
		return service.getUsers();
	}
	
	@PreAuthorize(Authorities.ADMIN)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody UserGet getUser(@PathVariable("id") Long id) {
		return service.getUser(id);
	}
	
	@RequestMapping(value = "/byusername/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody UserGet getUserByUserName(@PathVariable("username") String username) {
		return service.getUserByUserName(username);
	}
	
	@PreAuthorize(Authorities.TEEN)
	@RequestMapping(value = "/follower/add/{username}", method = RequestMethod.PUT)
	public void addFollower(@PathVariable("username") String username, Principal principal) {
		String principalName = principal.getName();
		if (principalName.equals(username)) {
			throw new IllegalArgumentException("Cannot add self as a follower");
		}
		service.addFollower(principalName, username);
	}
	
	@PreAuthorize(Authorities.TEEN)
	@RequestMapping(value = "/follower/remove/{username}", method = RequestMethod.PUT)
	public void removeFollower(@PathVariable("username") String username, Principal principal) {
		service.removeFollower(principal.getName(), username);
	}
	
}
