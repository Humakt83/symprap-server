package fi.ukkosnetti.symprap.auth;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Component;

import fi.ukkosnetti.symprap.service.UserService;

@Component
public class AuthorizationVerifier {
	
	@Autowired
	private UserService service;
	
	public void validateAuthorizationAsAUserOrFollower(String username, Principal principal) {
		if (!principal.getName().equals(username) && !service.isFollower(username, principal.getName())) {
			throw new UnauthorizedUserException("You are neither a follower of or logged in as user " + username);
		}
	}
}
