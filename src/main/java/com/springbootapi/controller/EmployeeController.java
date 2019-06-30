package com.springbootapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootapi.dao.EmployeeDAO;
import com.springbootapi.model.Employee;
import com.springbootapi.model.EmployeeBody;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	EmployeeDAO employeeDAO;
	
	/* to save an Employee */
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee emp) {
		return employeeDAO.save(emp);
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeDAO.findAll();
	} 
	
	/* Get Employee by id*/
	@GetMapping("/api/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long empid){
		Employee emp=employeeDAO.findById(empid);
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(emp);
	}
	
	
	/* Get Employee by email*/
	@GetMapping("/employees/{email}")
	public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable(value="email") String email){
		Employee emp=employeeDAO.findByEmail(email);
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(emp);
	}
	
	/* Get Employee by email and Password*/
	@GetMapping("/test")
	public ResponseEntity<Employee> getEmployeeByEmailPass(@RequestBody EmployeeBody empbody){
		//String password="";
		Employee emp=employeeDAO.findByEmailPass(empbody.getEmail(),empbody.getPassword());
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(emp);
	}
	
	
	/* Update an Employee by empid*/
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long empid, @Valid @RequestBody Employee empDetails ){
		Employee emp=employeeDAO.findById(empid);
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		emp.setName(empDetails.getName());
		emp.setEmail(empDetails.getEmail());
		emp.setPassword(empDetails.getPassword());		
		Employee updateEmployee=employeeDAO.save(emp);		
		return ResponseEntity.ok().body(updateEmployee);
	}
	
	/* Delete  Employee by empid*/
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value="id") Long empid){
		Employee emp=employeeDAO.findById(empid);
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		employeeDAO.delete(emp);				
		return ResponseEntity.ok().build();
	}
	
}
