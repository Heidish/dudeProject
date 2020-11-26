package com.dude.dudeproject.Repository;

import com.dude.dudeproject.Domain.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<user, Long> {
}
