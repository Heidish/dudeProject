package com.dude.dudeproject.Repository;

import com.dude.dudeproject.Domain.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<user, Long> {

    // 회원가입

//    // id로 회원 검색
//    Optional<user> findById(@Param("user_id") String user_id);
//
//    // pw로 회원 검색
//    Optional<user> findByPw(@Param("user_pw") String user_pw);

    @Query(value = "select u.user_pw from user u where u.user_id = ?1")
    String findByUserId(@Param("user_id") String user_id);


}
