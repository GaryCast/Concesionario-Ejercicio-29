package co.edu.udc.poo.concesionario.vistas.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clase base para todas las interfaces gráficas que requieren operaciones CRUD
 * (Crear, Leer, Actualizar, Eliminar)
 */
public abstract class PlantillaCRUD extends JFrame {

    // Paneles principales
    protected JPanel panelPrincipal;
    protected JPanel panelSuperior;
    protected JPanel panelInferior;
    protected JPanel panelCentral;

    // Componentes comunes
    protected JTextField campoBusqueda;
    protected JButton btnBuscar;
    protected JButton btnAgregar;
    protected JButton btnEditar;
    protected JButton btnEliminar;
    protected JButton btnListar;
    protected JButton btnAtras;

    // Tabla para mostrar datos
    protected JTable tablaResultados;
    protected JScrollPane scrollTabla;

    /**
     * Constructor de la clase base
     * @param titulo Título de la ventana
     * @param subtitulo Subtítulo de la ventana
     */
    public PlantillaCRUD(String titulo, String subtitulo) {
        configurarVentana(titulo);
        inicializarComponentes(titulo, subtitulo);
        configurarEventos();
    }

    /**
     * Configura las propiedades básicas de la ventana
     */
    private void configurarVentana(String titulo) {
        Dimension medPantalla = new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE);

        setTitle("CASTAÑO & TOVAR Motor Company - " + titulo);
        setSize(new Dimension(700,400));
        setMinimumSize(new Dimension(700,400));
        setPreferredSize(medPantalla);
        setMaximumSize(medPantalla);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Inicializa todos los componentes de la interfaz
     */
    private void inicializarComponentes(String titulo, String subtitulo) {
        // Panel principal
        panelPrincipal = new JPanel(new BorderLayout());

        // Panel superior (cabecera)
        panelSuperior = crearPanelSuperior(titulo, subtitulo);

        // Panel central (contenido principal)
        panelCentral = crearPanelCentral();

        // Panel inferior (búsqueda y navegación)
        panelInferior = crearPanelInferior();

        // Ensamblar paneles
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    /**
     * Crea el panel superior con el título y subtítulo
     */
    private JPanel crearPanelSuperior(String titulo, String subtitulo) {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        panel.setPreferredSize(new Dimension(800, 80));
        panel.setBackground(new Color(41, 83, 197, 255));

        JLabel tituloPrincipal = new JLabel(titulo.toUpperCase());
        tituloPrincipal.setFont(new Font("Tahoma", Font.BOLD, 36));
        tituloPrincipal.setForeground(Color.BLACK);

        JLabel tituloSecundario = new JLabel(subtitulo);
        tituloSecundario.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tituloSecundario.setForeground(Color.BLACK);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                        .addComponent(tituloPrincipal)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tituloSecundario)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(tituloPrincipal)
                        .addComponent(tituloSecundario)
        );

        return panel;
    }

    /**
     * Crea el panel central con fondo y tabla de resultados
     */
    private JPanel crearPanelCentral() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagenFondo = new ImageIcon("src/main/resources/fondo.png");
                g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(new BorderLayout());

        // Panel de botones CRUD
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelBotones.setOpaque(false);

        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Actualizar");

        // Estilo de botones
        JButton[] botones = {btnAgregar, btnEditar, btnEliminar, btnListar};
        for (JButton boton : botones) {
            // Estilo con fondo azul y letras blancas
            boton.setFont(new Font("Arial", Font.BOLD, 12));
            boton.setForeground(Color.WHITE);
            boton.setBackground(new Color(41, 83, 197));
            boton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
            boton.setFocusPainted(false);
            boton.setContentAreaFilled(true);
            boton.setOpaque(true);
            boton.setBorderPainted(false);
            boton.setPreferredSize(new Dimension(120, 35));
            
            // Efecto hover
            boton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    boton.setBackground(Color.WHITE);
                    boton.setForeground(new Color(41, 83, 197));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    boton.setBackground(new Color(41, 83, 197));
                    boton.setForeground(Color.WHITE);
                }
            });
        }

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);

        // Tabla de resultados
        tablaResultados = new JTable();
        scrollTabla = new JScrollPane(tablaResultados);
        scrollTabla.setPreferredSize(new Dimension(750, 350));

        // Panel para contener la tabla
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setOpaque(false);
        panelTabla.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        // Agregar componentes al panel central
        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(panelTabla, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Crea el panel inferior con búsqueda y navegación
     */
    private JPanel crearPanelInferior() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(800, 50));

        campoBusqueda = new JTextField();
        campoBusqueda.setPreferredSize(new Dimension(400, 30));

        ImageIcon iconoBuscar = new ImageIcon("src/main/resources/busqueda.png");
        btnBuscar = new JButton(iconoBuscar);
        btnBuscar.setPreferredSize(new Dimension(40, 30));

        // Botón de soporte (icono) - Estilo consistente
        ImageIcon iconoSoporte = new ImageIcon("src/main/resources/soporte.png");
        JButton btnSoporte = new JButton(iconoSoporte);
        btnSoporte.setPreferredSize(new Dimension(40, 30));
        btnSoporte.setOpaque(true);
        btnSoporte.setBackground(new Color(41, 83, 197));
        btnSoporte.setToolTipText("Soporte técnico");
        btnSoporte.setBorderPainted(false);
        btnSoporte.setFocusPainted(false);
        btnSoporte.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Soporte Técnico\nPara asistencia, contacte a:\nCorreo: ArrietasoporteTecnico@concesionario.com\nTeléfono: 3106459508",
                "Soporte", JOptionPane.INFORMATION_MESSAGE);
        });

        btnAtras = new JButton("Atrás");
        btnAtras.setFont(new Font("Arial", Font.BOLD, 12));
        btnAtras.setPreferredSize(new Dimension(120, 35));
        btnAtras.setBackground(new Color(41, 83, 197));
        btnAtras.setForeground(Color.WHITE);
        btnAtras.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnAtras.setFocusPainted(false);
        btnAtras.setContentAreaFilled(true);
        btnAtras.setOpaque(true);
        btnAtras.setBorderPainted(false);
        
        // Efecto hover
        btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAtras.setBackground(Color.WHITE);
                btnAtras.setForeground(new Color(41, 83, 197));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAtras.setBackground(new Color(41, 83, 197));
                btnAtras.setForeground(Color.WHITE);
            }
        });

        panel.add(campoBusqueda);
        panel.add(btnBuscar);
        panel.add(btnSoporte);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(btnAtras);

        return panel;
    }

    /**
     * Configura los eventos para los botones
     */
    private void configurarEventos() {
        btnAgregar.addActionListener(e -> mostrarFormularioAgregar());
        btnEditar.addActionListener(e -> mostrarFormularioEditar());
        btnEliminar.addActionListener(e -> confirmarEliminar());
        btnListar.addActionListener(e -> listarTodos());
        btnBuscar.addActionListener(e -> buscar());
        btnAtras.addActionListener(e -> volver());
    }

    /**
     * Establece el ActionListener para el botón Atrás
     */
    protected void setBotonAtrasListener(ActionListener listener) {
        btnAtras.addActionListener(listener);
    }

    // Métodos abstractos que deben implementar las clases hijas
    protected abstract void mostrarFormularioAgregar();
    protected abstract void mostrarFormularioEditar();
    protected abstract void confirmarEliminar();
    protected abstract void listarTodos();
    protected abstract void buscar();
    protected abstract void volver();
}
