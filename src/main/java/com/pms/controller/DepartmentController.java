package com.pms.controller;

import com.pms.entity.Department;
import com.pms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/getAllDepartments")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        return department.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createDepartment")
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
        Optional<Department> departmentOptional = departmentService.getDepartmentById(id);
        Department department;
        if (departmentOptional.isPresent()) {
            department = departmentOptional.get();
            department.setName(departmentDetails.getName());
//            department.setUsers(departmentDetails.getUsers());
//            department.setKpis(departmentDetails.getKpis());
            // Update other fields if necessary
            Department updatedDepartment = departmentService.save(department);
            return ResponseEntity.ok(updatedDepartment);
        } else {
            return ResponseEntity.notFound().build();
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@RequestBody Department department) {
        return ResponseEntity.ok("Department deleted successfully.");
    }
}







