package service.impl;

import service.Auth;

public class AuthImpl implements Auth {
    public boolean auth(String username, String password) {
        return (username.equals("admin") && password.equals("admin"));
    }
}