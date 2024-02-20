package com.scaler.productService.InheritanceExample.tableperclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
