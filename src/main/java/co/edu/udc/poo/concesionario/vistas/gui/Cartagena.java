package co.edu.udc.poo.concesionario.vistas.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Cartagena extends JFrame {

    // Constructor de la clase
    public Cartagena() {
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

        JLabel tituloPrincipal = new JLabel("CASTAÑO & TOVAR");
        tituloPrincipal.setFont(new Font("Tahoma", Font.BOLD, 44));
        tituloPrincipal.setForeground(Color.black);


        JLabel tituloSecundario = new JLabel("Motor Company");
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

        JLabel tituloAux1 = new JLabel("Su concesionario de confianza!");
        tituloAux1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tituloAux1.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloAux1.setForeground(Color.blue);

        JLabel tituloAux2 = new JLabel("CARTAGENA");
        tituloAux2.setFont(new Font("Tahoma", Font.PLAIN, 24));
        tituloAux2.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloAux2.setForeground(Color.blue);

        // Panel para contener los botones con espacio uniforme
        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 50, 0));
        panelBotones.setOpaque(false);
        panelBotones.setMaximumSize(new Dimension(900, 50));
        
        // Crear un panel interno para cada botón para mejor control del espaciado
        JPanel panelBoton1 = new JPanel();
        panelBoton1.setOpaque(false);
        JPanel panelBoton2 = new JPanel();
        panelBoton2.setOpaque(false);
        JPanel panelBoton3 = new JPanel();
        panelBoton3.setOpaque(false);
        
        JButton boton1 = crearBoton("OFICINAS");
        boton1.setPreferredSize(new Dimension(200, 40));
        boton1.addActionListener(e -> {
            try {
                dispose();
                new Oficinas_Cartagena().setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                    "Error al abrir las oficinas de Cartagena: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                setVisible(true);
            }
        });

        JButton boton2 = crearBoton("SERVICIOS OFICIALES");
        boton2.setFont(new Font("Arial", Font.BOLD, 12));
        boton2.setPreferredSize(new Dimension(200, 40));
        boton2.addActionListener(e -> {
            try {
                dispose();
                new ServiciosOficiales_Cartagena().setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                    "Error al abrir los servicios oficiales de Cartagena: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                setVisible(true);
            }
        });

        JButton boton3 = crearBoton("BODEGA");
        boton3.setPreferredSize(new Dimension(200, 40));
        boton3.addActionListener(e -> {
            try {
                dispose();
                new Bodega_Cartagena();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                    "Error al abrir Sucursales: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Agregar botones a sus respectivos paneles
        panelBoton1.add(boton1);
        panelBoton2.add(boton2);
        panelBoton3.add(boton3);
        
        // Agregar paneles de botones al panel principal
        panelBotones.add(panelBoton1);
        panelBotones.add(panelBoton2);
        panelBotones.add(panelBoton3);
        
        // Configurar el panel principal
        JPanel panelContenedorBotones = new JPanel();
        panelContenedorBotones.setLayout(new BoxLayout(panelContenedorBotones, BoxLayout.Y_AXIS));
        panelContenedorBotones.setOpaque(false);
        panelContenedorBotones.add(Box.createVerticalGlue());
        panelContenedorBotones.add(panelBotones);
        panelContenedorBotones.add(Box.createVerticalGlue());
        
        // Agregar componentes al panel inferior
        panelInferior.add(tituloAux1);
        panelInferior.add(Box.createVerticalStrut(30));
        panelInferior.add(tituloAux2);
        panelInferior.add(Box.createVerticalStrut(30));
        panelInferior.add(panelContenedorBotones);
        // ENSAMBLAR LOS PANELES
        // PANEL INFERIOR CON BÚSQUEDA, SOPORTE Y BOTÓN ATRÁS
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBackground(Color.LIGHT_GRAY);
        panelBusqueda.setPreferredSize(new Dimension(700, 50));

        // Campo de búsqueda
        JTextField campoBusqueda = new JTextField("Buscar...");
        campoBusqueda.setPreferredSize(new Dimension(400, 30));

        // Botón de búsqueda
        ImageIcon iconoBuscar = new ImageIcon("src/main/resources/busqueda.png");
        JButton btnBuscar = new JButton(iconoBuscar);
        btnBuscar.setPreferredSize(new Dimension(40, 30));
        btnBuscar.setBackground(new Color(148, 196, 255));

        // Botón de soporte (icono)
        ImageIcon iconoSoporte = new ImageIcon("src/main/resources/soporte.png");
        JButton btnSoporte = new JButton(iconoSoporte);
        btnSoporte.setPreferredSize(new Dimension(30, 30));
        btnSoporte.setFocusPainted(false);
        btnSoporte.setBackground(new Color(148, 196, 255));
        btnSoporte.setToolTipText("Soporte técnico");
        btnSoporte.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Soporte Técnico\nPara asistencia, contacte a:\nCorreo: ArrietasoporteTecnico@concesionario.com\nTeléfono: 3106459508",
                "Soporte", JOptionPane.INFORMATION_MESSAGE);
        });

        // Botón "Atrás"
        JButton btnAtras = crearBoton("ATRAS");
        btnAtras.setPreferredSize(new Dimension(100, 30));
        btnAtras.addActionListener(e -> {
            try {
                dispose();
                new VentanaInicio().setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, 
                    "Error al volver a la ventana principal: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
                setVisible(true);
            }
        });

        // Agregar componentes al panel inferior
        panelBusqueda.add(Box.createHorizontalStrut(20));
        panelBusqueda.add(campoBusqueda);
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(Box.createHorizontalStrut(10));
        panelBusqueda.add(btnSoporte);
        panelBusqueda.add(Box.createHorizontalGlue());
        panelBusqueda.add(btnAtras);
        panelBusqueda.add(Box.createHorizontalStrut(20));

        // Añadir funcionalidad de búsqueda
        btnBuscar.addActionListener(e -> {
            String busqueda = campoBusqueda.getText().trim();
            if (!busqueda.isEmpty() && !busqueda.equals("Buscar...")) {
                JOptionPane.showMessageDialog(this, 
                    "Búsqueda: " + busqueda + "\n(Esta es una simulación de búsqueda)", 
                    "Resultados de búsqueda", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        // Placeholder para el campo de búsqueda
        campoBusqueda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (campoBusqueda.getText().equals("Buscar...")) {
                    campoBusqueda.setText("");
                    campoBusqueda.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (campoBusqueda.getText().trim().isEmpty()) {
                    campoBusqueda.setForeground(Color.GRAY);
                    campoBusqueda.setText("Buscar...");
                }
            }
        });
        campoBusqueda.setForeground(Color.GRAY);

        // ENSAMBLAR LOS PANELES
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelInferior, BorderLayout.CENTER);
        panelPrincipal.add(panelBusqueda, BorderLayout.SOUTH);

        add(panelPrincipal);
        setVisible(true);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setBackground(Color.WHITE);
        boton.setForeground(new Color(0, 0, 255));
        boton.setFocusPainted(false);
        boton.setBorderPainted(true);
        boton.setBorder(BorderFactory.createLineBorder(new Color(41, 83, 197), 1));
        boton.setPreferredSize(new Dimension(150, 30));
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setVerticalTextPosition(SwingConstants.CENTER);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(230, 240, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(Color.WHITE);
            }
        });
        
        return boton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Cartagena();
        });
    }
}
