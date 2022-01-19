package com.selim.springboot.core;

public class Result {//Todo:Standart response
    private  String message;

    public Result(String message) {
        this.message = message;
    }

    public Result() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
