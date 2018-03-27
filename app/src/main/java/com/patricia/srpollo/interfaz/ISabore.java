package com.patricia.srpollo.interfaz;

import com.patricia.srpollo.modelo.Sabore;

import java.util.HashMap;

/**
 * Created by Jose on 24/2/2018.
 */

public interface ISabore {

    public void cargarComboSabore(String[] sabores);
    public void cargarMapSabore(HashMap<String, Sabore> map);

}
