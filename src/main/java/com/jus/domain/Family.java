package com.jus.domain;

import java.util.List;

/**
 * @Auther: JusChui
 * @Date: 2020/11/27 10:25
 * @Description:
 */
public class Family {

    private String family_id;
    private String user_id;
    private Integer user_count;

    private List<User> users;

    public String getFamily_id() {
        return family_id;
    }

    public void setFamily_id(String family_id) {
        this.family_id = family_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_count() {
        return user_count;
    }

    public void setUser_count(Integer user_count) {
        this.user_count = user_count;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Family{" +
                "family_id='" + family_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_count=" + user_count +
                ", users=" + users +
                '}';
    }
}
