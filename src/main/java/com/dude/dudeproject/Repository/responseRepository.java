package com.dude.dudeproject.Repository;

import com.dude.dudeproject.Domain.response;
import com.dude.dudeproject.Domain.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface responseRepository extends JpaRepository<response, Long> {

    //사용자 응답값 얻기
    @Query(value = "select user_response from response where user_id = ?1")
    String getStatus(@Param("user_id") String user_id);

    //사용자 번호 얻기
    @Query(value = "select user_mobile from user where user_id = ?1")
    String getPhonenum(@Param("user_id") String user_id);
}