package ar.edu.iua.iw3.backend.util.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.iua.iw3.backend.auth.User;
import ar.edu.iua.iw3.backend.util.Constants;



public class BaseRestController {
	protected User getUserLogged() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		return user;
	}
}
