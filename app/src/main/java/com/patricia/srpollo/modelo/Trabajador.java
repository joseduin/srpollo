package com.patricia.srpollo.modelo;

/**
 * Created by Jose on 5/3/2018.
 */

public class Trabajador {

    private int id;
    private int rol;
    private String nombre;
    private String apellido;
    private int cargo_id;
    private String identificacion;
    private int almacen_id;
    private int turno_id;
    private String usuario;

    private Turno turno;
    private Cargo cargo;
    private Almacen almacen;

    public Trabajador(int id, int rol, String nombre, String apellido, int cargo_id, String identificacion, int almacen_id, String usuario) {
        this.id = id;
        this.rol = rol;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo_id = cargo_id;
        this.identificacion = identificacion;
        this.almacen_id = almacen_id;
        this.usuario = usuario;
    }

    public Trabajador() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCargo_id() {
        return cargo_id;
    }

    public void setCargo_id(int cargo_id) {
        this.cargo_id = cargo_id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public int getAlmacen_id() {
        return almacen_id;
    }

    public void setAlmacen_id(int almacen_id) {
        this.almacen_id = almacen_id;
    }

    public int getTurno_id() {
        return turno_id;
    }

    public void setTurno_id(int turno_id) {
        this.turno_id = turno_id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }
}
