package com.dude.dudeproject.Repository;

import com.dude.dudeproject.Domain.scan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface scanRepository extends JpaRepository<scan, Long> {

}
