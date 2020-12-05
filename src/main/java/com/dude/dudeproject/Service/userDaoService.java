package com.dude.dudeproject.Service;

import com.dude.dudeproject.Domain.user;
import com.dude.dudeproject.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class userDaoService {

    @Autowired
    private userRepository repository;

    private static List<user> users = new ArrayList<>();
    private static Long usersCount = 0L;

    public userDaoService(userRepository repository) {
        this.repository = repository;
    }

    /**
     * 회원가입
     */
    public user save(user user) {

        return repository.save(user);
    }

    /**
     * 로그인
     */
    public boolean login(user user) {
        if (user.getUser_pw().equals(repository.findByUserPw(user.getUser_id()))) {
            return true; // login success
        }

        return false; // login fail
    }

    public String loginPwdChk(user user) {
        String pass = repository.findByUserPw(user.getUser_id()); // pass = 유저의 암호화된 비밀번호

        return pass; // login fail
    }

    /**
     *  idCheck
     */
    public int idCheck(String user_id) {
        if (repository.findByUseridIsTrue(user_id) == null) {
            return 0; // id is unique
        }
        return 1; // id is reduplicated
    }

    /**
     *  mobileCheck
     */
    public String mobileCheck(String user_mobile){
        if(repository.findByUserMobile(user_mobile) == null){
            return "0";  // is not exist
        }
        return repository.findByUserMobile(user_mobile);   // exist
    }

    /**
     * findId
     */
    public String findId(String user_mobile){
        if(repository.findByUserId(user_mobile) == null){
            return "0";  // is not exist
        }
        return repository.findByUserId(user_mobile);   // exist
    }

    /**
     *  mobileCheck2
     */
    public String mobileCheck2(String user_mobile){
        if(repository.findByUserMobile2(user_mobile) == null){
            return "0";  // is not exist
        }
        return repository.findByUserMobile2(user_mobile);   // exist
    }

    /**
     * setNewPassword
     */
    public void setNewPass(user user){
        String password = user.getUser_pw();
        user = repository.findByUser(user.getUser_id());
        user.setUser_pw(password);
        repository.save(user);
    }
}

