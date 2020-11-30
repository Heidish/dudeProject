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

    public user save(user user) {

        return repository.save(user);
    }

}
