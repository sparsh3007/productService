package com.scaler.productService.InheritanceExample.joined;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("joinedMentorRepository")
public interface MentorRespository extends JpaRepository<Mentor, Long> {
}
