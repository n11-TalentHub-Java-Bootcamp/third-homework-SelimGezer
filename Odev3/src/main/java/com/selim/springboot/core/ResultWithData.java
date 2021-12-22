package com.selim.springboot.core;

public class ResultWithData<T> extends Result{//Todo: Data içeren response

    private T data;

    public ResultWithData(String message, T data) {
        super(message);
        this.data=data;
    }

    public ResultWithData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
