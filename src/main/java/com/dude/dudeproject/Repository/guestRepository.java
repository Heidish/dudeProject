package com.dude.dudeproject.Repository;

import com.dude.dudeproject.Domain.guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface guestRepository extends JpaRepository<guest, Long> {

}