package com.lyonguyen.news.services;

public interface SecurityService {

    boolean isLoggedIn();

    // String findLoggedInUsername();

    // void autologin(String username, String password);

    // boolean hasRole(String role);

    String checkRole();

}
