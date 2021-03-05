package com.embl_ebi.assessment.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.embl_ebi.assessment.constants.PersonResponses;
import com.embl_ebi.assessment.domain.PersonDetails;
import com.embl_ebi.assessment.domain.PersonsDTO;
import com.embl_ebi.assessment.exception.ResourceNotFoundException;
import com.embl_ebi.assessment.model.Person;
import com.embl_ebi.assessment.repository.PersonRepository;
import com.embl_ebi.assessment.service.helper.PersonServiceHelper;
import com.fasterxml.jackson.core.JsonProcessingException;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class PersonServiceTest {

	@InjectMocks
	private PersonServiceImpl personService;

	@MockBean
	private PersonRepository personRepository;

	@MockBean
	private PersonServiceHelper personServiceHelper;

	private List<Person> personList = null;
	private List<PersonDetails> personDetailsList = null;
	private PersonsDTO expectedDto = null;

	@BeforeEach
	public void initEach() {
		personList = new ArrayList<>();
		personList.add(new Person(null, "Sam", "Joe", 22, "Black"));

		personDetailsList = new ArrayList<>();
		personDetailsList.add(new PersonDetails("Sam", "Joe", 22, "Black"));

		expectedDto = new PersonsDTO(personDetailsList);
	}

	@Test
	public void Test_fetchAllPersonDetails() throws JsonProcessingException {

		Mockito.when(personRepository.findAll()).thenReturn(personList);
		Mockito.when(personServiceHelper.convertToPersonDomain(Mockito.any()))
				.thenReturn(new PersonDetails("Sam", "Joe", 22, "Black"));

		PersonsDTO responseDto = personService.fetchAllPersonDetails();
		assertEquals(expectedDto.getPerson().get(0).getFirst_name(), responseDto.getPerson().get(0).getFirst_name());
	}

	@Test
	public void Test_fetchAllPersonDetails_Exception() throws JsonProcessingException {

		Mockito.when(personRepository.findAll()).thenReturn(null);

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			personService.fetchAllPersonDetails();
		});
	}

	@Test
	public void Test_addPerson() {

		Mockito.when(personRepository.findByFirst_NameAndLast_Name(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(null);
		Mockito.when(personServiceHelper.convertToPersonEntity(Mockito.any()))
				.thenReturn(new Person(null, "Sam", "Joe", 22, "Black"));
		Mockito.when(personRepository.save(Mockito.any()))
				.thenReturn(new Person(UUID.randomUUID(), "Sam", "Joe", 22, "Black"));

		ResponseEntity<Object> actualResponse = personService.addPerson(expectedDto);
		assertEquals(PersonResponses.CREATED, actualResponse.getBody());
	}

	@Test
	public void Test_addPerson_whenAlreadyPersonExists() {

		List<PersonDetails> personDetailsList = new ArrayList<>();
		personDetailsList.add(new PersonDetails());

		PersonsDTO dto = new PersonsDTO(personDetailsList);

		Mockito.when(personRepository.findByFirst_NameAndLast_Name(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(new Person());

		personService.addPerson(dto);
	}

	@Test
	public void Test_updatePerson() {

		Mockito.when(personRepository.findByFirst_NameAndLast_Name(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(new Person(null, "Sam", "Joe", 22, "Black"));
		Mockito.when(personServiceHelper.convertToPersonEntity(Mockito.any()))
				.thenReturn(new Person(null, "Sam", "Joe", 22, "Black"));
		Mockito.when(personRepository.save(Mockito.any()))
				.thenReturn(new Person(UUID.randomUUID(), "Sam", "Joe", 22, "Black"));

		ResponseEntity<Object> actualResponse = personService.updatePerson(expectedDto);
		assertEquals(PersonResponses.UPDATED, actualResponse.getBody());
	}

	@Test
	public void Test_updatePerson_whenNewPersonAreAdded() {

		List<PersonDetails> personDetailsList = new ArrayList<>();
		personDetailsList.add(new PersonDetails());

		PersonsDTO dto = new PersonsDTO(personDetailsList);
		Mockito.when(personRepository.findByFirst_NameAndLast_Name(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(null);
		Mockito.when(personServiceHelper.convertToPersonEntity(new PersonDetails())).thenReturn(new Person());

		personService.updatePerson(dto);
	}

	@Test
	public void Test_deletePerson() {

		Mockito.when(personRepository.findByFirst_NameAndLast_Name(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(new Person(null, "Sam", "Joe", 22, "Black"));
		ResponseEntity<Object> actualResponse = personService.deletePerson(expectedDto);
		assertEquals(PersonResponses.DELETED, actualResponse.getBody());
	}

	@Test
	public void Test_deletePerson_Exception() {

		Mockito.when(personRepository.findByFirst_NameAndLast_Name(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(null);
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			personService.deletePerson(expectedDto);
		});
	}

}
