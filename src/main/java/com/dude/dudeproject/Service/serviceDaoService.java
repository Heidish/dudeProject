package com.dude.dudeproject.Service;

import com.dude.dudeproject.Domain.response;
import com.dude.dudeproject.Domain.service;
import com.dude.dudeproject.Repository.serviceRepository;
import com.dude.dudeproject.System.ImageService;
import com.dude.dudeproject.System.QRGenerator;
import com.dude.dudeproject.System.RandomClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.Map;
import java.util.Random;

@Service
public class serviceDaoService {


    @Autowired
    private serviceRepository repository;






    public serviceDaoService(serviceRepository repository) {
        this.repository = repository;
    }




    public service create(service service){

        return repository.save(service);
    }

}
