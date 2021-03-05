package com.embl_ebi.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.embl_ebi.assessment.model.Person;

/**
 * 
 * Repository class to store person details.
 * 
 * @author Vivek Rajendran
 *
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, String>{
	
	@Query("SELECT p FROM Person p where p.first_name = ?1 AND p.last_name = ?2")
	public Person findByFirst_NameAndLast_Name(String first_name, String last_name);

}
