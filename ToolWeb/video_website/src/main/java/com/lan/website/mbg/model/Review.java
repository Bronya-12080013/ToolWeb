package com.lan.website.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {
    private Integer keyId;

    private String reviewid;

    private String context;

    private Integer courseid;

    private String username;

    private Date time;

    private int lable;

    private String sex;

    private Integer vip;

    private static final long serialVersionUID = 1L;

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public String getReviewid() {
        return reviewid;
    }

    public void setReviewid(String reviewid) {
        this.reviewid = reviewid;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getLable() {
        return lable;
    }

    public void setLable(int lable) {
        this.lable = lable;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getVip() {
        return vip;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", keyId=").append(keyId);
        sb.append(", reviewid=").append(reviewid);
        sb.append(", context=").append(context);
        sb.append(", courseid=").append(courseid);
        sb.append(", username=").append(username);
        sb.append(", time=").append(time);
        sb.append(", lable=").append(lable);
        sb.append(", sex=").append(sex);
        sb.append(", vip=").append(vip);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}