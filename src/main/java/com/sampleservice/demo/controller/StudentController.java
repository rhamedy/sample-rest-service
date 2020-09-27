/**
 * A Sample Controller that exposes a bunch of endpoints that allow operations 
 * such as CREATE, READ, DELETE and LIST users. 
 * 
 * @author Rafiullah Hamedy
 */

package com.sampleservice.demo.controller;

import java.util.*;

import com.sampleservice.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sampleservice.demo.dto.inbound.StudentInDTO;
import com.sampleservice.demo.dto.outbound.StudentOutDTO;
import com.sampleservice.demo.model.Student;

@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService studentService;

	public StudentController() {}

	@GetMapping("list")
	public List<StudentOutDTO> list() {
		Collection<Student> studentsIterable = studentService.list();
		List<StudentOutDTO> outDTOs = new ArrayList<>(); 
		studentsIterable.forEach(entry -> outDTOs.add(new StudentOutDTO(entry)));
		return outDTOs; 
	}
	
	@GetMapping("{sid}")
	public StudentOutDTO getById(@PathVariable("sid") Long id) {
		Student student = studentService.findById(id);
		return new StudentOutDTO(student);
	}
	
	@PostMapping
	public StudentOutDTO save(@RequestBody StudentInDTO dto) {
		Student student = dto.toEntity(); 
		studentService.saveOrUpdate(student);
		return new StudentOutDTO(student); 
	}
	
	@DeleteMapping("{sid}")
	public ResponseEntity<?> delete(@PathVariable("sid") Long id) {
		Student existingStudent = studentService.findById(id);
		studentService.delete(existingStudent);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
