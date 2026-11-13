package Estructuras;

public class Pila<T> {
    private Nodo<T> cima;
    private int size;

    public Pila() {
        this.cima = null;
        this.size = 0;
    }

    public void push(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (cima == null) {
            cima = nuevoNodo;
        } else {
            nuevoNodo.setSiguiente(cima);
            cima = nuevoNodo;
        }
        size++;
    }

    public T pop() {
        if (cima == null) {
            return null;
        }
        T dato = cima.getDato();
        cima = cima.getSiguiente();
        size--;
        return dato;
    }

    public T peek() {
        if (cima == null) {
            return null;
        }
        return cima.getDato();
    }

    public boolean isEmpty() {
        return cima == null;
    }

    public int getSize() {
        return size;
    }
}
