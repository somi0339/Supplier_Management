package com.example.sp.service.impl;


import com.example.sp.bean.SupplierEntity;
import com.example.sp.dto.SupplierDTO;
import com.example.sp.exceptions.ResourceNotFoundException;
import com.example.sp.jpa.SupplierRepository;
import com.example.sp.service.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class SupplierServiceImpl implements SupplierService {
    //dependency injection

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SupplierRepository supplierRepository;
    private static final Logger log =
            LoggerFactory.getLogger(SupplierServiceImpl.class);


    public ResponseEntity<Object>getMaster(){
        //throw new RuntimeException("Database connection failed");
            return ResponseEntity.ok(supplierRepository.findAll());
        }

    @Override
    public ResponseEntity<Object> createMaster(SupplierDTO supplierDTO) {
        SupplierEntity obj=new SupplierEntity();
      obj.setName(supplierDTO.getName());
      obj.setEmail(supplierDTO.getEmail());
      obj.setPhone(supplierDTO.getPhone());
       return ResponseEntity.ok(supplierRepository.save(obj));
    }

    @Override
    public ResponseEntity<Object> updateMaster(Long id, SupplierDTO supplierDTO) {
        Optional<SupplierEntity> rec=supplierRepository.findById(id);
        if(rec.isPresent()) {
            SupplierEntity supplierEntity = rec.get();

            supplierEntity.setName(supplierDTO.getName());
            supplierEntity.setPhone(supplierDTO.getPhone());
            supplierEntity.setEmail(supplierDTO.getEmail());

            supplierRepository.save(supplierEntity);

            return ResponseEntity.status(200).body(supplierEntity);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> deleteMaster(Long id) {
       supplierRepository.deleteById(id);
        return ResponseEntity.ok().body(Map.of("message", "Deleted Successfully"));
    }

    @Override
    public ResponseEntity<Object> fetchMaster(Long id) {
        Optional<SupplierEntity> obj=supplierRepository.findById(id);
        if(obj.isEmpty()){
            log.warn("No supplier found for id={}", id);
            throw new ResourceNotFoundException("Record Not Found");
        }
        return ResponseEntity.ok(obj);
    }
}


