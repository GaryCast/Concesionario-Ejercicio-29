package co.edu.udc.poo.concesionario.vistas.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class SedeBaja extends JFrame {

    public SedeBaja() {
        // Configuración básica de la ventana
        setTitle("CASTAÑO & TOVAR Motor Company - Sede Baja");
        setMinimumSize(new Dimension(700, 450));
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Inicializar componentes de la interfaz
        initComponents();
    }
    
    private void initComponents() {

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        // PANEL SUPERIOR
        JPanel panelSuperior = new JPanel();
        GroupLayout layoutSuperior = new GroupLayout(panelSuperior);
        panelSuperior.setLayout(layoutSuperior);
        panelSuperior.setPreferredSize(new Dimension(600, 80));
        panelSuperior.setBackground(new Color(41, 83, 197, 255));

        JLabel tituloPrincipal = new JLabel("SERVICIOS OFICIALES");
        tituloPrincipal.setFont(new Font("Tahoma", Font.BOLD, 36));
        tituloPrincipal.setForeground(Color.black);

        JLabel tituloSecundario = new JLabel("Sede Baja");
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
                ImageIcon imagenFondo = new ImageIcon("src/main/resources/fondo.png");
                g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(55, 15, 15, 15));

        // Título de sección
        JLabel tituloAux1 = new JLabel("Opciones de Sede Baja");
        tituloAux1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tituloAux1.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloAux1.setForeground(Color.blue);

        // Panel para los botones principales
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 150, 20));
        panelBotones.setOpaque(false);
        
        // Crear botones principales
        JButton btnMarcas = crearBoton("MARCAS");
        JButton btnVentas = crearBoton("VENTAS");
        JButton btnVehiculos = crearBoton("VEHÍCULOS");
        JButton btnVendedores = crearBoton("VENDEDORES");
        
        // Crear menú desplegable para MARCAS
        JPopupMenu menuMarcas = new JPopupMenu();
        String[] marcas = {"RENAULT", "KIA", "MAZDA", "TOYOTA"};
        for (String marca : marcas) {
            JMenuItem item = new JMenuItem(marca);
            item.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, 
                    "Marca seleccionada: " + marca,
                    "Marca", 
                    JOptionPane.INFORMATION_MESSAGE);
            });
            menuMarcas.add(item);
        }
        
        // Configurar el botón MARCAS para mostrar el menú
        btnMarcas.addActionListener(e -> {
            menuMarcas.show(btnMarcas, 0, btnMarcas.getHeight());
        });

        // Agregar botones al panel
        panelBotones.add(btnMarcas);
        panelBotones.add(btnVentas);
        panelBotones.add(btnVehiculos);
        panelBotones.add(btnVendedores);
        
        // Agregar componentes al panel inferior
        panelInferior.add(Box.createVerticalStrut(30));
        panelInferior.add(tituloAux1);
        panelInferior.add(Box.createVerticalStrut(15));
        panelInferior.add(panelBotones);
        panelInferior.add(Box.createVerticalStrut(20));

        // PANEL INFERIOR CON BÚSQUEDA, SOPORTE Y BOTÓN ATRÁS
        JPanel panelBusqueda = crearPanelBusqueda();

        // Agregar componentes al panel principal
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelInferior, BorderLayout.CENTER);
        panelPrincipal.add(panelBusqueda, BorderLayout.SOUTH);

        add(panelPrincipal);
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
    
    private JPanel crearPanelBusqueda() {
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBackground(Color.LIGHT_GRAY);
        panelBusqueda.setPreferredSize(new Dimension(700, 50));

        // Campo de búsqueda
        JTextField campoBusqueda = new JTextField("Buscar en sede: ");
        campoBusqueda.setPreferredSize(new Dimension(400, 30));

        // Botón de búsqueda
        ImageIcon iconoBuscar = new ImageIcon("src/main/resources/busqueda.png");
        JButton btnBuscar = new JButton(iconoBuscar);
        btnBuscar.setPreferredSize(new Dimension(40, 30));

        // Botón de soporte
        ImageIcon iconoSoporte = new ImageIcon("src/main/resources/soporte.png");
        JButton btnSoporte = new JButton(iconoSoporte);
        btnSoporte.setPreferredSize(new Dimension(30, 30));
        btnSoporte.setFocusPainted(false);
        btnSoporte.setBackground(new Color(148, 196, 255, 255));
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
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnSoporte);
        panelBusqueda.add(Box.createHorizontalStrut(20));
        panelBusqueda.add(btnAtras);
        
        return panelBusqueda;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SedeBaja().setVisible(true));
    }
}
