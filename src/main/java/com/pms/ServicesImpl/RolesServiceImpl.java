package com.pms.ServicesImpl;

import com.pms.dto.PermissionDto;
import com.pms.entity.Permission;
import com.pms.entity.Role;
import com.pms.repository.PermissionsRepo;
import com.pms.repository.RoleRepository;
import com.pms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RolesServiceImpl implements RoleService {


    @Autowired
    private PermissionsRepo permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Permission createPermission(String permissionName) {
        Permission permission = new Permission();
        try {
            Optional<Permission> existingPermissionOptional = permissionRepository.findByPermissionName(permissionName);
            if (existingPermissionOptional.isPresent()) {
                // Role with the given role name already exists
                // For example, you can throw an exception or return a custom error message
                throw new RuntimeException("permission with name " + permissionName + " already exists");
            }
            permission.setPermissionName(permissionName);
        }catch (Exception e){
            throw new RuntimeException("Error occurred while saving permissions: " + e.getMessage(), e);
        }

        return permissionRepository.save(permission);
    }

    public List<Permission> createPermissions(List<Permission> permissions) {

        List<Permission> savedPermissions = new ArrayList<>();
        // Get all existing permissions

        try {
            List<Permission> existingPermissions = permissionRepository.findAll();
            for (Permission permission : permissions) {
                boolean permissionExists = existingPermissions.stream()
                        .anyMatch(existingPermission -> existingPermission.getPermissionName().equals(permission.getPermissionName()));

                if (!permissionExists) {
                    // Permission does not already exist, so save it
                    Permission savedPermission = permissionRepository.save(permission);
                    savedPermissions.add(savedPermission);

                }else {
                    throw new RuntimeException("Permission with name " + permission.getPermissionName() + " already exists");
                }
            }
        } catch (Exception e) {
            // Handle any exceptions that might occur during the saving process
            // For example, you can log the exception or return an error message
            throw new RuntimeException("Error occurred while saving permissions: " + e.getMessage(), e);
            // Here, we're rethrowing the exception as a RuntimeException
        }


        return savedPermissions;
    }

    @Override
    public Role createRole(String roleName, List<Long> permissionIds) {
        Set<Permission> permissions = new HashSet<>();
        for (Long permissionId : permissionIds) {
            Permission permission = permissionRepository.findById(permissionId).orElse(null);
            if (permission != null) {
                permissions.add(permission);
            }
        }
        Optional<Role> existingRoleOptional = roleRepository.findByroleName(roleName);

        if (existingRoleOptional.isPresent()) {
            // Role with the given role name already exists
            // For example, you can throw an exception or return a custom error message
            throw new RuntimeException("Role with name " + roleName + " already exists");
        }

        // Role with the given role name does not exist, proceed with creating a new role
        Role role = new Role();
        role.setRoleName(roleName);
        role.setPermissions(permissions);

        return roleRepository.save(role);
    }
}
