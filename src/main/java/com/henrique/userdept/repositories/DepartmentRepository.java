package com.henrique.userdept.repositories;

import com.henrique.userdept.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
