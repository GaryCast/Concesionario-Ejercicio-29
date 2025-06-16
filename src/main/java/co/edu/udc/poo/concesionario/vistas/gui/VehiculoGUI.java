package co.edu.udc.poo.concesionario.vistas.gui;

import co.edu.udc.poo.concesionario.modelo.crud.VehiculoCRUD;
import co.edu.udc.poo.concesionario.modelo.entidades.Caracteristicas;
import co.edu.udc.poo.concesionario.modelo.entidades.Vehiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Interfaz gráfica para la gestión de vehículos
 */
public class VehiculoGUI extends PlantillaCRUD {
    
    private DefaultTableModel modeloTabla;
    private VehiculoCRUD vehiculoCRUD;
    
    /**
     * Constructor de la clase
     */
    public VehiculoGUI() {
        super("Vehiculos", "Gestión de Vehículos");
        
        try {
            vehiculoCRUD = new VehiculoCRUD();
            configurarTabla();
            listarTodos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                    "Error al inicializar: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        setVisible(true);
    }
    
    /**
     * Configura la estructura de la tabla
     */
    private void configurarTabla() {
        String[] columnas = {"Marca", "Modelo", "Tipo", "Año", "Color", "Precio Base", "Ubicación", "Bastidor"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No permitir edición directa en la tabla
            }
        };
        
        tablaResultados.setModel(modeloTabla);
        tablaResultados.getTableHeader().setReorderingAllowed(false);
    }
    
    /**
     * Actualiza la tabla con la lista de vehículos proporcionada
     */
    private void actualizarTabla(ArrayList<Vehiculo> vehiculos) {
        modeloTabla.setRowCount(0); // Limpiar tabla
        
        for (Vehiculo vehiculo : vehiculos) {
            Object[] fila = {
                vehiculo.Marca,
                vehiculo.Modelo,
                vehiculo.tipoVehiculo,
                vehiculo.añoFabrica,
                vehiculo.color,
                vehiculo.precioBase,
                vehiculo.ubicacion,
                vehiculo.numeroBastidor
            };
            modeloTabla.addRow(fila);
        }
    }
    
    /**
     * Muestra el formulario para agregar un nuevo vehículo
     */
    @Override
    protected void mostrarFormularioAgregar() {
        JDialog dialogo = new JDialog(this, "Agregar Vehículo", true);
        dialogo.setSize(600, 700);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout());
        
        // Panel con pestañas para organizar la información
        JTabbedPane pestañas = new JTabbedPane();
        
        // Pestaña de información básica
        JPanel panelBasico = new JPanel(new GridLayout(0, 2, 10, 10));
        panelBasico.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Campos del formulario - Información básica
        JTextField campoMarca = new JTextField();
        JTextField campoModelo = new JTextField();
        JTextField campoTipoVehiculo = new JTextField();
        JTextField campoCarroceria = new JTextField();
        JCheckBox campoNuevo = new JCheckBox();
        JTextField campoAñoFabrica = new JTextField();
        JTextField campoCilindraje = new JTextField();
        JTextField campoPotenciaFiscal = new JTextField();
        JTextField campoPrecioBase = new JTextField();
        JTextField campoColor = new JTextField();
        JTextField campoNumeroBastidor = new JTextField();
        
        // Agregar componentes al panel básico
        panelBasico.add(new JLabel("Marca:"));
        panelBasico.add(campoMarca);
        panelBasico.add(new JLabel("Modelo:"));
        panelBasico.add(campoModelo);
        panelBasico.add(new JLabel("Tipo de Vehículo:"));
        panelBasico.add(campoTipoVehiculo);
        panelBasico.add(new JLabel("Carrocería:"));
        panelBasico.add(campoCarroceria);
        panelBasico.add(new JLabel("Nuevo:"));
        panelBasico.add(campoNuevo);
        panelBasico.add(new JLabel("Año de Fabricación:"));
        panelBasico.add(campoAñoFabrica);
        panelBasico.add(new JLabel("Cilindraje:"));
        panelBasico.add(campoCilindraje);
        panelBasico.add(new JLabel("Potencia Fiscal:"));
        panelBasico.add(campoPotenciaFiscal);
        panelBasico.add(new JLabel("Precio Base:"));
        panelBasico.add(campoPrecioBase);
        panelBasico.add(new JLabel("Color:"));
        panelBasico.add(campoColor);
        panelBasico.add(new JLabel("Número de Bastidor:"));
        panelBasico.add(campoNumeroBastidor);
        
