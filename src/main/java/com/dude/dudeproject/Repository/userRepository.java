package com.dude.dudeproject.Repository;

import com.dude.dudeproject.Domain.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<user, Long> {

    // 아이디를 이용해 암호화비밀번호를 찾는 쿼리문
    // findPw
    @Query(value = "select u.user_pw from user u where u.user_id = ?1")
    String findByUserPw(@Param("user_id") String user_id);

    // 아이디가 존재하는지 검색하는 쿼리
    @Query(value ="select user_id from user where user_id=?1")
    String findByUseridIsTrue(@Param("user_id") String user_id);

    // 아이디 찾는 쿼리 findId
    @Query(value = "select user_id from user where user_mobile=?1")
    String findByUserId(@Param("user_mobile") String user_mobile);

    // 핸드폰 번호 찾는 쿼리 문
    @Query(value = "select user_name from user where user_mobile=?1")
    String findByUserMobile(@Param("user_mobile") String user_mobile);

    // 핸드폰 번호 찾는 쿼리 문
    @Query(value = "select user_id from user where user_mobile=?1")
    String findByUserMobile2(@Param("user_mobile") String user_mobile);

    // id로 user 값 얻기
    @Query(value = "select user_no, user_id, user_mobile, user_name, user_pw from user where user_id=?1")
    user findByUser(@Param("user_id") String user_id);

}
