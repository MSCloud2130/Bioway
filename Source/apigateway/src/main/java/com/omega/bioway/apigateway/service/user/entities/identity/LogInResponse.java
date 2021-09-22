package com.omega.bioway.apigateway.service.user.entities.identity;

public class LogInResponse{
    private String id;
    private String token;

    public LogInResponse() {
    }

    public LogInResponse(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}