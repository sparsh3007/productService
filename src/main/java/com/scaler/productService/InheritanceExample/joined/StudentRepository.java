package com.scaler.productService.InheritanceExample.joined;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("joinedStudentRepository")
public interface StudentRepository extends JpaRepository<Student, Long>{

}
