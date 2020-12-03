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
        if (user.getUser_pw().equals(repository.findByUserId(user.getUser_id()))) {
            return true; // login success
        }


        return false; // login fail
    }

    /**
     *  idCheck
     */
    public int idCheck(String user_id) {
        if (repository.findByUserIds(user_id) == null) {
            return 0; // id is unique
        }
        return 1; // id is reduplicated
    }
}

