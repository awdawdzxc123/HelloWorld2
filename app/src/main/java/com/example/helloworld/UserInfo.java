package com.example.helloworld;

import java.io.Serializable;

class UserInfo implements Serializable {
     private  String userName;
     private  String Password;
     private  String sex;
     private  String shone;
     private  String  sms;

     public UserInfo(String userName, String password, String sex, String shone, String  sms) {
         this.userName = userName;
         Password = password;
         this.sex = sex;
         this.shone = shone;
         this.sms = sms;
     }

     public String getUserName() {
         return userName;
     }

     public void setUserName(String userName) {
         this.userName = userName;
     }

     public String getPassword() {
         return Password;
     }

     public void setPassword(String password) {
         Password = password;
     }

     public String getSex() {
         return sex;
     }

     public void setSex(String sex) {
         this.sex = sex;
     }

     public String getShone() {
         return shone;
     }

     public void setShone(String shone) {
         this.shone = shone;
     }

     public String  getSms() {
         return sms;
     }

     public void setSms(String sms) {
         this.sms = sms;
     }
 }
