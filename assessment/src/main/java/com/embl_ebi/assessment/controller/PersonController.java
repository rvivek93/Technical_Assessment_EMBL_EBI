package com.embl_ebi.assessment.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.embl_ebi.assessment.domain.PersonsDTO;
import com.embl_ebi.assessment.service.PersonServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * Controller class for Person utility operation.
 * 
 * 
 * @author Vivek Rajendran
 *
 */
@RestController
@RequestMapping("/persons")
public class PersonController {
	
	private Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonServiceImpl personService;

	@GetMapping(path = "/all")
	@ApiOperation(value = "Get All Person entities", notes = "Fetch all Person entities")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public PersonsDTO getAllPersons() {
		
		logger.debug("Fetching all person details");
		return personService.fetchAllPersonDetails();
	}

	@PostMapping(path = "/add")
	@ApiOperation(value = "Add a Person", notes = "Add a Person entity")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Object> addPerson(@RequestBody PersonsDTO person) {
		
		logger.debug("Add person details");
		return personService.addPerson(person);
	}
	
	@PutMapping(path = "/update")
	@ApiOperation(value = "Update Person", notes = "Update person and its details.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public ResponseEntity<Object> updatePerson(@RequestBody PersonsDTO person) {

		logger.debug("Update person details");
		return personService.updatePerson(person);
	}
	
	@DeleteMapping(path = "/delete")
	@ApiOperation(value = "Delete person and related details", notes = "Delete person and its details.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public ResponseEntity<Object> deleteCustomer(@RequestBody PersonsDTO person) {

		logger.debug("Delete person details");
		return personService.deletePerson(person);
	}


}