        // Pestaña de características técnicas
        JPanel panelTecnico = new JPanel(new GridLayout(0, 2, 10, 10));
        panelTecnico.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Campos del formulario - Características técnicas
        JTextField campoUbicacion = new JTextField();
        JTextField campoTransmision = new JTextField();
        JTextField campoTraccion = new JTextField();
        JTextField campoCombustible = new JTextField();
        JTextField campoEficiencia = new JTextField();
        JTextField campoMantenimientos = new JTextField();
        
        // Agregar componentes al panel técnico
        panelTecnico.add(new JLabel("Ubicación:"));
        panelTecnico.add(campoUbicacion);
        panelTecnico.add(new JLabel("Transmisión:"));
        panelTecnico.add(campoTransmision);
        panelTecnico.add(new JLabel("Tracción:"));
        panelTecnico.add(campoTraccion);
        panelTecnico.add(new JLabel("Combustible:"));
        panelTecnico.add(campoCombustible);
        panelTecnico.add(new JLabel("Eficiencia de Combustible:"));
        panelTecnico.add(campoEficiencia);
        panelTecnico.add(new JLabel("Mantenimientos:"));
        panelTecnico.add(campoMantenimientos);
        
        // Pestaña de características
        JPanel panelCaracteristicas = new JPanel(new BorderLayout());
        panelCaracteristicas.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel para características básicas
        JPanel panelBasicos = new JPanel(new GridLayout(0, 1));
        panelBasicos.setBorder(BorderFactory.createTitledBorder("Características Básicas"));
        
        // Checkboxes para características básicas
        Map<Caracteristicas, JCheckBox> checkboxesBasicos = new HashMap<>();
        for (Caracteristicas c : Caracteristicas.values()) {
            JCheckBox checkbox = new JCheckBox(c.toString());
            checkboxesBasicos.put(c, checkbox);
            panelBasicos.add(checkbox);
        }
        
        // Panel para características extras
        JPanel panelExtras = new JPanel(new GridLayout(0, 3));
        panelExtras.setBorder(BorderFactory.createTitledBorder("Características Extras (Precio)"));
        
        // Campos para precios de extras
        Map<Caracteristicas, JTextField> camposExtras = new HashMap<>();
        for (Caracteristicas c : Caracteristicas.values()) {
            JLabel label = new JLabel(c.toString() + ":");
            JTextField campo = new JTextField("500000");
            camposExtras.put(c, campo);
            panelExtras.add(label);
            panelExtras.add(campo);
            panelExtras.add(new JLabel("COP"));
        }
        
        // Agregar paneles de características al panel principal
        JPanel panelCaracteristicasContenido = new JPanel(new GridLayout(2, 1));
        panelCaracteristicasContenido.add(panelBasicos);
        panelCaracteristicasContenido.add(new JScrollPane(panelExtras));
        panelCaracteristicas.add(panelCaracteristicasContenido, BorderLayout.CENTER);
        
