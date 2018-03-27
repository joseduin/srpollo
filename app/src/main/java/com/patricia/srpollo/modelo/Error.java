package com.patricia.srpollo.modelo;

/**
 * Created by Jose on 3/3/2018.
 */

public class Error {

    private String error;

    public Error(String error) {
        this.error = error;
    }

    public Error() {}

    public String getMgs() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
