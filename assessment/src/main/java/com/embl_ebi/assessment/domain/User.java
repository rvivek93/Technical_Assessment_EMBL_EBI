package com.embl_ebi.assessment.domain;

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
public class User {
	
	private String username;
	private String token;

}
