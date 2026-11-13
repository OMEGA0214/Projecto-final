package Modelo;

import Estructuras.ArbolUsuarios;
import Estructuras.Cola;
import Estructuras.ColaPrioridad;
import Estructuras.Grafo;

import java.util.HashMap;

public class Fintech {
    private HashMap<String, Usuario> usuarios;

    private HashMap<String, Billetera> billeteras;

    private ColaPrioridad operacionesProgramadas;

    private Cola notificaciones;

    private ArbolUsuarios usuariosPorPuntos;

    private Grafo grafoTransferencias;

    public Fintech() {
    }


}
