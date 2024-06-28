package com.bai3.hrm_management_system.repository;

import com.bai3.hrm_management_system.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Selim Horri
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}








