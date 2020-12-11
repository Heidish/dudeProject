package com.dude.dudeproject.Repository;

import com.dude.dudeproject.Domain.service;
import com.dude.dudeproject.Domain.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Blob;

@Repository
public interface serviceRepository extends JpaRepository<service, Long> {


    @Query(value = "select u.user_no from user u where u.user_id = ?1")
    service findByUserId(@Param("user_id") String user_id);


//    @Query(value = "select u from user u where u.user_id = ?1")
    @Query(value = "select s from service s where s.user_id=?1")
    service findByService(@Param("user_id") String user_id);
}
