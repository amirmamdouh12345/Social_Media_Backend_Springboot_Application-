package com.project.Services;

import com.project.Entities.Role;
import com.project.Repos.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepo roleRepo;

    public Role getRoleByID(Long id) {
        return roleRepo.findById(id).get();
    }
}
