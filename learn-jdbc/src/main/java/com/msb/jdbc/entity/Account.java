package com.msb.jdbc.entity;

import java.io.Serializable;

/**
 * @Auther: Huanyu Lee
 * @Date: 2022/3/6  16:36
 * @Description: Account 实体类
 * @version: 1.0
 */
public class Account implements Serializable {
    private Integer aid;
    private String username;
    private Integer password;
    private Double money;
    public Account(){
    }

    public Account(Integer aid, String username, Integer password, Double money) {
        this.aid = aid;
        this.username = username;
        this.password = password;
        this.money = money;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "aid=" + aid +
                ", username='" + username + '\'' +
                ", password=" + password +
                ", money=" + money +
                '}';
    }
}
