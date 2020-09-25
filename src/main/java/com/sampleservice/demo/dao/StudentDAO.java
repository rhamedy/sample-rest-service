package com.sampleservice.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sampleservice.demo.model.Student;

@Repository
public interface StudentDAO extends CrudRepository<Student, Long>{
	Optional<Student> findByFirstNameLike(String firstName);

	@Query("FROM Student s WHERE s.email = :email")
	Optional<Student> findByEmail(@Param("email") String email);
}
