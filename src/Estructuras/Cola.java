package Estructuras;

public class Cola<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int size;

    public Cola() {
        this.inicio = null;
        this.fin = null;
        this.size = 0;
    }

    public void encolar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (inicio == null) {
            inicio = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.setSiguiente(nuevoNodo);
            fin = nuevoNodo;
        }
        size++;
    }

    public T desencolar() {
        if (inicio == null) {
            return null;
        }
        T dato = inicio.getDato();
        inicio = inicio.getSiguiente();
        size--;
        return dato;
    }

    public T peek() {
        if (inicio == null) {
            return null;
        }
        return inicio.getDato();
    }

    public boolean isEmpty() {
        return inicio == null;
    }

    public int getSize() {
        return size;
    }
}
