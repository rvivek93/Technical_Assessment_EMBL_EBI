package com.embl_ebi.assessment.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author Vivek Rajendran
 *
 */


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonsDTO {
	
	@JsonProperty("person")
	private List<PersonDetails> person;

}
