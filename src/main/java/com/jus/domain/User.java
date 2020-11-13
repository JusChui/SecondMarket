package com.jus.domain;

import java.io.Serializable;

/**
 * @Auther: JusChui
 * @Date: 2020/11/9 17:27
 * @Description:
 */
public class User implements Serializable {

    private String id;
    private String email;
    private String password;
    private String telNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }
}
