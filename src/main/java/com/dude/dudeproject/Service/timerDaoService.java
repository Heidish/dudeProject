package com.dude.dudeproject.Service;

import com.dude.dudeproject.Domain.timer;
import com.dude.dudeproject.Repository.timerRepository;
import com.dude.dudeproject.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class timerDaoService {

    @Autowired
    private timerRepository repository;

    private static List<timer> timer = new ArrayList<>();
    private static Long timersCount = 0L;

    public timerDaoService(timerRepository repository) {
        this.repository = repository;
    }

    /**
     * setTimer
     */
    public timer save(timer timer, String user_id) {
        timer.setUser_id(user_id);

        return repository.save(timer);
    }

}
