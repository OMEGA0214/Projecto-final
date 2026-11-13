package Estructuras;

public class Lista<T> {
    public Nodo<T> inicio;
    public Nodo<T> fin;
    private int size;

    public Lista() {
        this.inicio = null;
        this.fin = null;
        this.size = 0;
    }

    public void agregarFinal(T dato) {
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

    public void agregarInicio(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (inicio == null) {
            inicio = nuevoNodo;
            fin = nuevoNodo;
        } else {
            nuevoNodo.setSiguiente(inicio);
            inicio = nuevoNodo;
        }
        size++;
    }

    public boolean esVacio() {
        return inicio == null;
    }

    public T obtener(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Nodo<T> actual = inicio;
        for (int i = 0; i < index; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    public void eliminarInicio() {
        if (inicio != null) {
            inicio = inicio.getSiguiente();
            size--;
            if (inicio == null) {
                fin = null;
            }
        }
    }

    public void eliminarFin() {
        if (inicio != null) {
            if (inicio == fin) {
                inicio = null;
                fin = null;
            } else {
                Nodo<T> actual = inicio;
                while (actual.getSiguiente() != fin) {
                    actual = actual.getSiguiente();
                }
                actual.setSiguiente(null);
                fin = actual;
            }
            size--;
        }
    }

    public T primero() {
        if (inicio == null) {
            return null;
        }
        return inicio.getDato();
    }

    public void insertar(T dato, int index) {
        if (index < 0 || index > size) {
            return;
        }
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (index == 0) {
            nuevoNodo.setSiguiente(inicio);
            inicio = nuevoNodo;
            if (fin == null) {
                fin = nuevoNodo;
            }
        } else {
            Nodo<T> actual = inicio;
            for (int i = 0; i < index - 1; i++) {
                actual = actual.getSiguiente();
            }
            nuevoNodo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevoNodo);
            if (nuevoNodo.getSiguiente() == null) {
                fin = nuevoNodo;
            }
        }
        size++;
    }

    public T ultimo() {
        if (fin == null) {
            return null;
        }
        return fin.getDato();
    }

    public T buscar (T dato) {
        Nodo<T> actual = inicio;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    public Nodo<T> getInicio() {
        return inicio;
    }

    public void setInicio(Nodo<T> inicio) {
        this.inicio = inicio;
    }

    public Nodo<T> getFin() {
        return fin;
    }

    public void setFin(Nodo<T> fin) {
        this.fin = fin;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


}
