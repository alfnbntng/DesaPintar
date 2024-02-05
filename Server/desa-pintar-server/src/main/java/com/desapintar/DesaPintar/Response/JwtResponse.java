package com.desapintar.DesaPintar.Response;

import com.desapintar.DesaPintar.Model.UserModel;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final UserModel userData;

    public JwtResponse(String jwttoken, UserModel userData) {
        this.jwttoken = jwttoken;
        this.userData = userData;
    }

    public UserModel getUserData() {return userData; }

    public String getToken() {
        return this.jwttoken;
    }
}