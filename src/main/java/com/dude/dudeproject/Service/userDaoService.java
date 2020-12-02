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
     * @param user
     * @return
     */
    public user save(user user) {

        return repository.save(user);
    }

    /**
     * 로그인
     */
    public boolean login(user user) {
//        if (repository.findById(user.getUser_id()).isPresent() && repository.findByPw(user.getUser_pw()).isPresent()) {
//            return user;
//        }
        if (user.getUser_pw().equals(repository.findByUserId(user.getUser_id()))) {
            return true; // login success
        }

        return false; // login fail
    }
}
