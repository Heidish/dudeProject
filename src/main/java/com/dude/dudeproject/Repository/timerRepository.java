package com.dude.dudeproject.Repository;

import com.dude.dudeproject.Domain.timer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface timerRepository extends JpaRepository<timer, Long> {
}
