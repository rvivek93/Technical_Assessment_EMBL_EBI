package com.embl_ebi.assessment.service;

import org.springframework.http.ResponseEntity;

import com.embl_ebi.assessment.domain.PersonsDTO;


/**
 * @author Vivek Rajendran
 *
 */

public interface PersonService {
	
	public PersonsDTO fetchAllPersonDetails();
	
	public ResponseEntity<Object> addPerson(PersonsDTO personData);
	
	public ResponseEntity<Object> updatePerson(PersonsDTO personData);
	
	public ResponseEntity<Object> deletePerson(PersonsDTO personData);

}
