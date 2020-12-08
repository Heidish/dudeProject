package com.dude.dudeproject.Repository;

import com.dude.dudeproject.Domain.service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface serviceRepository extends JpaRepository<service, Long> {



}
