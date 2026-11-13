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

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }
}
