package com.lan.website.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Ipset implements Serializable {
    private Integer keyId;

    private String ip;

    private String type;

    private String mark;

    private Date firsttime;

    private Date bantime;

    private Date totime;

    private static final long serialVersionUID = 1L;

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Date getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(Date firsttime) {
        this.firsttime = firsttime;
    }

    public Date getBantime() {
        return bantime;
    }

    public void setBantime(Date bantime) {
        this.bantime = bantime;
    }

    public Date getTotime() {
        return totime;
    }

    public void setTotime(Date totime) {
        this.totime = totime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", keyId=").append(keyId);
        sb.append(", ip=").append(ip);
        sb.append(", type=").append(type);
        sb.append(", mark=").append(mark);
        sb.append(", firsttime=").append(firsttime);
        sb.append(", bantime=").append(bantime);
        sb.append(", totime=").append(totime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}