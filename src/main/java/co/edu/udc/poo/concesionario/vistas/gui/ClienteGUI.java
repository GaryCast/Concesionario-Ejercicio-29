package co.edu.udc.poo.concesionario.vistas.gui;

import co.edu.udc.poo.concesionario.modelo.crud.ClienteCRUD;
import co.edu.udc.poo.concesionario.modelo.entidades.Cliente;
import co.edu.udc.poo.concesionario.modelo.entidades.Satisfaccion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Interfaz gráfica para la gestión de clientes
 */
public class ClienteGUI extends PlantillaCRUD {
    
    private DefaultTableModel modeloTabla;
    
    /**
     * Constructor de la clase
     */
    public ClienteGUI() {
        super("Clientes", "Gestión de Clientes");
        
        try {
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
        String[] columnas = {"Nombre", "Cédula", "Ciudad", "Teléfono", "Correo", "Nivel Satisfacción"};
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
     * Actualiza la tabla con la lista de clientes proporcionada
     */
    private void actualizarTabla(ArrayList<Cliente> clientes) {
        modeloTabla.setRowCount(0); // Limpiar tabla
        
        for (Cliente cliente : clientes) {
            Object[] fila = {
                cliente.nombre,
                cliente.cedula,
                cliente.ciudad,
                cliente.telefono,
                cliente.correo,
                getSatisfaccionText(cliente.nivelSatisfaccion)
            };
            modeloTabla.addRow(fila);
        }
    }
    
    /**
     * Convierte el valor numérico de satisfacción a texto
     */
    private String getSatisfaccionText(int nivel) {
        if (nivel >= 0 && nivel < Satisfaccion.values().length) {
            return Satisfaccion.values()[nivel].toString();
        }
        return "Desconocido";
    }
    
    /**
     * Muestra el formulario para agregar un nuevo cliente
     */
    @Override
    protected void mostrarFormularioAgregar() {
        JDialog dialogo = new JDialog(this, "Agregar Cliente", true);
        dialogo.setSize(400, 500);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout());
        
        JPanel panelFormulario = new JPanel(new GridLayout(0, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Campos del formulario
        JTextField campoNombre = new JTextField();
        JTextField campoCedula = new JTextField();
        JTextField campoDepartamento = new JTextField();
        JTextField campoCiudad = new JTextField();
        JTextField campoDireccion = new JTextField();
        JTextField campoTelefono = new JTextField();
        JTextField campoCorreo = new JTextField();
        JCheckBox campoContactoInicial = new JCheckBox();
        JTextField campoReferidoPor = new JTextField();
        JTextField campoReferidoTel = new JTextField();
        JComboBox<String> campoSatisfaccion = new JComboBox<>();
        for (Satisfaccion s : Satisfaccion.values()) {
            campoSatisfaccion.addItem(s.toString());
        }
        
        // Agregar componentes al panel
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(campoNombre);
        panelFormulario.add(new JLabel("Cédula:"));
        panelFormulario.add(campoCedula);
        panelFormulario.add(new JLabel("Departamento:"));
        panelFormulario.add(campoDepartamento);
        panelFormulario.add(new JLabel("Ciudad:"));
        panelFormulario.add(campoCiudad);
        panelFormulario.add(new JLabel("Dirección:"));
        panelFormulario.add(campoDireccion);
        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(campoTelefono);
        panelFormulario.add(new JLabel("Correo:"));
        panelFormulario.add(campoCorreo);
        panelFormulario.add(new JLabel("Contacto Inicial:"));
        panelFormulario.add(campoContactoInicial);
        panelFormulario.add(new JLabel("Referido Por:"));
        panelFormulario.add(campoReferidoPor);
        panelFormulario.add(new JLabel("Teléfono Referido:"));
        panelFormulario.add(campoReferidoTel);
        panelFormulario.add(new JLabel("Nivel Satisfacción:"));
        panelFormulario.add(campoSatisfaccion);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnGuardar.addActionListener(e -> {
            try {
                // Validar campos obligatorios
                if (campoNombre.getText().isEmpty() || campoCedula.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(dialogo, 
                            "Los campos Nombre y Cédula son obligatorios", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Crear nuevo cliente
                Cliente nuevoCliente = new Cliente(
                        campoNombre.getText(),
                        campoCedula.getText(),
                        campoDepartamento.getText(),
                        campoCiudad.getText(),
                        campoDireccion.getText(),
                        campoTelefono.getText(),
                        campoCorreo.getText()
                );
                
                // Establecer campos adicionales
                nuevoCliente.contactoInicial = campoContactoInicial.isSelected();
                nuevoCliente.referidoPor = campoReferidoPor.getText();
                nuevoCliente.referidoTel = campoReferidoTel.getText();
                nuevoCliente.nivelSatisfaccion = campoSatisfaccion.getSelectedIndex();
                
                // Guardar cliente
                ClienteCRUD.agregar(nuevoCliente);
                JOptionPane.showMessageDialog(dialogo, 
                        "Cliente agregado correctamente", 
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
        
        dialogo.add(panelFormulario, BorderLayout.CENTER);
        dialogo.add(panelBotones, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    
    /**
     * Muestra el formulario para editar un cliente existente
     */
    @Override
    protected void mostrarFormularioEditar() {
        int filaSeleccionada = tablaResultados.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, 
                    "Debe seleccionar un cliente para editar", 
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String cedula = (String) tablaResultados.getValueAt(filaSeleccionada, 1);
        System.out.println("Cedula seleccionada: "+cedula);
        
        try {
            // Buscar cliente por cédula
            Cliente cliente = ClienteCRUD.buscar(cedula);
            
            if (cliente == null) {
                JOptionPane.showMessageDialog(this, 
                        "No se encontró el cliente", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Crear diálogo de edición
            JDialog dialogo = new JDialog(this, "Editar Cliente", true);
            dialogo.setSize(400, 500);
            dialogo.setLocationRelativeTo(this);
            dialogo.setLayout(new BorderLayout());
            
            JPanel panelFormulario = new JPanel(new GridLayout(0, 2, 10, 10));
            panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            // Campos del formulario
            JTextField campoNombre = new JTextField(cliente.nombre);
            JTextField campoCedula = new JTextField(cliente.cedula);
            campoCedula.setEditable(false); // No permitir cambiar la cédula
            JTextField campoDepartamento = new JTextField(cliente.departamento);
            JTextField campoCiudad = new JTextField(cliente.ciudad);
            JTextField campoDireccion = new JTextField(cliente.direccion);
            JTextField campoTelefono = new JTextField(cliente.telefono);
            JTextField campoCorreo = new JTextField(cliente.correo);
            JCheckBox campoContactoInicial = new JCheckBox("", cliente.contactoInicial);
            JTextField campoReferidoPor = new JTextField(cliente.referidoPor);
            JTextField campoReferidoTel = new JTextField(cliente.referidoTel);
            JComboBox<String> campoSatisfaccion = new JComboBox<>();
            for (Satisfaccion s : Satisfaccion.values()) {
                campoSatisfaccion.addItem(s.toString());
            }
            campoSatisfaccion.setSelectedIndex(cliente.nivelSatisfaccion-1);
            
            // Agregar componentes al panel
            panelFormulario.add(new JLabel("Nombre:"));
            panelFormulario.add(campoNombre);
            panelFormulario.add(new JLabel("Cédula:"));
            panelFormulario.add(campoCedula);
            panelFormulario.add(new JLabel("Departamento:"));
            panelFormulario.add(campoDepartamento);
            panelFormulario.add(new JLabel("Ciudad:"));
            panelFormulario.add(campoCiudad);
            panelFormulario.add(new JLabel("Dirección:"));
            panelFormulario.add(campoDireccion);
            panelFormulario.add(new JLabel("Teléfono:"));
            panelFormulario.add(campoTelefono);
            panelFormulario.add(new JLabel("Correo:"));
            panelFormulario.add(campoCorreo);
            panelFormulario.add(new JLabel("Contacto Inicial:"));
            panelFormulario.add(campoContactoInicial);
            panelFormulario.add(new JLabel("Referido Por:"));
            panelFormulario.add(campoReferidoPor);
            panelFormulario.add(new JLabel("Teléfono Referido:"));
            panelFormulario.add(campoReferidoTel);
            panelFormulario.add(new JLabel("Nivel Satisfacción:"));
            panelFormulario.add(campoSatisfaccion);
            
            // Panel de botones
            JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton btnGuardar = new JButton("Guardar");
            JButton btnCancelar = new JButton("Cancelar");
            
            btnGuardar.addActionListener(e -> {
                try {
                    if (campoNombre.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(dialogo, 
                                "El campo Nombre es obligatorio", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    // Actualizar cliente
                    Cliente clienteActualizado = new Cliente(
                            campoNombre.getText(),
                            cliente.cedula,
                            campoDepartamento.getText(),
                            campoCiudad.getText(),
                            campoDireccion.getText(),
                            campoTelefono.getText(),
                            campoCorreo.getText()
                    );
                    
                    // Establecer campos adicionales
                    clienteActualizado.contactoInicial = campoContactoInicial.isSelected();
                    clienteActualizado.referidoPor = campoReferidoPor.getText();
                    clienteActualizado.referidoTel = campoReferidoTel.getText();
                    clienteActualizado.nivelSatisfaccion = campoSatisfaccion.getSelectedIndex();
                    clienteActualizado.vehiculosComprados = cliente.vehiculosComprados;
                    
                    // Eliminar el cliente antiguo y agregar el actualizado
                    ClienteCRUD.eliminar(cliente.cedula);
                    ClienteCRUD.agregar(clienteActualizado);
                    
                    JOptionPane.showMessageDialog(dialogo, 
                            "Cliente actualizado correctamente", 
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
            
            dialogo.add(panelFormulario, BorderLayout.CENTER);
            dialogo.add(panelBotones, BorderLayout.SOUTH);
            dialogo.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                    "Error al cargar el cliente: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Confirma y elimina un cliente
     */
    @Override
    protected void confirmarEliminar() {
        int filaSeleccionada = tablaResultados.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, 
                    "Debe seleccionar un cliente para eliminar", 
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String cedula = (String) tablaResultados.getValueAt(filaSeleccionada, 1);
        String nombre = (String) tablaResultados.getValueAt(filaSeleccionada, 0);
        
        int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de eliminar al cliente " + nombre + "?", 
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // Eliminar cliente
                ClienteCRUD.eliminar(cedula);
                JOptionPane.showMessageDialog(this, 
                        "Cliente eliminado correctamente", 
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
     * Lista todos los clientes
     */
    @Override
    protected void listarTodos() {
        try {
            ArrayList<Cliente> clientes = ClienteCRUD.listarTodo();
            actualizarTabla(clientes);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                    "Error al listar clientes: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Busca clientes según el texto ingresado
     */
    @Override
    protected void buscar() {
        String textoBusqueda = campoBusqueda.getText().trim();
        if (textoBusqueda.isEmpty()) {
            listarTodos();
            return;
        }
        
        try {
            ArrayList<Cliente> clientes = ClienteCRUD.listarTodo();
            ArrayList<Cliente> resultados = new ArrayList<>();
            
            for (Cliente c : clientes) {
                if (c.nombre.toLowerCase().contains(textoBusqueda.toLowerCase()) || 
                    c.cedula.contains(textoBusqueda) ||
                    c.ciudad.toLowerCase().contains(textoBusqueda.toLowerCase())) {
                    resultados.add(c);
                }
            }
            
            actualizarTabla(resultados);
            
            if (resultados.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                        "No se encontraron clientes con el criterio de búsqueda", 
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                    "Error al buscar clientes: " + e.getMessage(), 
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
        new ClienteGUI();
    }
}