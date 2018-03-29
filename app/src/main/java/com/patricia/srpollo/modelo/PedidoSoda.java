package com.patricia.srpollo.modelo;

/**
 * Created by patricia on 1/25/2018.
 */

public class PedidoSoda {

    private String producto;
    int uds, cantpaq,totalPaq, cantUds;

    public PedidoSoda(String producto, int uds, int cantpaq, int totalPaq, int cantUds) {
        this.producto = producto;
        this.uds = uds;
        this.cantpaq = cantpaq;
        this.totalPaq = totalPaq;
        this.cantUds = cantUds;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getUds() {
        return uds;
    }

    public void setUds(int uds) {
        this.uds = uds;
    }

    public int getCantpaq() {
        return cantpaq;
    }

    public void setCantpaq(int cantpaq) {
        this.cantpaq = cantpaq;
    }

    public int getTotalPaq() {
        return totalPaq;
    }

    public void setTotalPaq(int totalPaq) {
        this.totalPaq = totalPaq;
    }

    public int getCantUds() {
        return cantUds;
    }

    public void setCantUds(int cantUds) {
        this.cantUds = cantUds;
    }
}
