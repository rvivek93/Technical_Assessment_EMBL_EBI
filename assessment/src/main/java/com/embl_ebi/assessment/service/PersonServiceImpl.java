package com.embl_ebi.assessment.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.embl_ebi.assessment.constants.PersonResponses;
import com.embl_ebi.assessment.domain.PersonDetails;
import com.embl_ebi.assessment.domain.PersonsDTO;
import com.embl_ebi.assessment.exception.ResourceNotFoundException;
import com.embl_ebi.assessment.model.Person;
import com.embl_ebi.assessment.repository.PersonRepository;
import com.embl_ebi.assessment.service.helper.PersonServiceHelper;

/**
 * 
 * Service class for utility operation.
 * 
 * 
 * @author Vivek Rajendran
 *
 */
@Service
public class PersonServiceImpl implements PersonService {
	
	private Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PersonServiceHelper personServiceHelper;

	/**
	 *Used to fetch all person details.
	 *
	 */
	public PersonsDTO fetchAllPersonDetails() {
		List<PersonDetails> allPersonDetails = new ArrayList<>();
		Iterable<Person> personList = personRepository.findAll();
		
		if(personList==null) {
			logger.debug("No Person Details Found");
			throw new ResourceNotFoundException("No Person details found");
		}

		personList.forEach(person -> {
			allPersonDetails.add(personServiceHelper.convertToPersonDomain(person));
		});
		PersonsDTO personDto = new PersonsDTO(allPersonDetails);
		return personDto;
	}

	/**
	 * Used to add person details.
	 *
	 */
	public ResponseEntity<Object> addPerson(PersonsDTO personData) {
		for (PersonDetails personDetails : personData.getPerson()) {
			Person personEntity = personRepository.findByFirst_NameAndLast_Name(personDetails.getFirst_name(),
					personDetails.getLast_name());
			if(personEntity != null) {
				logger.debug("Person "+personDetails.getFirst_name() +" "+personDetails.getLast_name()+" already exists");
				continue;
			}
			Person person = personServiceHelper.convertToPersonEntity(personDetails);
			personRepository.save(person);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(PersonResponses.CREATED);
	}

	/**
	 * Used to update person details.
	 * 
	 * @param personData
	 * @return
	 */
	/**
	 *
	 */
	public ResponseEntity<Object> updatePerson(PersonsDTO personData) {
		for (PersonDetails personDetails : personData.getPerson()) {
			Person person = personServiceHelper.convertToPersonEntity(personDetails);
			Person personEntity = personRepository.findByFirst_NameAndLast_Name(personDetails.getFirst_name(),
					personDetails.getLast_name());
			if (personEntity != null) {
				personEntity.setAge(person.getAge());
				personEntity.setFavourite_colour(person.getFavourite_colour());
				personRepository.save(personEntity);
			} else {
				logger.debug("Person "+personDetails.getFirst_name()+" "+personDetails.getLast_name()+" not found to update.");
				continue;
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(PersonResponses.UPDATED);
	}

	/**
	 * 
	 * Used to delete Person Details.
	 *
	 */
	public ResponseEntity<Object> deletePerson(PersonsDTO personData) {
		List<String> messageList = new ArrayList<>();
		StringBuilder personBuilder = new StringBuilder();
		boolean hasValidPerson = true;

		for (PersonDetails personDetails : personData.getPerson()) {
			Person personEntity = personRepository.findByFirst_NameAndLast_Name(personDetails.getFirst_name(),
					personDetails.getLast_name());
			if (personEntity != null) {
				personRepository.delete(personEntity);
			} else {
				logger.debug("Person "+personDetails.getFirst_name()+" "+personDetails.getLast_name()+" not found to delete.");
				hasValidPerson = false;
				personBuilder.append(personDetails.getFirst_name() + " " + personDetails.getLast_name());
				messageList.add("Person " + personDetails.getFirst_name() + " " + personDetails.getLast_name()
						+ " does not exist.");
			}
		}
		if (hasValidPerson) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(PersonResponses.DELETED);
		} else {
			throw new ResourceNotFoundException("Person's "+personBuilder.toString()+" not found to be deleted");
		}
	}

}
