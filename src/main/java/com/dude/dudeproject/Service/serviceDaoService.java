package com.dude.dudeproject.Service;

import com.dude.dudeproject.Repository.serviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class serviceDaoService {


    @Autowired
    private serviceRepository repository;


    public serviceDaoService(serviceRepository repository) {
        this.repository = repository;
    }


    public String saveQR(String target_qr_no){

        return repository.getTarget_qr_no(target_qr_no);
    }

    public String  idCheck (String user_id){
        return repository.idCheck(user_id);
    }

    public void saveImage(String qr_image, String user_id){

        repository.saveImage(qr_image, user_id);
    }




}
