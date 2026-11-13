package Estructuras;

import Modelo.OperacionProgramada;

public class ColaPrioridad {
    public Lista<OperacionProgramada> operaciones;

    public ColaPrioridad() {
        this.operaciones = new Lista<>();
    }

    public boolean estaVacia() {
        return operaciones.esVacio();
    }

    public void encolar(OperacionProgramada operacion) {
        if (operaciones.esVacio()) {
            operaciones.agregarInicio(operacion);
            return;
        }

        if (operacion.getFechaEjecucion().compareTo(
                operaciones.getInicio().getDato().getFechaEjecucion()) < 0) {

            operaciones.agregarInicio(operacion);
            return;
        }

        int pos = 0;

        while (pos < operaciones.getSize()
                && operacion.getFechaEjecucion().compareTo(
                operaciones.obtener(pos).getFechaEjecucion()) >= 0) {

            pos++;
        }

        operaciones.insertar(operacion, pos);
    }

    public OperacionProgramada desencolar() {
        if (operaciones.esVacio()) {
            return null;
        }
        OperacionProgramada operacion = operaciones.getInicio().getDato();
        operaciones.eliminarInicio();
        return operacion;
    }

    public OperacionProgramada peek() {
        if (operaciones.esVacio()) {
            return null;
        }
        return operaciones.primero();
    }

    public Lista<OperacionProgramada> getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(Lista<OperacionProgramada> operaciones) {
        this.operaciones = operaciones;
    }
}
