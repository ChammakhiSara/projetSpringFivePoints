package com.fivePoints.firstProject.Controllers;

import com.fivePoints.firstProject.Models.Role;
import com.fivePoints.firstProject.Payload.Responses.ResponseMessage;
import com.fivePoints.firstProject.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/")
    public ResponseEntity<Role> saveNewRole(@RequestBody Role role)
    {
        Role savedRole =  this.roleService.saveNewRole(role);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Role>> getAllRoles()
    {
        List<Role> listRoles = this.roleService.getAllRoles();
        return new ResponseEntity<>(listRoles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserByID(@PathVariable("id") int id)
    {
        Role role = this.roleService.findRoleByID(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateUserByID(@PathVariable("id") int id, @RequestBody Role role)
    {
        String message = this.roleService.updateRoleByID(id, role);
        return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
    }

}