        // Agregar pestañas
        pestañas.addTab("Información Básica", panelBasico);
        pestañas.addTab("Características Técnicas", panelTecnico);
        pestañas.addTab("Características y Extras", panelCaracteristicas);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnGuardar.addActionListener(e -> {
            try {
                // Validar campos obligatorios
                if (campoMarca.getText().isEmpty() || campoModelo.getText().isEmpty() || 
                    campoNumeroBastidor.getText().isEmpty() || campoPrecioBase.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(dialogo, 
                            "Los campos Marca, Modelo, Número de Bastidor y Precio Base son obligatorios", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Recopilar características básicas seleccionadas
                List<Caracteristicas> caracteristicasBasicas = new ArrayList<>();
                for (Map.Entry<Caracteristicas, JCheckBox> entry : checkboxesBasicos.entrySet()) {
                    if (entry.getValue().isSelected()) {
                        caracteristicasBasicas.add(entry.getKey());
                    }
                }
                
                // Crear nuevo vehículo
                Vehiculo nuevoVehiculo = new Vehiculo(
                        campoNuevo.isSelected(),
                        campoAñoFabrica.getText(),
                        campoCilindraje.getText(),
                        campoPotenciaFiscal.getText(),
                        Integer.parseInt(campoPrecioBase.getText()),
                        caracteristicasBasicas,
                        campoColor.getText(),
                        campoNumeroBastidor.getText(),
                        campoUbicacion.getText(),
                        campoTransmision.getText(),
                        campoTraccion.getText(),
                        campoCombustible.getText(),
                        campoEficiencia.getText()
                );
                
                // Establecer campos adicionales
                nuevoVehiculo.Marca = campoMarca.getText();
                nuevoVehiculo.Modelo = campoModelo.getText();
                nuevoVehiculo.tipoVehiculo = campoTipoVehiculo.getText();
                nuevoVehiculo.Carroceria = campoCarroceria.getText();
                nuevoVehiculo.mantenimientos = campoMantenimientos.getText();
                
                // Configurar extras con precios personalizados
                nuevoVehiculo.Extras.clear();
                for (Map.Entry<Caracteristicas, JTextField> entry : camposExtras.entrySet()) {
                    if (!checkboxesBasicos.get(entry.getKey()).isSelected()) {
                        try {
                            int precio = Integer.parseInt(entry.getValue().getText());
                            nuevoVehiculo.Extras.put(entry.getKey(), precio);
                        } catch (NumberFormatException ex) {
                            nuevoVehiculo.Extras.put(entry.getKey(), 500000); // Valor por defecto
                        }
                    }
                }
                
                // Guardar vehículo
                vehiculoCRUD.agregar(nuevoVehiculo);
                JOptionPane.showMessageDialog(dialogo, 
                        "Vehículo agregado correctamente", 
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                dialogo.dispose();
                listarTodos(); // Actualizar tabla
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialogo, 
                        "Error al guardar: " + ex.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnCancelar.addActionListener(e -> dialogo.dispose());
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        
        dialogo.add(pestañas, BorderLayout.CENTER);
        dialogo.add(panelBotones, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    
    /**
     * Muestra el formulario para editar un vehículo existente
     */
    @Override
    protected void mostrarFormularioEditar() {
        int filaSeleccionada = tablaResultados.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, 
                    "Debe seleccionar un vehículo para editar", 
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String numeroBastidor = (String) tablaResultados.getValueAt(filaSeleccionada, 7);
        
        try {
            // Buscar vehículo por número de bastidor
            final Vehiculo vehiculo = vehiculoCRUD.buscar(numeroBastidor);
            
            if (vehiculo == null) {
                JOptionPane.showMessageDialog(this, 
                        "No se encontró el vehículo", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Crear diálogo de edición (similar al de agregar pero con datos precargados)
            JDialog dialogo = new JDialog(this, "Editar Vehículo", true);
            dialogo.setSize(600, 700);
            dialogo.setLocationRelativeTo(this);
            dialogo.setLayout(new BorderLayout());
            
            // Panel con pestañas para organizar la información
            JTabbedPane pestañas = new JTabbedPane();
            
            // Pestaña de información básica
            JPanel panelBasico = new JPanel(new GridLayout(0, 2, 10, 10));
            panelBasico.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            // Campos del formulario - Información básica
            JTextField campoMarca = new JTextField(vehiculo.Marca);
            JTextField campoModelo = new JTextField(vehiculo.Modelo);
            JTextField campoTipoVehiculo = new JTextField(vehiculo.tipoVehiculo);
            JTextField campoCarroceria = new JTextField(vehiculo.Carroceria);
            JCheckBox campoNuevo = new JCheckBox("", vehiculo.nuevo);
            JTextField campoAñoFabrica = new JTextField(vehiculo.añoFabrica);
            JTextField campoCilindraje = new JTextField(vehiculo.cilindraje);
            JTextField campoPotenciaFiscal = new JTextField(vehiculo.potenciaFiscal);
            JTextField campoPrecioBase = new JTextField(vehiculo.precioBase.toString());
            JTextField campoColor = new JTextField(vehiculo.color);
            JTextField campoNumeroBastidor = new JTextField(vehiculo.numeroBastidor);
            campoNumeroBastidor.setEditable(false); // No permitir cambiar el número de bastidor
            
            // Agregar componentes al panel básico
            panelBasico.add(new JLabel("Marca:"));
            panelBasico.add(campoMarca);
            panelBasico.add(new JLabel("Modelo:"));
            panelBasico.add(campoModelo);
            panelBasico.add(new JLabel("Tipo de Vehículo:"));
            panelBasico.add(campoTipoVehiculo);
            panelBasico.add(new JLabel("Carrocería:"));
            panelBasico.add(campoCarroceria);
            panelBasico.add(new JLabel("Nuevo:"));
            panelBasico.add(campoNuevo);
            panelBasico.add(new JLabel("Año de Fabricación:"));
            panelBasico.add(campoAñoFabrica);
            panelBasico.add(new JLabel("Cilindraje:"));
            panelBasico.add(campoCilindraje);
            panelBasico.add(new JLabel("Potencia Fiscal:"));
            panelBasico.add(campoPotenciaFiscal);
            panelBasico.add(new JLabel("Precio Base:"));
            panelBasico.add(campoPrecioBase);
            panelBasico.add(new JLabel("Color:"));
            panelBasico.add(campoColor);
            panelBasico.add(new JLabel("Número de Bastidor:"));
            panelBasico.add(campoNumeroBastidor);
            
            // Pestaña de características técnicas
            JPanel panelTecnico = new JPanel(new GridLayout(0, 2, 10, 10));
            panelTecnico.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            // Campos del formulario - Características técnicas
            JTextField campoUbicacion = new JTextField(vehiculo.ubicacion);
            JTextField campoTransmision = new JTextField(vehiculo.transmision);
            JTextField campoTraccion = new JTextField(vehiculo.traccion);
            JTextField campoCombustible = new JTextField(vehiculo.combustible);
            JTextField campoEficiencia = new JTextField(vehiculo.eficienciaCombustible);
            JTextField campoMantenimientos = new JTextField(vehiculo.mantenimientos);
            
            // Agregar componentes al panel técnico
            panelTecnico.add(new JLabel("Ubicación:"));
            panelTecnico.add(campoUbicacion);
            panelTecnico.add(new JLabel("Transmisión:"));
            panelTecnico.add(campoTransmision);
            panelTecnico.add(new JLabel("Tracción:"));
            panelTecnico.add(campoTraccion);
            panelTecnico.add(new JLabel("Combustible:"));
            panelTecnico.add(campoCombustible);
            panelTecnico.add(new JLabel("Eficiencia de Combustible:"));
            panelTecnico.add(campoEficiencia);
            panelTecnico.add(new JLabel("Mantenimientos:"));
            panelTecnico.add(campoMantenimientos);
            
            // Pestaña de características
            JPanel panelCaracteristicas = new JPanel(new BorderLayout());
            panelCaracteristicas.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            // Panel para características básicas
            JPanel panelBasicos = new JPanel(new GridLayout(0, 1));
            panelBasicos.setBorder(BorderFactory.createTitledBorder("Características Básicas"));
            
            // Checkboxes para características básicas
            Map<Caracteristicas, JCheckBox> checkboxesBasicos = new HashMap<>();
            for (Caracteristicas c : Caracteristicas.values()) {
                JCheckBox checkbox = new JCheckBox(c.toString(), vehiculo.Basicos.contains(c));
                checkboxesBasicos.put(c, checkbox);
                panelBasicos.add(checkbox);
            }
            
            // Panel para características extras
            JPanel panelExtras = new JPanel(new GridLayout(0, 3));
            panelExtras.setBorder(BorderFactory.createTitledBorder("Características Extras (Precio)"));
            
            // Campos para precios de extras
            Map<Caracteristicas, JTextField> camposExtras = new HashMap<>();
            for (Caracteristicas c : Caracteristicas.values()) {
                JLabel label = new JLabel(c.toString() + ":");
                Integer precio = vehiculo.Extras.get(c);
                JTextField campo = new JTextField(precio != null ? precio.toString() : "500000");
                camposExtras.put(c, campo);
                panelExtras.add(label);
                panelExtras.add(campo);
                panelExtras.add(new JLabel("COP"));
            }
            
            // Agregar paneles de características al panel principal
            JPanel panelCaracteristicasContenido = new JPanel(new GridLayout(2, 1));
            panelCaracteristicasContenido.add(panelBasicos);
            panelCaracteristicasContenido.add(new JScrollPane(panelExtras));
            panelCaracteristicas.add(panelCaracteristicasContenido, BorderLayout.CENTER);
            
            // Agregar pestañas
            pestañas.addTab("Información Básica", panelBasico);
            pestañas.addTab("Características Técnicas", panelTecnico);
            pestañas.addTab("Características y Extras", panelCaracteristicas);
            
            // Panel de botones
            JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton btnGuardar = new JButton("Guardar");
            JButton btnCancelar = new JButton("Cancelar");
            
            btnGuardar.addActionListener(e -> {
                try {
                    // Validar campos obligatorios
                    if (campoMarca.getText().isEmpty() || campoModelo.getText().isEmpty() || 
                        campoPrecioBase.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(dialogo, 
                                "Los campos Marca, Modelo y Precio Base son obligatorios", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    // Recopilar características básicas seleccionadas
                    List<Caracteristicas> caracteristicasBasicas = new ArrayList<>();
                    for (Map.Entry<Caracteristicas, JCheckBox> entry : checkboxesBasicos.entrySet()) {
                        if (entry.getValue().isSelected()) {
                            caracteristicasBasicas.add(entry.getKey());
                        }
                    }
                    
                    // Crear vehículo actualizado
                    Vehiculo vehiculoActualizado = new Vehiculo(
                            campoNuevo.isSelected(),
                            campoAñoFabrica.getText(),
                            campoCilindraje.getText(),
                            campoPotenciaFiscal.getText(),
                            Integer.parseInt(campoPrecioBase.getText()),
                            caracteristicasBasicas,
                            campoColor.getText(),
                            vehiculo.numeroBastidor, // Mantener el mismo número de bastidor
                            campoUbicacion.getText(),
                            campoTransmision.getText(),
                            campoTraccion.getText(),
                            campoCombustible.getText(),
                            campoEficiencia.getText()
                    );
                    
                    // Establecer campos adicionales
                    vehiculoActualizado.Marca = campoMarca.getText();
                    vehiculoActualizado.Modelo = campoModelo.getText();
                    vehiculoActualizado.tipoVehiculo = campoTipoVehiculo.getText();
                    vehiculoActualizado.Carroceria = campoCarroceria.getText();
                    vehiculoActualizado.mantenimientos = campoMantenimientos.getText();
                    
                    // Configurar extras con precios personalizados
                    vehiculoActualizado.Extras.clear();
                    for (Map.Entry<Caracteristicas, JTextField> entry : camposExtras.entrySet()) {
                        if (!checkboxesBasicos.get(entry.getKey()).isSelected()) {
                            try {
                                int precio = Integer.parseInt(entry.getValue().getText());
                                vehiculoActualizado.Extras.put(entry.getKey(), precio);
                            } catch (NumberFormatException ex) {
                                vehiculoActualizado.Extras.put(entry.getKey(), 500000); // Valor por defecto
                            }
                        }
                    }
                    
                    // Eliminar el vehículo antiguo y agregar el actualizado
                    vehiculoCRUD.eliminar(vehiculo.numeroBastidor);
                    vehiculoCRUD.agregar(vehiculoActualizado);
                    
                    JOptionPane.showMessageDialog(dialogo, 
                            "Vehículo actualizado correctamente", 
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    
                    dialogo.dispose();
                    listarTodos(); // Actualizar tabla
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialogo, 
                            "Error al actualizar: " + ex.getMessage(), 
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            
            btnCancelar.addActionListener(e -> dialogo.dispose());
            
            panelBotones.add(btnGuardar);
            panelBotones.add(btnCancelar);
            
            dialogo.add(pestañas, BorderLayout.CENTER);
            dialogo.add(panelBotones, BorderLayout.SOUTH);
            dialogo.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                    "Error al cargar el vehículo: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Confirma y elimina un vehículo
     */
    @Override
    protected void confirmarEliminar() {
        int filaSeleccionada = tablaResultados.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, 
                    "Debe seleccionar un vehículo para eliminar", 
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String numeroBastidor = (String) tablaResultados.getValueAt(filaSeleccionada, 7);
        String marca = (String) tablaResultados.getValueAt(filaSeleccionada, 0);
        String modelo = (String) tablaResultados.getValueAt(filaSeleccionada, 1);
        
        int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de eliminar el vehículo " + marca + " " + modelo + "?", 
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // Eliminar vehículo
                vehiculoCRUD.eliminar(numeroBastidor);
                JOptionPane.showMessageDialog(this, 
                        "Vehículo eliminado correctamente", 
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                listarTodos(); // Actualizar tabla
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                        "Error al eliminar: " + e.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Lista todos los vehículos
     */
    @Override
    protected void listarTodos() {
        try {
            ArrayList<Vehiculo> vehiculos = vehiculoCRUD.listarTodo();
            actualizarTabla(vehiculos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                    "Error al listar vehículos: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Busca vehículos según el texto ingresado
     */
    @Override
    protected void buscar() {
        String textoBusqueda = campoBusqueda.getText().trim();
        if (textoBusqueda.isEmpty()) {
            listarTodos();
            return;
        }
        
        try {
            ArrayList<Vehiculo> vehiculos = vehiculoCRUD.listarTodo();
            ArrayList<Vehiculo> resultados = new ArrayList<>();
            
            for (Vehiculo v : vehiculos) {
                if ((v.Marca != null && v.Marca.toLowerCase().contains(textoBusqueda.toLowerCase())) || 
                    (v.Modelo != null && v.Modelo.toLowerCase().contains(textoBusqueda.toLowerCase())) ||
                    (v.tipoVehiculo != null && v.tipoVehiculo.toLowerCase().contains(textoBusqueda.toLowerCase())) ||
                    (v.numeroBastidor != null && v.numeroBastidor.contains(textoBusqueda))) {
                    resultados.add(v);
                }
            }
            
            actualizarTabla(resultados);
            
            if (resultados.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                        "No se encontraron vehículos con el criterio de búsqueda", 
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                    "Error al buscar vehículos: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Vuelve a la pantalla anterior
     */
    @Override
    protected void volver() {
        dispose();
        new VentanaInicio();
    }
    
    /**
     * Método principal para pruebas
     */
    public static void main(String[] args) {
        new VehiculoGUI();
    }
}