package com.fivePoints.firstProject.Services;

import com.fivePoints.firstProject.Exceptions.ResourceNotFoundException;
import com.fivePoints.firstProject.Models.Role;
import com.fivePoints.firstProject.Repositries.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    //add a new role to the dataBase
    public Role saveNewRole(Role role)
    {
        return this.roleRepository.save(role);
    }

    //get all the list of roles added to the dataBase
    public List<Role> getAllRoles()
    {
        return this.roleRepository.findAll();
    }

    //get or find one role by his id
    public Role findRoleByID(int id)
    {
        Optional<Role> roleData = this.roleRepository.findById(id);
        return roleData.orElseThrow(() -> new ResourceNotFoundException("Role not found"));
    }

    //update the role by his id and the object role wanted to update
    public String updateRoleByID(int id, Role role)
    {
        Optional<Role> roleData = this.roleRepository.findById(id);
        if (roleData.isPresent()) {
            Role existingRole = roleData.orElse(null);
            existingRole.setName(role.getName());
            this.roleRepository.save(existingRole);
            return "Role updated successfully!";
        } else {
            throw new ResourceNotFoundException("Role not found");
        }
    }
}
