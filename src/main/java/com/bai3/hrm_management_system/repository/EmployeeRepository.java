package com.bai3.hrm_management_system.repository;

import com.bai3.hrm_management_system.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Selim Horri
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}








