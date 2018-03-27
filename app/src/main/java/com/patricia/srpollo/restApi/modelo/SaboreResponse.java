package com.patricia.srpollo.restApi.modelo;

import com.patricia.srpollo.modelo.Sabore;

import java.util.ArrayList;

/**
 * Created by Jose on 4/3/2018.
 */

public class SaboreResponse {

    private ArrayList<Sabore> sabore = new ArrayList<>();

    public ArrayList<Sabore> getSabore() {
        return sabore;
    }

    public void setSabore(ArrayList<Sabore> sabore) {
        this.sabore = sabore;
    }
}
