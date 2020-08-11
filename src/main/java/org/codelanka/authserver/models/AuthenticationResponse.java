package org.codelanka.authserver.models;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
    private String authToken;

    private  String refreshToken;

    private String userId;

    private String userRole;


    public String getAuthToken() {
        return authToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }




}
