package dev.abhi.userservice.userservice.services;

import dev.abhi.userservice.userservice.dtos.TestEmployeeDto;
import dev.abhi.userservice.userservice.models.Employee;
import dev.abhi.userservice.userservice.repo.TestEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TestEmployeeService {

    @Autowired
    private TestEmployeeRepository testEmployeeRepository ;

    public Employee createEmployee(String name, int age, String email ){

        Employee newEmployee = new Employee() ;
        newEmployee.setName(name);
        newEmployee.setEmail(email);
        newEmployee.setAge(age);
        return testEmployeeRepository.save(newEmployee);
    }


    public List<Employee> getAllEmployee(){
        return testEmployeeRepository.findAll() ;
    }
}
