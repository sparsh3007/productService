package com.scaler.ecommerceapp.InheritanceExample.tableperclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
