package Estructuras;

import Modelo.Usuario;

public class Grafo{
    public Lista<Usuario> vertices;
    public Lista<Arista> aristas;

    public Grafo() {
        this.vertices = new Lista<>();
        this.aristas = new Lista<>();
    }

    public void agregarVertice(Usuario usuario) {
        vertices.agregarFinal(usuario);
    }

    public void agregarArista(Usuario origen, Usuario destino) {
        for (int i = 0; i < aristas.getSize(); i++) {

            Arista actual = aristas.obtener(i);

            if (actual.getOrigen().equals(origen)
                    && actual.getDestino().equals(destino)) {

                actual.setFrecuencia(
                        actual.getFrecuencia() + 1);

                return;
            }
        }
        aristas.agregarFinal(
                new Arista(origen, destino));
    }
    public void setAristas(Lista<Arista> aristas) {
        this.aristas = aristas;
    }

    public void setVertices(Lista<Usuario> vertices) {
        this.vertices = vertices;
    }

}
