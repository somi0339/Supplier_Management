package com.example.sp.jpa;

import com.example.sp.bean.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginUserRepository extends JpaRepository<LoginUser,Long> {
}
