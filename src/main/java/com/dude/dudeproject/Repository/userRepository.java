package com.dude.dudeproject.Repository;

import com.dude.dudeproject.Domain.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<user, Long> {

    // 아이디를 이용해 암호화비밀번호를 찾는 쿼리문
    @Query(value = "select u.user_pw from user u where u.user_id = ?1")
    String findByUserId(@Param("user_id") String user_id);

    @Query(value ="select user_id from user where user_id=?1")
    String findByUserIds(@Param("user_id") String user_id);


}
