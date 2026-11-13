package Estructuras;

import Modelo.Usuario;

public class NodoArbol {

    private Usuario dato;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    public NodoArbol(Usuario dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Usuario getDato() {
        return dato;
    }

    public void setDato(Usuario dato) {
        this.dato = dato;
    }

    public NodoArbol getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbol izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbol getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbol derecho) {
        this.derecho = derecho;
    }
}
