package com.embl_ebi.assessment.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vivek Rajendran
 *
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PERSON_ID")
	private UUID id;
	
	private String first_name;
	
	private String last_name;
	
	private Integer age;
	
	private String favourite_colour;

}
