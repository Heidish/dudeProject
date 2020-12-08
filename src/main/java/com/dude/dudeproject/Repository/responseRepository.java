package com.dude.dudeproject.Repository;

import com.dude.dudeproject.Domain.response;
import com.dude.dudeproject.Domain.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface responseRepository extends JpaRepository<response, Long> {

    //ID, 상태값 저장
    @Query(value= "insert into user_response_tbl (user_id, user_response) values (:user_id, :user_response)", nativeQuery = true)
    String save (@Param("user_id") String user_id, @Param("user_response") String user_response);

}
