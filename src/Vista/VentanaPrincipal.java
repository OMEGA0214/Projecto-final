package Vista;
import Modelo.Fintech;

import javax.swing.*;

    public class VentanaPrincipal extends JFrame {

        private Fintech fintech;

        public VentanaPrincipal() {

            fintech = new Fintech();

            setTitle("Sistema Fintech");
            setSize(800, 500);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            JTabbedPane pestañas = new JTabbedPane();

            pestañas.add("Usuarios", new PanelUsuarios(fintech));
            pestañas.add("Billeteras", new PanelBilleteras(fintech));
            pestañas.add("Operaciones", new PanelOperaciones(fintech));
            pestañas.add("Consultas", new PanelConsultas(fintech));

            add(pestañas);

            setVisible(true);
        }

    }
