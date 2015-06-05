package com.lifeisle.Utils;

import java.util.Arrays;

/**
 * @author Jekton Luo
 * @version 0.01 6/4/2015.
 */
public class UserInfo {

    private String username;
    private char[] password;
    private int userID;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", password=" + Arrays.toString(password) +
                ", userID=" + userID +
                '}';
    }
}
