package com.dude.dudeproject.Service;

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



    /**
     * save service_tbl DB
     * @param service create qr
     */
    public void save(service service){
        repository.save(service);
    }

    /**
     * get service_tbl by user_id
     */
    public service findByService(String user_id){

        return  repository.findByService(user_id);
    }


}
