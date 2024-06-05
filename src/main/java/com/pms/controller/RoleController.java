package com.pms.controller;

import com.pms.dto.CreateRoleRequest;
import com.pms.entity.Permission;
import com.pms.entity.Role;
import com.pms.service.RoleService;
import com.pms.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/Api/")
public class RoleController {

    @Autowired
    private RoleService roleService;



    @PostMapping("create-permissions")
    public ApiResponse<Role> createPermissions(@RequestBody List<Permission> permission) {
        List<Permission> permissions = roleService.createPermissions(permission);
        ApiResponse res = new ApiResponse<>();
        res.setMessage("permissions added successfully");
        res.setStatus(200);
        res.setPayload(permissions);
        return res;
    }

    @PostMapping("createRoles")
    public ApiResponse<Role> createRole(@RequestBody CreateRoleRequest request) {
        Role createdRole = roleService.createRole(request.getRoleName(), request.getPermissionIds());
        ApiResponse<Role> res = new ApiResponse<>();
        res.setMessage("Roles created successfully");
        res.setStatus(200);
        res.setPayload(createdRole);
        return res;
    }
}
