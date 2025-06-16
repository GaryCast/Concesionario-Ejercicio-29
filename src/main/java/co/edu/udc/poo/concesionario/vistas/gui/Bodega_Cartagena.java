package co.edu.udc.poo.concesionario.vistas.gui;

import co.edu.udc.poo.concesionario.modelo.crud.VehiculoCRUD;
import co.edu.udc.poo.concesionario.modelo.entidades.Vehiculo;
import javax.swing.*;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Bodega_Cartagena extends JFrame {
    private JTable tablaVehiculos;
    private DefaultTableModel modeloTabla;
    private VehiculoCRUD vehiculoCRUD;

    // Constructor de la clase
    public Bodega_Cartagena() throws Exception {
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

        JLabel tituloPrincipal = new JLabel("BODEGA");
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

        // Inicializar el CRUD de vehículos
        try {
            vehiculoCRUD = new VehiculoCRUD();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al inicializar el controlador de vehículos: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Crear el panel principal con imagen de fondo
        JPanel panelCentral = new JPanel(new BorderLayout()) {
            private Image backgroundImage;
            
            {
                // Cargar la imagen de fondo
                try {
                    // Usar el ClassLoader para cargar la imagen
                    java.io.InputStream input = getClass().getClassLoader().getResourceAsStream("fondo.png");
                    if (input != null) {
                        backgroundImage = javax.imageio.ImageIO.read(input);
                        System.out.println("Imagen de fondo cargada correctamente desde recursos");
                    } else {
                        // Intentar desde el sistema de archivos como respaldo
                        try {
                            String path = System.getProperty("user.dir") + "/src/main/resources/fondo.png";
                            backgroundImage = javax.imageio.ImageIO.read(new java.io.File(path));
                            System.out.println("Imagen de fondo cargada desde sistema de archivos: " + path);
                        } catch (Exception e2) {
                            System.err.println("No se pudo cargar la imagen de fondo desde ningún método");
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Error al cargar la imagen de fondo: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    // Dibujar la imagen de fondo escalada al tamaño del panel
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } else {
                    // Fondo gris por defecto si no se puede cargar la imagen
                    g.setColor(new Color(240, 240, 240));
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.setColor(Color.RED);
                    g.drawString("No se pudo cargar el fondo. Verifique que fondo.png esté en src/main/resources/", 20, 20);
                }
            }
        };
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes de 20px
        panelCentral.setOpaque(true);

        // Configurar la tabla
        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que todas las celdas no sean editables
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) { // Columna de número
                    return Integer.class;
                }
                return String.class;
            }
        };
        
        // Agregar columnas al modelo
        modeloTabla.addColumn("#"); // Columna de numeración
        modeloTabla.addColumn("N° Bastidor");
        modeloTabla.addColumn("Marca");
        modeloTabla.addColumn("Modelo");
        modeloTabla.addColumn("Tipo de Carrocería");
        modeloTabla.addColumn("Tipo de Vehículo");
        modeloTabla.addColumn("Color");
        modeloTabla.addColumn("Año de Fab."); // Año como última columna
        
        tablaVehiculos = new JTable(modeloTabla);
        tablaVehiculos.setFillsViewportHeight(true);
        tablaVehiculos.setRowHeight(25);
        
        // Configurar scroll pane para la tabla con márgenes
        JScrollPane scrollPane = new JScrollPane(tablaVehiculos);
        scrollPane.setPreferredSize(new Dimension(900, 400));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Sin borde ya que el panel ya tiene margen
        
        // Configurar márgenes para la tabla
        tablaVehiculos.setIntercellSpacing(new Dimension(0, 0));
        tablaVehiculos.setShowGrid(false);
        tablaVehiculos.setShowHorizontalLines(true);
        tablaVehiculos.setShowVerticalLines(true);
        tablaVehiculos.setRowMargin(5);
        tablaVehiculos.setRowHeight(30); // Aumentar altura de fila para mejor legibilidad
        
        // Asegurar que el scroll se muestre solo cuando sea necesario
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        // Panel para los botones de acción
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setOpaque(false);
        
        // Hacer la tabla semi-transparente
        tablaVehiculos.setOpaque(false);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)tablaVehiculos.getDefaultRenderer(Object.class);
        if (renderer != null) {
            renderer.setOpaque(false);
            
            // Configurar el renderer para celdas transparentes
            DefaultTableCellRenderer transparentRenderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    c.setBackground(new Color(255, 255, 255, 200));
                    if (!isSelected) {
                        c.setBackground(new Color(255, 255, 255, 200));
                    }
                    return c;
                }
            };
            
            // Aplicar el renderer a todas las columnas
            for (int i = 0; i < tablaVehiculos.getColumnCount(); i++) {
                tablaVehiculos.getColumnModel().getColumn(i).setCellRenderer(transparentRenderer);
            }
        }
        
        // Crear botones
        JButton btnAgregar = crearBotonAccion("Agregar");
        JButton btnEditar = crearBotonAccion("Editar");
        JButton btnEliminar = crearBotonAccion("Eliminar");
        JButton btnActualizar = crearBotonAccion("Actualizar");
        
        // Agregar acción a los botones
        btnAgregar.addActionListener(e -> mostrarFormularioAgregar());
        btnEditar.addActionListener(e -> editarVehiculo());
        btnEliminar.addActionListener(e -> eliminarVehiculo());
        btnActualizar.addActionListener(e -> actualizarTabla());
        
        // Agregar botones al panel
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizar);
        
        // Agregar componentes al panel central
        panelCentral.add(panelBotones, BorderLayout.NORTH);
        panelCentral.add(scrollPane, BorderLayout.CENTER);
        
        // Cargar datos en la tabla
        actualizarTabla();

        // PANEL INFERIOR CON BÚSQUEDA, SOPORTE Y BOTÓN ATRÁS
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBackground(Color.LIGHT_GRAY);
        panelBusqueda.setPreferredSize(new Dimension(700, 50));

        // Campo de búsqueda
        JTextField campoBusqueda = new JTextField("Buscar vehículo: ");
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
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelBusqueda, BorderLayout.SOUTH);

        add(panelPrincipal);
        setVisible(true);
    }

    private JButton crearBotonAccion(String texto) {
        JButton boton = new JButton(texto);
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
        
        return boton;
    }
    
    private void actualizarTabla() {
        try {
            // Mostrar indicador de carga
            JOptionPane.showMessageDialog(this, "Cargando vehículos...", "Cargando", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpiar la tabla
            modeloTabla.setRowCount(0);
            
            // Obtener la lista de vehículos
            List<Vehiculo> vehiculos = vehiculoCRUD.listarTodo();
            
            if (vehiculos.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "No se encontraron vehículos en la base de datos", 
                    "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            // Contador para la numeración
            int contador = 1;
            
            // Llenar la tabla con los datos
            for (Vehiculo vehiculo : vehiculos) {
                // Verificar que el vehículo no sea nulo
                if (vehiculo == null) continue;
                
                try {
                    Object[] fila = {
                        contador++, // Número de fila (primera columna)
                        vehiculo.numeroBastidor != null ? vehiculo.numeroBastidor : "N/A",
                        vehiculo.Marca != null ? vehiculo.Marca : "N/A",
                        vehiculo.Modelo != null ? vehiculo.Modelo : "N/A",
                        vehiculo.Carroceria != null ? vehiculo.Carroceria : "N/A",
                        vehiculo.tipoVehiculo != null ? vehiculo.tipoVehiculo : "N/A",
                        vehiculo.color != null ? vehiculo.color : "N/A",
                        vehiculo.añoFabrica != null ? vehiculo.añoFabrica : "N/A" // Año como última columna
                    };
                    modeloTabla.addRow(fila);
                } catch (Exception e) {
                    System.err.println("Error al procesar vehículo: " + e.getMessage());
                    // Continuar con el siguiente vehículo si hay un error
                    continue;
                }
            }
            
            // Ajustar el ancho de las columnas
            tablaVehiculos.getColumnModel().getColumn(0).setPreferredWidth(30);  // Columna de número
            tablaVehiculos.getColumnModel().getColumn(0).setMaxWidth(50);
            
            // Ajustar anchos específicos para cada columna
            tablaVehiculos.getColumnModel().getColumn(1).setPreferredWidth(120); // N° Bastidor
            tablaVehiculos.getColumnModel().getColumn(2).setPreferredWidth(100); // Marca
            tablaVehiculos.getColumnModel().getColumn(3).setPreferredWidth(100); // Modelo
            tablaVehiculos.getColumnModel().getColumn(4).setPreferredWidth(120); // Tipo de Carrocería
            tablaVehiculos.getColumnModel().getColumn(5).setPreferredWidth(120); // Tipo de Vehículo
            tablaVehiculos.getColumnModel().getColumn(6).setPreferredWidth(80);  // Color
            tablaVehiculos.getColumnModel().getColumn(7).setPreferredWidth(80);  // Año de Fab.

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar los vehículos: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void mostrarFormularioAgregar() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 5));
        
        JTextField txtBastidor = new JTextField();
        JTextField txtMarca = new JTextField();
        JTextField txtCarroceria = new JTextField();
        JTextField txtModelo = new JTextField();
        JTextField txtTipo = new JTextField();
        JTextField txtColor = new JTextField();
        JTextField txtAnio = new JTextField();
        
        panel.add(new JLabel("N° Bastidor:"));
        panel.add(txtBastidor);
        panel.add(new JLabel("Marca:"));
        panel.add(txtMarca);
        panel.add(new JLabel("Tipo de Carrocería:"));
        panel.add(txtCarroceria);
        panel.add(new JLabel("Modelo:"));
        panel.add(txtModelo);
        panel.add(new JLabel("Tipo de Vehículo:"));
        panel.add(txtTipo);
        panel.add(new JLabel("Color:"));
        panel.add(txtColor);
        panel.add(new JLabel("Año de Fabricación:"));
        panel.add(txtAnio);
        
        int result = JOptionPane.showConfirmDialog(
            this, 
            panel, 
            "Agregar Nuevo Vehículo", 
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE);
            
        if (result == JOptionPane.OK_OPTION) {
            try {
                Vehiculo nuevoVehiculo = new Vehiculo();
                nuevoVehiculo.numeroBastidor = txtBastidor.getText();
                nuevoVehiculo.Marca = txtMarca.getText();
                nuevoVehiculo.Carroceria = txtCarroceria.getText();
                nuevoVehiculo.Modelo = txtModelo.getText();
                nuevoVehiculo.tipoVehiculo = txtTipo.getText();
                nuevoVehiculo.color = txtColor.getText();
                nuevoVehiculo.añoFabrica = txtAnio.getText();
                
                // Configuración por defecto para nuevos vehículos
                nuevoVehiculo.nuevo = true;
                nuevoVehiculo.ubicacion = "Bodega Cartagena";
                nuevoVehiculo.precioBase = 0; // Se puede modificar según sea necesario
                
                vehiculoCRUD.agregar(nuevoVehiculo);
                actualizarTabla();
                
                JOptionPane.showMessageDialog(this, 
                    "Vehículo agregado exitosamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error al agregar el vehículo: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void editarVehiculo() {
        int filaSeleccionada = tablaVehiculos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione un vehículo para editar", 
                "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            String bastidor = (String) tablaVehiculos.getValueAt(filaSeleccionada, 0);
            Vehiculo vehiculo = vehiculoCRUD.buscar(bastidor);
            
            if (vehiculo == null) {
                throw new Exception("No se encontró el vehículo seleccionado");
            }
            
            JPanel panel = new JPanel(new GridLayout(0, 2, 10, 5));
            
            JTextField txtBastidor = new JTextField(vehiculo.numeroBastidor);
            JTextField txtMarca = new JTextField(vehiculo.Marca);
            JTextField txtCarroceria = new JTextField(vehiculo.Carroceria);
            JTextField txtModelo = new JTextField(vehiculo.Modelo);
            JTextField txtTipo = new JTextField(vehiculo.tipoVehiculo);
            JTextField txtColor = new JTextField(vehiculo.color);
            JTextField txtAnio = new JTextField(vehiculo.añoFabrica);
            
            // Hacer el campo de bastidor no editable
            txtBastidor.setEditable(false);
            
            panel.add(new JLabel("N° Bastidor:"));
            panel.add(txtBastidor);
            panel.add(new JLabel("Marca:"));
            panel.add(txtMarca);
            panel.add(new JLabel("Tipo de Carrocería:"));
            panel.add(txtCarroceria);
            panel.add(new JLabel("Modelo:"));
            panel.add(txtModelo);
            panel.add(new JLabel("Tipo de Vehículo:"));
            panel.add(txtTipo);
            panel.add(new JLabel("Color:"));
            panel.add(txtColor);
            panel.add(new JLabel("Año de Fabricación:"));
            panel.add(txtAnio);
            
            int result = JOptionPane.showConfirmDialog(
                this, 
                panel, 
                "Editar Vehículo", 
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
                
            if (result == JOptionPane.OK_OPTION) {
                // Actualizar los datos del vehículo
                vehiculo.Marca = txtMarca.getText();
                vehiculo.Carroceria = txtCarroceria.getText();
                vehiculo.Modelo = txtModelo.getText();
                vehiculo.tipoVehiculo = txtTipo.getText();
                vehiculo.color = txtColor.getText();
                vehiculo.añoFabrica = txtAnio.getText();
                
                vehiculoCRUD.listarTodo();
                actualizarTabla();
                
                JOptionPane.showMessageDialog(this, 
                    "Vehículo actualizado exitosamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al editar el vehículo: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarVehiculo() {
        int filaSeleccionada = tablaVehiculos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione un vehículo para eliminar", 
                "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            String bastidor = (String) tablaVehiculos.getValueAt(filaSeleccionada, 0);
            String marca = (String) tablaVehiculos.getValueAt(filaSeleccionada, 1);
            String modelo = (String) tablaVehiculos.getValueAt(filaSeleccionada, 3);
            
            int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de eliminar el siguiente vehículo?\n" +
                "Marca: " + marca + "\n" +
                "Modelo: " + modelo + "\n" +
                "Bastidor: " + bastidor,
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
                
            if (confirmacion == JOptionPane.YES_OPTION) {
                vehiculoCRUD.eliminar(bastidor);
                actualizarTabla();
                
                JOptionPane.showMessageDialog(this, 
                    "Vehículo eliminado correctamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al eliminar el vehículo: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Bodega_Cartagena().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
