package com.scaler.productService.InheritanceExample.tableperclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
