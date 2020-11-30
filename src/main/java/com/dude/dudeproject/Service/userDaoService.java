package com.dude.dudeproject.Service;

import com.dude.dudeproject.Domain.user;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class userDaoService {

    private static List<user> users = new ArrayList<>();

    private static Long usersCount = 0L;


    public user save(user user) {
        user.setUser_no(++usersCount);
        user.setUser_name(user.getUser_name());


        return user;
    }

}
