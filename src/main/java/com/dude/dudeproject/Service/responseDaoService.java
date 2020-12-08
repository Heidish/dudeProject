package com.dude.dudeproject.Service;

import com.dude.dudeproject.Domain.response;
import com.dude.dudeproject.Repository.responseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class responseDaoService {

    @Autowired
    private responseRepository repository;

    private static List<response> users = new ArrayList<>();
    private static Long usersCount = 0L;

    public responseDaoService(responseRepository repository) {
        this.repository = repository;
    }

    /**
     * ID, 응답 상태 저장
     */
    public response create(response response){

        return repository.save(response);
    }
}

