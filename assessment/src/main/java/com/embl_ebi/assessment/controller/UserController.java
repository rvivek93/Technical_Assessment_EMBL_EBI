package com.embl_ebi.assessment.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.embl_ebi.assessment.domain.User;
import com.embl_ebi.assessment.service.helper.PersonServiceHelper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * 
 * JWT class for authenticating user.
 * 
 * 
 * @author Vivek Rajendran
 *
 */
@RestController
public class UserController {
	
	@Autowired
	private PersonServiceHelper serviceHelper;

	@PostMapping("user")
	public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		
		String token = serviceHelper.getJWTToken(username);
		User user = new User();
		user.setUsername(username);
		user.setToken(token);		
		return user;
		
	}

}
