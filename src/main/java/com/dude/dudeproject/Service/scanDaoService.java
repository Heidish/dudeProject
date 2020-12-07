package com.dude.dudeproject.Service;

import com.dude.dudeproject.Repository.scanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class scanDaoService {

    @Autowired
    scanRepository repository;

    public scanDaoService(scanRepository repository) {

        this.repository = repository;
    }


        public String findIdByQrNo(String target_qr_no){
            String id=repository.findIdByQrNo(target_qr_no);

           return id;
        }




}
