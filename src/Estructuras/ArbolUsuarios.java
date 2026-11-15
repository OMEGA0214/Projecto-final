package Estructuras;

import Modelo.Usuario;

public class ArbolUsuarios {
    public NodoArbol raiz;

    public ArbolUsuarios() {
        this.raiz = null;
    }

    public void insertar(Usuario usuario) {
        if(raiz == null) {
            raiz = new NodoArbol(usuario);
        } else {
            insertarRecursivo(raiz, usuario);
        }
    }

    private void insertarRecursivo(NodoArbol nodo, Usuario usuario) {
        if(usuario.getPuntos() <= nodo.getDato().getPuntos()) {
            if(nodo.getIzquierdo() == null) {
                nodo.setIzquierdo(new NodoArbol(usuario));
            } else {
                insertarRecursivo(nodo.getIzquierdo(), usuario);
            }
        } else {
            if(nodo.getDerecho() == null) {
                nodo.setDerecho(new NodoArbol(usuario));
            } else {
                insertarRecursivo(nodo.getDerecho(), usuario);
            }
        }
    }

    public Usuario buscar(int puntos) {
        return buscarRecursivo(raiz, puntos);
    }

    private Usuario buscarRecursivo(NodoArbol nodo, int puntos) {
        if(nodo == null) {
            return null;
        }
        if(nodo.getDato().getPuntos() == puntos) {
            return nodo.getDato();
        }
        if(puntos < nodo.getDato().getPuntos()) {
            return buscarRecursivo(nodo.getIzquierdo(), puntos);
        } else {
            return buscarRecursivo(nodo.getDerecho(), puntos);
        }
    }

    public void inOrden() {
        inOrdenRecursivo(raiz);
    }

    private void inOrdenRecursivo(NodoArbol nodo) {

        if (nodo != null) {

            inOrdenRecursivo(nodo.getIzquierdo());

            System.out.println(
                    nodo.getDato().getNombre()
                            + " - "
                            + nodo.getDato().getPuntos());

            inOrdenRecursivo(nodo.getDerecho());
        }
    }

    public void eliminar(Usuario dato){
        raiz = eliminarRecursivo(raiz, dato);
    }

    private NodoArbol eliminarRecursivo(NodoArbol raiz, Usuario dato) {
        if (raiz == null) {
            return null;
        }

        if (dato.getPuntos() < raiz.getDato().getPuntos()) {
            raiz.setIzquierdo(eliminarRecursivo(raiz.getIzquierdo(), dato));
        } else if (dato.getPuntos() > raiz.getDato().getPuntos()) {
            raiz.setDerecho(eliminarRecursivo(raiz.getDerecho(), dato));
        } else {
            if (raiz.getIzquierdo() == null) {
                return raiz.getDerecho();
            } else if (raiz.getDerecho() == null) {
                return raiz.getIzquierdo();
            }
            NodoArbol sucesor = obtenerSucesorInorder(raiz.getDerecho());
            raiz.setDato(sucesor.getDato());
            raiz.setDerecho(eliminarRecursivo(raiz.getDerecho(), sucesor.getDato()));
        }
        return raiz;
    }

    private NodoArbol obtenerSucesorInorder(NodoArbol nodo) {
        NodoArbol actual = nodo;
        while (actual.getIzquierdo() != null) {
            actual = actual.getIzquierdo();
        }
        return actual;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }
}
