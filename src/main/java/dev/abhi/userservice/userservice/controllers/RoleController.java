package dev.abhi.userservice.userservice.controllers;


import dev.abhi.userservice.userservice.dtos.CreateRoleRequestDto;
import dev.abhi.userservice.userservice.dtos.RoleDto;
import dev.abhi.userservice.userservice.services.AuthService;
import dev.abhi.userservice.userservice.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/roles")
public class RoleController {

    @Autowired
    private RoleService roleService ;

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody CreateRoleRequestDto createRoleRequestDto){

        RoleDto roleDto = roleService.createRole(createRoleRequestDto.getName());

        return new ResponseEntity<>(roleDto, HttpStatus.OK) ;
    }
}
