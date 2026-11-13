package Estructuras;

import Modelo.Usuario;

public class Arista {

    private Usuario origen;

    private Usuario destino;

    private int frecuencia;

    public Arista(Usuario origen, Usuario destino) {
        this.origen = origen;
        this.destino = destino;
        this.frecuencia = 1;
    }



    public Usuario getOrigen() {
        return origen;
    }

    public void setOrigen(Usuario origen) {
        this.origen = origen;
    }

    public Usuario getDestino() {
        return destino;
    }

    public void setDestino(Usuario destino) {
        this.destino = destino;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
}
