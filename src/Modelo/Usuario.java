package Modelo;

import Estructuras.Lista;
import Estructuras.Pila;

public class Usuario {

    public String id;
    public String nombre;
    public String correo;
    public int puntos;

    public NivelUsuario nivelUsuario;
    public Lista<Billetera> billeteras;
    public Lista<Notificacion> notificaciones;
    public Lista<Transaccion> transacciones;
    public Pila<Transaccion> reversibles;

    public Usuario(String id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;

        this.puntos = 0;
        this.nivelUsuario = NivelUsuario.BRONCE;

        this.billeteras = new Lista<>();
        this.notificaciones = new Lista<>();
        this.transacciones = new Lista<>();
        this.reversibles = new Pila<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public NivelUsuario getNivelUsuario() {
        return nivelUsuario;
    }

    public void setNivelUsuario(NivelUsuario nivelUsuario) {
        this.nivelUsuario = nivelUsuario;
    }

    public Lista<Billetera> getBilleteras() {
        return billeteras;
    }

    public void setBilleteras(Lista<Billetera> billeteras) {
        this.billeteras = billeteras;
    }

    public Lista<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(Lista<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public Lista<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(Lista<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public Pila<Transaccion> getReversibles() {
        return reversibles;
    }

    public void setReversibles(Pila<Transaccion> reversibles) {
        this.reversibles = reversibles;
    }

}
