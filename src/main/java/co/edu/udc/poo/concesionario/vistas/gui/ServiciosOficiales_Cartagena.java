package co.edu.udc.poo.concesionario.vistas.gui;

import co.edu.udc.poo.concesionario.modelo.crud.ServicioOficialCRUD;

import javax.swing.*;
import java.awt.*;

public class ServiciosOficiales_Cartagena extends JFrame {

    // Constructor de la clase
    public ServiciosOficiales_Cartagena() throws Exception {
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

        JLabel tituloPrincipal = new JLabel("SERVICIOS OFICIALES");
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
        panelInferior.setBorder(BorderFactory.createEmptyBorder(55, 15, 15, 15));

        ServicioOficialCRUD servicioOficialCRUD = new ServicioOficialCRUD();
        JLabel tituloAux1 = new JLabel("Sedes" );
        tituloAux1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tituloAux1.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloAux1.setForeground(Color.blue);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 150, 20));
        panelBotones.setOpaque(false); // Hacer transparente el fondo de los botones
        JButton boton1 = new JButton("Sede Alta");
        boton1.setFont(new Font("Tahoma", Font.BOLD, 12));
        boton1.setBackground(new Color(41, 83, 197, 255));
        boton1.setForeground(Color.BLUE);
        boton1.addActionListener(e -> {
            this.dispose();
            new SedeAlta().setVisible(true);
        });
        
        JButton boton2 = new JButton("Sede Baja");
        boton2.setFont(new Font("Tahoma", Font.BOLD, 12));
        boton2.setBackground(new Color(41, 83, 197, 255));
        boton2.setForeground(Color.BLUE);
        boton2.addActionListener(e -> {
            this.dispose();
            new SedeBaja().setVisible(true);
        });
        JButton boton3 = new JButton("Acciones");
        boton3.setFont(new Font("Tahoma", Font.BOLD, 12));
        boton3.setBackground(new Color(41, 83, 197, 255));
        boton3.setForeground(Color.BLUE);

// Crear el menú emergente (submenú)
        JPopupMenu subMenu = new JPopupMenu();

// Crear las opciones del submenú
        JMenuItem opcionAgregar = new JMenuItem("Agregar");
        opcionAgregar.setFont(new Font("Tahoma", Font.BOLD, 12));
        opcionAgregar.setForeground(Color.BLUE);
        
        JMenuItem opcionEditar = new JMenuItem("Editar");
        opcionEditar.setFont(new Font("Tahoma", Font.BOLD, 12));
        opcionEditar.setForeground(Color.BLUE);
        
        JMenuItem opcionEliminar = new JMenuItem("Eliminar");
        opcionEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
        opcionEliminar.setForeground(Color.BLUE);

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

        // PANEL DE BÚSQUEDA
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setPreferredSize(new Dimension(700, 50));
        panelBusqueda.setBorder(BorderFactory.createEmptyBorder(10, 55, 10, 10));
        panelBusqueda.setBackground(Color.LIGHT_GRAY);

        JTextField campoBusqueda = new JTextField("Buscar sede: ");
        campoBusqueda.setPreferredSize(new Dimension(400, 30));
        
        // Botón de búsqueda
        ImageIcon iconoBuscar = new ImageIcon("src/main/resources/busqueda.png");
        JButton btnSearch = new JButton(iconoBuscar);
        btnSearch.setPreferredSize(new Dimension(40, 30));
        
        // Botón de soporte (icono)
        ImageIcon iconoSoporte = new ImageIcon("src/main/resources/soporte.png");
        JButton btnSoporte = new JButton(iconoSoporte);
        btnSoporte.setPreferredSize(new Dimension(30, 30));
        btnSoporte.setFocusPainted(false);
        btnSoporte.setBackground(new Color(148, 196, 255, 255));
        btnSoporte.setToolTipText("Soporte técnico");
        
        // Botón "Atrás"
        JButton btnAtras = new JButton("ATRAS");
        btnAtras.setPreferredSize(new Dimension(100, 30));
        btnAtras.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAtras.setBackground(new Color(148, 196, 255, 255));
        btnAtras.setForeground(Color.BLUE);
        btnAtras.addActionListener(e -> {
            dispose();
            try {
                new Cartagena().setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, 
                    "Error al volver: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panelBusqueda.add(campoBusqueda);
        panelBusqueda.add(btnSearch);
        panelBusqueda.add(Box.createHorizontalStrut(10));
        panelBusqueda.add(btnSoporte);
        panelBusqueda.add(Box.createHorizontalGlue());
        panelBusqueda.add(btnAtras);

        // ENSAMBLAR LOS PANELES
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelInferior, BorderLayout.CENTER);
        panelPrincipal.add(panelBusqueda, BorderLayout.SOUTH);

        add(panelPrincipal);
        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        new ServiciosOficiales_Cartagena();
    }
}
