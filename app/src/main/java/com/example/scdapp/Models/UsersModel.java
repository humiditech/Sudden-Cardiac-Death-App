package com.example.scdapp.Models;


public class UsersModel {
    String addr, age, dName, emailAddr, fName, nName, uid;

    public UsersModel(String addr, String age, String dName, String emailAddr, String fName, String nName, String uid) {
        this.addr = addr;
        this.age = age;
        this.dName = dName;
        this.emailAddr = emailAddr;
        this.fName = fName;
        this.nName = nName;
        this.uid = uid;
    }

    public UsersModel() {
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getnName() {
        return nName;
    }

    public void setnName(String nName) {
        this.nName = nName;
    }

    public String getUid(){
        return uid;
    }

    public void setUid(String uid){
        this.uid = uid;
    }
}
