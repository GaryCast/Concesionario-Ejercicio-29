package co.edu.udc.poo.concesionario.vistas.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Barranquilla extends JFrame {

    public Barranquilla() {
        // Configuración básica de la ventana
        setTitle("CASTAÑO & TOVAR Motor Company - Barranquilla");
        setMinimumSize(new Dimension(700, 450));
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

        JLabel tituloPrincipal = new JLabel("BARRAQUILLA");
        tituloPrincipal.setFont(new Font("Tahoma", Font.BOLD, 36));
        tituloPrincipal.setForeground(Color.black);

        JLabel tituloSecundario = new JLabel("Página en construcción");
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

        // PANEL CENTRAL CON MENSAJE
        JPanel panelCentral = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagenFondo = new ImageIcon("src/main/resources/fondo.png");
                g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelCentral.setLayout(new BorderLayout());

        // Mensaje de página en construcción
        JLabel mensaje = new JLabel("PAGINA EN CONSTRUCCION", SwingConstants.CENTER);
        mensaje.setFont(new Font("Tahoma", Font.BOLD, 36));
        mensaje.setForeground(Color.BLUE);
        mensaje.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));

        // Botón para volver
        JButton btnVolver = new JButton("<html><font color='#0000FF'>VOLVER A INICIO</font></html>");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.setBackground(new Color(255, 255, 255));
        btnVolver.setForeground(Color.BLUE);
        btnVolver.setPreferredSize(new Dimension(200, 40));
        btnVolver.addActionListener(e -> {
            this.dispose();
            new VentanaInicio().setVisible(true);
        });

        // Panel para el botón centrado
        JPanel panelBoton = new JPanel();
        panelBoton.setOpaque(false);
        panelBoton.add(btnVolver);

        // Agregar componentes al panel central
        panelCentral.add(mensaje, BorderLayout.CENTER);
        panelCentral.add(panelBoton, BorderLayout.SOUTH);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));

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
                "Soporte Técnico\nPara asistencia, contacte a:\nCorreo: soporte@concesionario.com\nTeléfono: (123) 456-7890", 
                "Soporte", JOptionPane.INFORMATION_MESSAGE);
        });

        // Botón "Atrás"
        JButton btnAtras = new JButton("<html><font color='#0000FF'>Atrás</font></html>");
        btnAtras.setPreferredSize(new Dimension(100, 30));
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setForeground(Color.BLUE);
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

        // Agregar componentes al panel principal
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelBusqueda, BorderLayout.SOUTH);

        add(panelPrincipal);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Barranquilla();
    }
}
