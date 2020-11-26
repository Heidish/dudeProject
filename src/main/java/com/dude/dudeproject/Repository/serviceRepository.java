package com.dude.dudeproject.Repository;

import com.dude.dudeproject.Domain.service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface serviceRepository extends JpaRepository<service, Long> {
}
