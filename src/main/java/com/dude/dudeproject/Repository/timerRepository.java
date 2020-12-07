package com.dude.dudeproject.Repository;

import com.dude.dudeproject.Domain.timer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface timerRepository extends JpaRepository<timer, Long> {

    @Query(value = "select u.user_no from user u where u.user_id = ?1")
    Long findByUserId(@Param("user_id") String user_id);

}
