package com.embl_ebi.assessment;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.embl_ebi.assessment.controller.PersonController;
import com.embl_ebi.assessment.domain.PersonDetails;
import com.embl_ebi.assessment.domain.PersonsDTO;
import com.embl_ebi.assessment.service.PersonServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PersonControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
    
    @MockBean
    PersonServiceImpl personService;
    
    @Test
    public void testAddPerson() throws Exception {

    	List<PersonDetails> personList = new ArrayList<>();
    	personList.add(new PersonDetails());
    	
        PersonsDTO personStub = new PersonsDTO(personList);
        Mockito.when(personService.addPerson(personStub)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Person created successfully"));
        
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8989/persons/add")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(personStub))).andReturn();
        
        assertEquals(200, result.getResponse().getStatus());
        
    }
    
    @Test
    public void testGetAllPerson() throws Exception {
    	
    	List<PersonDetails> personList = new ArrayList<>();
    	personList.add(new PersonDetails());
    	PersonsDTO dto = new PersonsDTO(personList);

        Mockito.when(personService.fetchAllPersonDetails()).thenReturn(dto);
        
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8989/persons/all")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
        
        assertEquals(mapper.writeValueAsString(dto), result.getResponse().getContentAsString());
        
    }
    
    @Test
    public void testUpdatePerson() throws Exception {
    	
    	List<PersonDetails> personList = new ArrayList<>();
    	personList.add(new PersonDetails());
    	
        PersonsDTO personStub = new PersonsDTO(personList);
        Mockito.when(personService.updatePerson(personStub)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Person updated successfully"));
        
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8989/persons/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(personStub)))
                .andReturn();
        
        assertEquals(200, result.getResponse().getStatus());
        
    }
    
    @Test
    public void testDeletePerson() throws Exception {
    	
    	List<PersonDetails> personList = new ArrayList<>();
    	personList.add(new PersonDetails());
    	
        PersonsDTO personStub = new PersonsDTO(personList);
        Mockito.when(personService.deletePerson(personStub)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Person deleted successfully"));
        
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8989/persons/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(personStub)))
        		.andReturn();
        
        assertEquals(200, result.getResponse().getStatus());
        
    }

}
