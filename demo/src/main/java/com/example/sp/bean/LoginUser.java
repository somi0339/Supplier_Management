package com.example.sp.bean;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Getter
public class LoginUser {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column
private Long id;

    @Column
    private String username;
    @Column
    private String password;

    public Long getId(){
        return id;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
