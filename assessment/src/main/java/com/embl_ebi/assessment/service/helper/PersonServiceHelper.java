package com.embl_ebi.assessment.service.helper;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.embl_ebi.assessment.domain.PersonDetails;
import com.embl_ebi.assessment.model.Person;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * Helper class for data transformation.
 * 
 * 
 * @author Vivek Rajendran
 *
 */
@Component
public class PersonServiceHelper {

	public PersonDetails convertToPersonDomain(Person person) {

		return PersonDetails.builder().first_name(person.getFirst_name()).last_name(person.getLast_name())
				.age(person.getAge()).favourite_colour(person.getFavourite_colour()).build();

	}

	public Person convertToPersonEntity(PersonDetails personDetails) {

		return Person.builder().first_name(personDetails.getFirst_name()).last_name(personDetails.getLast_name())
				.age(personDetails.getAge()).favourite_colour(personDetails.getFavourite_colour()).build();
	}

	public String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("embl_ebi_JWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

}
