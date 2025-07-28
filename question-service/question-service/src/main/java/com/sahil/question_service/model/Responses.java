package com.sahil.question_service.model;

public class Responses {

    private Integer id;
    private String response;

    public Responses() {
    }

    public Responses(Integer id, String response) {
        this.id = id;
        this.response = response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }



}
