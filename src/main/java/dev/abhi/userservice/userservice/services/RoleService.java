package dev.abhi.userservice.userservice.services;

import dev.abhi.userservice.userservice.dtos.RoleDto;
import dev.abhi.userservice.userservice.models.Role;
import dev.abhi.userservice.userservice.models.User;
import dev.abhi.userservice.userservice.repo.RoleRepository;
import dev.abhi.userservice.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleDto createRole(String name){

        List<Role> roles = roleRepository.findAll() ;

        Set<Role> set = new HashSet<>(roles);
        int numberOfRoles = set.size() ;

        Role newRole = new Role() ;
        newRole.setRole(name);

        set.add(newRole);
        if(set.size() == numberOfRoles){
            System.out.println("Duplicates Role !! Role is already added ");
        }

        Role savedRole = roleRepository.save(newRole);
        RoleDto roleDto = new RoleDto() ;
        roleDto.setName(savedRole.getRole());
        return roleDto ;

    }
}
