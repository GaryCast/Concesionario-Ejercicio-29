package co.edu.udc.poo.concesionario.vistas.gui;

import javax.swing.*;
import java.awt.*;

public class VentanaInicio extends JFrame {

    public VentanaInicio() {
        Dimension medPantalla = new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE);

        setTitle("CASTAÑO & TOVAR Motor Company");
        setSize(new Dimension(1100, 700));
        setMinimumSize(new Dimension(1100, 700));
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
        tituloPrincipal.setForeground(Color.white);

        JLabel tituloSecundario = new JLabel("Motor Company");
        tituloSecundario.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tituloSecundario.setForeground(Color.white);

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

        // Panel principal con título
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setOpaque(false);
        
        // Título principal
        JLabel titulo = new JLabel("Su concesionario de confianza!");
        titulo.setFont(new Font("Tahoma", Font.BOLD, 24));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setForeground(new Color(0, 51, 153));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
        
        // Panel para las dos columnas
        JPanel panelColumnas = new JPanel(new GridLayout(1, 2, 40, 0));
        panelColumnas.setOpaque(false);
        panelColumnas.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        
        // Panel izquierdo - Ubicaciones
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BorderLayout());
        panelIzquierdo.setOpaque(false);
        panelIzquierdo.setBorder(BorderFactory.createLineBorder(new Color(41, 83, 197), 2, true));
        
        JLabel tituloUbicaciones = new JLabel("Nos encontramos en");
        tituloUbicaciones.setFont(new Font("Tahoma", Font.BOLD, 18));
        tituloUbicaciones.setHorizontalAlignment(JLabel.CENTER);
        tituloUbicaciones.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        tituloUbicaciones.setForeground(new Color(41, 83, 197));
        
        // Panel para botones de ubicación con tamaño fijo
        JPanel panelBotonesUbicaciones = new JPanel(new GridBagLayout());
        panelBotonesUbicaciones.setOpaque(false);
        panelBotonesUbicaciones.setBorder(BorderFactory.createEmptyBorder(10, 50, 30, 50));
        
        // Configuración de restricciones para los botones
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 0, 20, 0); // Espacio vertical entre botones
        
        JButton btnCartagena = crearBotonUbicacion("CARTAGENA", e -> {
            try {
                dispose();
                new Cartagena().setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, 
                    "Error al abrir la ventana de Cartagena: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnBarranquilla = crearBotonUbicacion("BARRANQUILLA", e -> {
            try {
                dispose();
                new Barranquilla().setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, 
                    "Error al abrir la ventana de Barranquilla: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Agregar botones con restricciones
        panelBotonesUbicaciones.add(btnCartagena, gbc);
        gbc.insets = new Insets(0, 0, 0, 0); // Sin espacio después del último botón
        panelBotonesUbicaciones.add(btnBarranquilla, gbc);
        
        panelIzquierdo.add(tituloUbicaciones, BorderLayout.NORTH);
        panelIzquierdo.add(panelBotonesUbicaciones, BorderLayout.CENTER);

        // Panel derecho - Secciones
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BorderLayout());
        panelDerecho.setOpaque(false);
        panelDerecho.setBorder(BorderFactory.createLineBorder(new Color(41, 83, 197), 2, true));
        
        JLabel tituloSecciones = new JLabel("Secciones");
        tituloSecciones.setFont(new Font("Tahoma", Font.BOLD, 18));
        tituloSecciones.setHorizontalAlignment(JLabel.CENTER);
        tituloSecciones.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        tituloSecciones.setForeground(new Color(41, 83, 197));
        
        // Panel para botones de secciones con tamaño fijo
        JPanel panelBotonesSecciones = new JPanel(new GridBagLayout());
        panelBotonesSecciones.setOpaque(false);
        panelBotonesSecciones.setBorder(BorderFactory.createEmptyBorder(10, 50, 30, 50));
        
        // Configuración de restricciones para los botones
        GridBagConstraints gbcSecciones = new GridBagConstraints();
        gbcSecciones.fill = GridBagConstraints.HORIZONTAL;
        gbcSecciones.weightx = 1.0;
        gbcSecciones.gridwidth = GridBagConstraints.REMAINDER;
        gbcSecciones.insets = new Insets(0, 0, 15, 0); // Espacio vertical entre botones
        
        // Botones de secciones
        JButton btnClientes = crearBotonSeccion("CLIENTES", e -> {
            try {
                dispose();
                new ClienteGUI().setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, 
                    "Error al abrir la gestión de clientes: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnVehiculos = crearBotonSeccion("VEHICULOS", e -> {
            dispose();
            try {
                new VehiculoGUI();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, 
                    "Error al abrir la gestión de vehículos: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Crear botón MARCAS con menú desplegable
        JButton btnMarcas = crearBotonSeccion("MARCAS ▼", e -> {});
        
        // Crear menú desplegable
        JPopupMenu menuMarcas = new JPopupMenu();
        
        // Estilo para los items del menú
        UIManager.put("PopupMenu.background", Color.WHITE);
        UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(new Color(41, 83, 197)));
        UIManager.put("MenuItem.selectionBackground", new Color(230, 240, 255));
        UIManager.put("MenuItem.selectionForeground", Color.BLACK);
        
        // Crear items del menú
        JMenuItem renaultItem = new JMenuItem("RENAULT");
        renaultItem.setFont(new Font("Arial", Font.PLAIN, 12));
        renaultItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Ha seleccionado la marca RENAULT", 
                "Marca Seleccionada", JOptionPane.INFORMATION_MESSAGE);
        });
        
        JMenuItem kiaItem = new JMenuItem("KIA");
        kiaItem.setFont(new Font("Arial", Font.PLAIN, 12));
        kiaItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Ha seleccionado la marca KIA", 
                "Marca Seleccionada", JOptionPane.INFORMATION_MESSAGE);
        });
        
        JMenuItem mazdaItem = new JMenuItem("MAZDA");
        mazdaItem.setFont(new Font("Arial", Font.PLAIN, 12));
        mazdaItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Ha seleccionado la marca MAZDA", 
                "Marca Seleccionada", JOptionPane.INFORMATION_MESSAGE);
        });
        
        JMenuItem toyotaItem = new JMenuItem("TOYOTA");
        toyotaItem.setFont(new Font("Arial", Font.PLAIN, 12));
        toyotaItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Ha seleccionado la marca TOYOTA", 
                "Marca Seleccionada", JOptionPane.INFORMATION_MESSAGE);
        });
        
        // Agregar items al menú
        menuMarcas.add(renaultItem);
        menuMarcas.add(kiaItem);
        menuMarcas.add(mazdaItem);
        menuMarcas.add(toyotaItem);
        
        // Mostrar menú al hacer clic en el botón
        btnMarcas.addActionListener(e -> {
            menuMarcas.show(btnMarcas, 0, btnMarcas.getHeight());
        });
        
        JButton btnVentas = crearBotonSeccion("VENTAS", e -> {
            // Acción para el botón de ventas
            JOptionPane.showMessageDialog(this, 
                "Módulo de Ventas en desarrollo", 
                "Información", JOptionPane.INFORMATION_MESSAGE);
        });
        
        // Agregar botones con restricciones al panel de secciones
        panelBotonesSecciones.add(btnClientes, gbcSecciones);
        panelBotonesSecciones.add(btnVehiculos, gbcSecciones);
        panelBotonesSecciones.add(btnMarcas, gbcSecciones);
        gbcSecciones.insets = new Insets(0, 0, 0, 0); // Sin espacio después del último botón
        panelBotonesSecciones.add(btnVentas, gbcSecciones);
        
        panelDerecho.add(tituloSecciones, BorderLayout.NORTH);
        panelDerecho.add(panelBotonesSecciones, BorderLayout.CENTER);
        
        // Agregar paneles laterales al contenedor principal
        panelColumnas.add(panelIzquierdo);
        panelColumnas.add(panelDerecho);
        
        // Agregar todo al panel central
        panelCentral.add(tituloPrincipal, BorderLayout.NORTH);
        panelCentral.add(panelColumnas, BorderLayout.CENTER);
        
        // Agregar el panel central al panel inferior
        panelInferior.add(panelCentral);

        // PANEL INFERIOR CON BÚSQUEDA Y SOPORTE
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelBusqueda.setBackground(new Color(202, 193, 193));
        panelBusqueda.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(202, 193, 193)));
        panelBusqueda.setPreferredSize(new Dimension(getWidth(), 60));

        // Campo de búsqueda
        JTextField campoBusqueda = new JTextField("Buscar...");
        campoBusqueda.setPreferredSize(new Dimension(300, 30));
        campoBusqueda.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        // Botón de búsqueda
        ImageIcon iconoBuscar = new ImageIcon("src/main/resources/busqueda.png");
        JButton btnBuscar = new JButton(iconoBuscar);
        btnBuscar.setPreferredSize(new Dimension(40, 30));
        btnBuscar.setBackground(new Color(41, 83, 197));
        btnBuscar.setFocusPainted(false);
        
        // Botón de soporte (icono)
        ImageIcon iconoSoporte = new ImageIcon("src/main/resources/soporte.png");
        JButton btnSoporte = new JButton(iconoSoporte);
        btnSoporte.setPreferredSize(new Dimension(40, 30));
        btnSoporte.setOpaque(true);
        btnSoporte.setBackground(new Color(41, 83, 197));
        btnSoporte.setToolTipText("Soporte técnico");
        btnSoporte.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Soporte Técnico\nPara asistencia, contacte a:\nCorreo: ArrietasoporteTecnico@concesionario.com\nTeléfono: 3208375978",
                "Soporte", JOptionPane.INFORMATION_MESSAGE);
        });

        // Agregar componentes al panel inferior
        panelBusqueda.add(Box.createHorizontalStrut(20));
        panelBusqueda.add(campoBusqueda);
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(Box.createHorizontalStrut(10));
        panelBusqueda.add(btnSoporte);
        
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
        
        // Asegurar que el foco no esté en el campo de búsqueda al iniciar
        panelPrincipal.requestFocusInWindow();
    }

    private JButton crearBotonUbicacion(String texto, java.awt.event.ActionListener accion) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setForeground(Color.WHITE); // Texto blanco
        boton.setBackground(new Color(41, 83, 197)); // Fondo azul
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Establecer tamaño preferido, mínimo y máximo
        Dimension tamanoBoton = new Dimension(200, 50);
        boton.setPreferredSize(tamanoBoton);
        boton.setMinimumSize(tamanoBoton);
        boton.setMaximumSize(tamanoBoton);
        
        // Centrar el texto
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setVerticalTextPosition(SwingConstants.CENTER);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setOpaque(true);
        
        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(31, 63, 167)); // Azul más oscuro al pasar el mouse
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(41, 83, 197)); // Vuelve al azul original
            }
        });
        
        boton.addActionListener(accion);
        return boton;
    }
    
    private JButton crearBotonSeccion(String texto, java.awt.event.ActionListener accion) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setForeground(Color.WHITE); // Texto blanco
        boton.setBackground(new Color(41, 83, 197)); // Fondo azul
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        
        // Establecer tamaño preferido, mínimo y máximo
        Dimension tamanoBoton = new Dimension(200, 40);
        boton.setPreferredSize(tamanoBoton);
        boton.setMinimumSize(tamanoBoton);
        boton.setMaximumSize(tamanoBoton);
        
        // Centrar el texto
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setVerticalTextPosition(SwingConstants.CENTER);
        
        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(31, 63, 167)); // Azul más oscuro al pasar el mouse
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(41, 83, 197)); // Vuelve al azul original
            }
        });
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setContentAreaFilled(false);
        boton.setOpaque(true);
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(230, 240, 255));
                boton.setForeground(new Color(41, 83, 197));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setForeground(new Color(255, 255, 255));
                boton.setBackground(new Color(41, 83, 197));
            }
        });
        boton.addActionListener(accion);
        return boton;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new VentanaInicio();
        });
    }
}
