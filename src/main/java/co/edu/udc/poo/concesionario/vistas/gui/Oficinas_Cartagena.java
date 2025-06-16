package co.edu.udc.poo.concesionario.vistas.gui;

import co.edu.udc.poo.concesionario.modelo.crud.OficinaCRUD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Oficinas_Cartagena extends JFrame {

    // Constructor de la clase
    public Oficinas_Cartagena() throws Exception {
        Dimension medPantalla = new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE);

        setTitle("CASTAÑO & TOVAR Motor Company");
        setSize(new Dimension(700,400));
        setMinimumSize(new Dimension(700,400));
        setPreferredSize(medPantalla);
        setMaximumSize(medPantalla);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        // PANEL SUPERIOR
        JPanel panelSuperior = new JPanel();
        GroupLayout layoutSuperior = new GroupLayout(panelSuperior);
        panelSuperior.setLayout(layoutSuperior);
        panelSuperior.setPreferredSize(new Dimension(600, 80));
        panelSuperior.setBackground(new Color(41, 83, 197, 255));

        JLabel tituloPrincipal = new JLabel("OFICINAS");
        tituloPrincipal.setFont(new Font("Tahoma", Font.BOLD, 44));
        tituloPrincipal.setForeground(Color.black);

        JLabel tituloSecundario = new JLabel("Cartagena");
        tituloSecundario.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tituloSecundario.setForeground(Color.black);

        layoutSuperior.setAutoCreateGaps(true);
        layoutSuperior.setAutoCreateContainerGaps(true);

        layoutSuperior.setHorizontalGroup(
                layoutSuperior.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 200, 250)
                        .addComponent(tituloPrincipal)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 10)
                        .addComponent(tituloSecundario)
        );
        layoutSuperior.setVerticalGroup(
                layoutSuperior.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(tituloPrincipal)
                        .addComponent(tituloSecundario)
        );

        // PANEL INFERIOR CON IMAGEN DE FONDO
        JPanel panelInferior = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Cargar la imagen como fondo
                ImageIcon imagenFondo = new ImageIcon("src/main/resources/fondo.png");

                g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));



        OficinaCRUD oficinaCRUD = new OficinaCRUD();
        JLabel tituloAux1 = new JLabel("Sedes");
        tituloAux1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tituloAux1.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloAux1.setForeground(Color.blue);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 150, 20));
        panelBotones.setOpaque(false); // Hacer transparente el fondo de los botones
        JButton boton1 = new JButton("Sede Norte");
        boton1.setBackground(new Color(148, 196, 255));
        boton1.setForeground(Color.BLUE);
        boton1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        boton1.addActionListener(e -> {
            this.dispose();
            new SedeNorte().setVisible(true);
        });
        JButton boton2 = new JButton("Sede Sur");
        boton2.setBackground(new Color(148, 196, 255));
        boton2.setForeground(Color.BLUE);
        boton2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        boton2.addActionListener(e -> {
            this.dispose();
            new SedeSur().setVisible(true);
        });
        JButton boton3 = new JButton("Acciones");
        boton3.setBackground(new Color(148, 196, 255));
        boton3.setForeground(Color.BLUE);
        boton3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));

// Crear el menú emergente (submenú)
        JPopupMenu subMenu = new JPopupMenu();

// Crear las opciones del submenú
        JMenuItem opcionAgregar = new JMenuItem("Agregar");
        JMenuItem opcionEditar = new JMenuItem("Editar");
        JMenuItem opcionEliminar = new JMenuItem("Eliminar");

// Agregar opciones al submenú
        subMenu.add(opcionAgregar);
        subMenu.add(opcionEditar);
        subMenu.add(opcionEliminar);

// Añadir funcionalidad a las opciones del submenú
        opcionAgregar.addActionListener(e -> JOptionPane.showMessageDialog(null, "Agregar seleccionado"));
        opcionEditar.addActionListener(e -> JOptionPane.showMessageDialog(null, "Editar seleccionado"));
        opcionEliminar.addActionListener(e -> JOptionPane.showMessageDialog(null, "Eliminar seleccionado"));

// Mostrar el submenú al hacer clic en el botón
        boton3.addActionListener(e -> {
            // Mostrar el submenú en la posición relativa al propio botón (debajo del botón)
            subMenu.show(boton3, 0, boton3.getHeight());
        });


        panelBotones.add(boton1);
        panelBotones.add(boton2);
        panelBotones.add(boton3);
        panelInferior.add(tituloAux1);
        panelInferior.add(Box.createVerticalStrut(30));
        panelInferior.add(Box.createVerticalStrut(15));
        panelInferior.add(panelBotones);
        boton1.setPreferredSize(new Dimension(150, 30));
        boton2.setPreferredSize(new Dimension(150,30));
        boton3.setPreferredSize(new Dimension(150,30));

        // PANEL INFERIOR CON BÚSQUEDA, SOPORTE Y BOTÓN ATRÁS
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBackground(Color.LIGHT_GRAY);
        panelBusqueda.setPreferredSize(new Dimension(700, 50));

        // Campo de búsqueda
        JTextField campoBusqueda = new JTextField("Buscar sede: ");
        campoBusqueda.setPreferredSize(new Dimension(400, 30));

        // Botón de búsqueda
        ImageIcon iconoBuscar = new ImageIcon("src/main/resources/busqueda.png");
        JButton btnBuscar = new JButton(iconoBuscar);
        btnBuscar.setPreferredSize(new Dimension(40, 30));

        // Botón de soporte (icono)
        ImageIcon iconoSoporte = new ImageIcon("src/main/resources/soporte.png");
        JButton btnSoporte = new JButton(iconoSoporte);
        btnSoporte.setPreferredSize(new Dimension(30, 30));
        btnSoporte.setFocusPainted(false);
        btnSoporte.setBackground(new Color(148, 196, 255, 255));
        btnSoporte.setToolTipText("Soporte técnico");

        // Botón "Atrás"
        JButton btnAtras = new JButton("Atras");
        btnAtras.setPreferredSize(new Dimension(100, 30));
        btnAtras.setBackground(new Color(148, 196, 255, 255));
        btnAtras.setForeground(Color.blue);
        btnAtras.addActionListener(e -> {
            dispose();
            try {
                new Cartagena();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, 
                        "Error al volver: " + ex.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Agregar componentes al panel inferior
        panelBusqueda.add(campoBusqueda);
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnSoporte);
        panelBusqueda.add(Box.createHorizontalStrut(20));
        panelBusqueda.add(btnAtras);

        // ENSAMBLAR LOS PANELES
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelInferior, BorderLayout.CENTER);
        panelPrincipal.add(panelBusqueda, BorderLayout.SOUTH);

        add(panelPrincipal);
        setVisible(true);

    }

    public static void main(String[] args) throws Exception {
        new Oficinas_Cartagena();
    }
}
