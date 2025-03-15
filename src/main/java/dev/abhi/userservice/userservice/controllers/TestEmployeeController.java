package dev.abhi.userservice.userservice.controllers;

import dev.abhi.userservice.userservice.dtos.TestEmployeeDto;
import dev.abhi.userservice.userservice.models.Employee;
import dev.abhi.userservice.userservice.services.TestEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class TestEmployeeController {

    @Autowired /** this is field injection **/
    private TestEmployeeService testEmployeeService ;

    @PostMapping()
    @ResponseBody
    public Employee createEmployee(@RequestBody TestEmployeeDto dto){
        return  testEmployeeService.createEmployee(dto.getName(), dto.getAge(), dto.getEmail());
        //return new ResponseEntity<>(employee, HttpStatus.OK) ;
    }

    @GetMapping
    public List<Employee> getEmployee(){
        return testEmployeeService.getAllEmployee() ;
    }
}
