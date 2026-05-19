package com.example.sp.controller.impl;


import com.example.sp.bean.LoginUser;
import com.example.sp.controller.SupplierController;
import com.example.sp.dto.SupplierDTO;
import com.example.sp.jpa.LoginUserRepository;
import com.example.sp.jpa.SupplierRepository;
import com.example.sp.service.SupplierService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/suppliers")

public class SupplierControllerImpl implements SupplierController {
  @Autowired
  LoginUserRepository loginUserRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    SupplierService supplierService;

//    @Override
//    public ResponseEntity<Object> login(User user, HttpServletRequest req) {
//
//    }


    @Override
    public ResponseEntity<Object> login(LoginUser user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = user.getUsername();
        String password = user.getPassword();
        Cookie[] res=request.getCookies();
        String  str=request.getRequestURI();


        if ("user".equals(username) && "password".equals(password)) {
            LoginUser user1=new LoginUser();
            user1.setUsername(user.getUsername());
            user1.setPassword(user.getPassword());
            loginUserRepository.save(user1);
            return ResponseEntity.ok("Login success");

        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @Override
        public ResponseEntity<Object> fetchMaster() {
           ResponseEntity<Object> list = supplierService.getMaster();
            return list;
        }

    @Override
    public ResponseEntity<Object> createMaster(SupplierDTO masterDTO) {
       ResponseEntity<Object> list=supplierService.createMaster(masterDTO);
       return list;
    }

    @Override
    public ResponseEntity<Object> getMaster(Long id) {
       ResponseEntity<Object> obj= supplierService.fetchMaster(id);
       return obj;
    }

    @Override
    public ResponseEntity<Object> updateMaster(Long id,SupplierDTO supplierDTO) {
        ResponseEntity<Object> record=supplierService.updateMaster(id,supplierDTO);
        return record;
    }

    @Override
    public ResponseEntity<Object> deleteMaster(Long id) {
        ResponseEntity<Object> str= supplierService.deleteMaster(id);
        return str;
    }
}
