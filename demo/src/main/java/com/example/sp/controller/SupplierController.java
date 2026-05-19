package com.example.sp.controller;


import com.example.sp.bean.LoginUser;
import com.example.sp.dto.SupplierDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
@CrossOrigin("*")

public interface SupplierController  {

    @PostMapping("/login")
     public ResponseEntity<Object> login(@RequestBody LoginUser user, HttpServletRequest request);


    @GetMapping("/fetch/master")
    ResponseEntity<Object> fetchMaster();
    //Object could be of list ,string, map ,dto etc.
    //List<Supplier>


    @PostMapping("/add/master")
    ResponseEntity<Object> createMaster(@RequestBody SupplierDTO masterDTO);

    @GetMapping("/get/master")
    ResponseEntity<Object> getMaster(@RequestParam Long id);


    @PutMapping("/update/master")
    ResponseEntity<Object> updateMaster(@RequestParam Long id,@RequestBody SupplierDTO supplierDTO);


    @DeleteMapping("/delete/master")
    ResponseEntity<Object> deleteMaster(@RequestParam(name = "id") Long id);


}

