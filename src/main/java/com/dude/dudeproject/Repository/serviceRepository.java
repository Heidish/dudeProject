package com.dude.dudeproject.Repository;

import com.dude.dudeproject.Domain.service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface serviceRepository extends JpaRepository<service, Long> {



    @Query(value= "insert into service_tbl (user_id, target_qr_no) values ('ygk1204', :target_qr_no )", nativeQuery = true)
    String getTarget_qr_no (@Param("target_qr_no") String target_qr_no);


    @Transactional
    @Modifying
    @Query(value="update service_tbl set qr_image=? where user_id=?", nativeQuery = true)
    void saveImage(@Param("qr_image") String qr_image, @Param("user_id") String user_id);

    @Query(value = "select target_qr_no from service where user_id=?1")
    String idCheck(@Param("user_id") String user_id);



}
