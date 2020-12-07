package com.dude.dudeproject.Repository;

import com.dude.dudeproject.Domain.scan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface scanRepository extends JpaRepository<scan, String> {

    //target_qr_no를 이용해 user_no&&user_id확인
    @Query(value = "select u.user_id from user u join service s on u.user_id=s.user_id and s.target_qr_no = ?1")
    String findIdByQrNo(@Param("target_qr_no") String target_qr_no);

}
