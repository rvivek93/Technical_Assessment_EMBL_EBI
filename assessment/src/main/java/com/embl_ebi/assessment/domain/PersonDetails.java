package com.embl_ebi.assessment.domain;

import lombok.AllArgsConstructor;

import lombok.Builder;
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
@Builder
public class PersonDetails {
	
	private String first_name;
	
	private String last_name;
	
	private Integer age;
	
	private String favourite_colour;

}
