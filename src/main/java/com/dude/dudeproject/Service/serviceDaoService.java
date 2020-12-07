package com.dude.dudeproject.Service;

import com.dude.dudeproject.Domain.service;
import com.dude.dudeproject.Repository.serviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class serviceDaoService {

    @Autowired
    serviceRepository repository;

//    public void findByQrNo(service service){
//        repository.findById(service.getUser_id());
//    }


}
