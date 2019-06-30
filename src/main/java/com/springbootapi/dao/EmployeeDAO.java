package com.springbootapi.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springbootapi.model.Employee;
import com.springbootapi.repository.EmployeeRepository;

@Service
@Component
public class EmployeeDAO {
	@Autowired
	EmployeeRepository employeeRepository;
	
	/* to save an Employee*/
	public Employee save(Employee emp) {
		return employeeRepository.save(emp);
	}

	/* Search ALl Employee*/
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	/* get an Employee by id*/
	public Employee findById(Long empid) {
		return employeeRepository.findById(empid).get();
	}
	
	/* get an Employee by email*/
	public Employee findByEmail(String email) {
		Employee employee=new Employee();
		employee.setEmail(email);		
		Example<Employee> employeeExample=Example.of(employee);
		//calling QueryByExampleExecutor#findAll(Example)
		Iterable<Employee> employees = employeeRepository.findAll(employeeExample);
		Employee em = null;
		for(Employee e:employees)
			em=e;
		return em;
	}
	
	
	
	/* get an Employee by email and Password*/
	public Employee findByEmailPass(String email,String password) {
		Employee employee=new Employee();
		employee.setEmail(email);
		employee.setEmail(password);
		Example<Employee> employeeExample=Example.of(employee);
		//calling QueryByExampleExecutor#findAll(Example)
		Iterable<Employee> employees = employeeRepository.findAll(employeeExample);
		Employee em = null;
		for(Employee e:employees)
			em=e;
		return em;
	}
	/* delete an Employee*/
	
	public void delete(Employee emp) {
		employeeRepository.delete(emp);
	}
	
}
