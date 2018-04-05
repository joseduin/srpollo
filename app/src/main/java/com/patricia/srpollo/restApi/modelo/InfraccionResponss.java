package com.patricia.srpollo.restApi.modelo;

import com.patricia.srpollo.modelo.Infraccion;

import java.util.ArrayList;

/**
 * Created by SPF1 on 4/5/2018.
 */

public class InfraccionResponss {

    private ArrayList<Infraccion> infraccions = new ArrayList<>();

    public ArrayList<Infraccion> getInfraccions() {
        return infraccions;
    }

    public void setInfraccions(ArrayList<Infraccion> infraccions) {
        this.infraccions = infraccions;
    }
}
