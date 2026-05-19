package com.example.sp.service;


import com.example.sp.dto.SupplierDTO;
import org.springframework.http.ResponseEntity;

public interface SupplierService {

    public ResponseEntity<Object>getMaster();
    public ResponseEntity<Object>createMaster(SupplierDTO supplierDTO);
    public ResponseEntity<Object> updateMaster(Long id,SupplierDTO supplierDTO);
    public ResponseEntity<Object> deleteMaster(Long id);
    public ResponseEntity<Object> fetchMaster(Long id);
}
