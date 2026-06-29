package com.example.sp.dto;

public class RefreshTokenDTO {

    private String refreshToken;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;

   public String getRefreshToken(){
       return refreshToken;
   }
   public String setRefreshToken(String refreshToken){
       this.refreshToken=refreshToken;
       return this.refreshToken;
   }



}
