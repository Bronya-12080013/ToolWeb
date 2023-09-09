package com.lan.website.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String id;

    private String sex;

    private String phone;

    private String mail;

    private String vx;

    private String nickname;

    private String username;

    private String password;

    // 身份: 如"admin"
    private String mission;

    private String buycase;

    private String mycase;

    private int collect;

    private String education;

    private Date vip;

    private Date fristtime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getVx() {
        return vx;
    }

    public void setVx(String vx) {
        this.vx = vx;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getBuycase() {
        return buycase;
    }

    public void setBuycase(String buycase) {
        this.buycase = buycase;
    }

    public String getMycase() {
        return mycase;
    }

    public void setMycase(String mycase) {
        this.mycase = mycase;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Date getVip() {
        return vip;
    }

    public void setVip(Date vip) {
        this.vip = vip;
    }

    public Date getFristtime() {
        return fristtime;
    }

    public void setFristtime(Date fristtime) {
        this.fristtime = fristtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sex=").append(sex);
        sb.append(", phone=").append(phone);
        sb.append(", mail=").append(mail);
        sb.append(", vx=").append(vx);
        sb.append(", nickname=").append(nickname);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", mission=").append(mission);
        sb.append(", buycase=").append(buycase);
        sb.append(", mycase=").append(mycase);
        sb.append(", collect=").append(collect);
        sb.append(", education=").append(education);
        sb.append(", vip=").append(vip);
        sb.append(", fristtime=").append(fristtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}