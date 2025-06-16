package co.edu.udc.poo.concesionario.vistas.gui;

import co.edu.udc.poo.concesionario.modelo.crud.MarcaCRUD;
import co.edu.udc.poo.concesionario.modelo.crud.VehiculoCRUD;
import co.edu.udc.poo.concesionario.modelo.crud.VentasCRUD;
import co.edu.udc.poo.concesionario.modelo.entidades.*;

import javax.swing.*;
import java.awt.*;

public class PlantillaSedesOficina extends JFrame {

    public PlantillaSedesOficina() {
        Dimension medPantalla = new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE);

        setTitle("CASTAÑO & TOVAR Motor Company");
        setSize(new Dimension(700,400));
        setMinimumSize(new Dimension(700,400));
        setPreferredSize(medPantalla);
        setMaximumSize(medPantalla);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en la pantalla

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        // PANEL SUPERIOR
        JPanel panelSuperior = new JPanel();
        GroupLayout layoutSuperior = new GroupLayout(panelSuperior);
        panelSuperior.setLayout(layoutSuperior);
        panelSuperior.setPreferredSize(new Dimension(700, 80));
        panelSuperior.setBackground(new Color(41, 83, 197, 255));

        JLabel tituloPrincipal = new JLabel("SEDES DEL CONCESIONARIO");
        tituloPrincipal.setFont(new Font("Tahoma", Font.BOLD, 36));
        tituloPrincipal.setForeground(Color.black);

        JLabel tituloSecundario = new JLabel("Ubicaciones Disponibles");
        tituloSecundario.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tituloSecundario.setForeground(Color.black);

        layoutSuperior.setAutoCreateGaps(true);
        layoutSuperior.setAutoCreateContainerGaps(true);

        // Diseño horizontal y vertical
        layoutSuperior.setHorizontalGroup(
                layoutSuperior.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                        .addComponent(tituloPrincipal)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tituloSecundario)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
        );

        layoutSuperior.setVerticalGroup(
                layoutSuperior.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(tituloPrincipal)
                        .addComponent(tituloSecundario)
        );

        // PANEL INFERIOR CON EL ICONO "SOPORTE" JUNTO A "BUSCAR"
        JPanel panelInferior = createPanelInferior();

        // PANEL CENTRAL CON FONDO Y BOTONES
        JPanel panelCentral = createPanelCentralConFondo();

        // ENSAMBLAR TODOS LOS PANELES
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);
        setVisible(true);
    }


    // Método para crear el panel inferior
    private JPanel createPanelInferior() {
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelInferior.setBackground(Color.LIGHT_GRAY);
        panelInferior.setPreferredSize(new Dimension(700, 50)); // Tamaño fijo del panel inferior

        // Campo de búsqueda (SIN CAMBIOS)
        JTextField campoBusqueda = new JTextField("Buscar sede: ");
        campoBusqueda.setPreferredSize(new Dimension(400, 30));

        // Botón de búsqueda
        ImageIcon iconoBuscar = new ImageIcon("src/main/resources/busqueda.png");
        JButton botonBuscar = new JButton(iconoBuscar);
        botonBuscar.setPreferredSize(new Dimension(100, 30));

        // Botón de soporte (icono)
        ImageIcon iconoSoporte = new ImageIcon("src/main/resources/soporte.png");
        JButton botonSoporte = new JButton(iconoSoporte);
        botonSoporte.setPreferredSize(new Dimension(30, 30)); // Botón pequeño
        botonSoporte.setFocusPainted(false);
        botonSoporte.setBackground(new Color(148, 196, 255, 255)); // Color de fondo
        botonSoporte.setToolTipText("Soporte técnico"); // Tooltip

        // Botón "Atrás"
        JButton botonAtras = new JButton("Atrás");
        botonAtras.setPreferredSize(new Dimension(100, 30));
        botonAtras.setBackground(new Color(148, 196, 255, 255));
        botonAtras.setForeground(Color.WHITE);

        // Agregar componentes al panel inferior
        panelInferior.add(campoBusqueda); // Primero el cuadro de búsqueda
        panelInferior.add(botonBuscar); // Luego el botón de búsqueda
        panelInferior.add(botonSoporte); // Después el icono de soporte
        panelInferior.add(botonAtras); // Al final el botón atrás

        return panelInferior;
    }

    // Método para crear el panel central con fondo
    private JPanel createPanelCentralConFondo() {
        JPanel panelCentral = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagenFondo = new ImageIcon("src/main/resources/fondo.png");
                g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        panelCentral.setLayout(new GridBagLayout());
        panelCentral.setPreferredSize(new Dimension(700, 250));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel fila1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        fila1.setOpaque(false);

        JButton boton1 = new JButton("Marcas");
        JPopupMenu listaMarcas = new JPopupMenu();
        try {
            for (Marca m : MarcaCRUD.listarTodo()) {
                listaMarcas.add(m.marca);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        boton1.addActionListener(e -> listaMarcas.show(boton1, 0, boton1.getHeight()));

        JButton boton2 = new JButton("Vehiculos");
        JPopupMenu listaVehiculos = new JPopupMenu();
        VehiculoCRUD crudVehiculo = new VehiculoCRUD();
        try{
            for (Vehiculo v : crudVehiculo.listarTodo()){
                listaVehiculos.add(v.tipoVehiculo);
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        boton2.addActionListener(e -> listaVehiculos.show(boton2, 0, boton2.getHeight()));

        JButton boton3 = new JButton("Vitrina");
        JPopupMenu listaVitrina = new JPopupMenu();

        fila1.add(boton1);
        fila1.add(boton2);
        fila1.add(boton3);

        JPanel fila2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        fila2.setOpaque(false);

        JButton boton4 = new JButton("Ventas");
        JPopupMenu listaVentas = new JPopupMenu();
        VentasCRUD crudVentas = new VentasCRUD();
        try {
            for (Ventas v : crudVentas.listarTodo()){
                listaVentas.add(v.registroVenta);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        JButton boton5 = new JButton("Vendedores");
        JButton boton6 = new JButton("Clientes");

        fila2.add(boton4);
        fila2.add(boton5);
        fila2.add(boton6);

        JButton[] botones = {boton1, boton2, boton3, boton4, boton5, boton6};
        for (JButton boton : botones) {
            boton.setBackground(new Color(41, 83, 197, 255));
            boton.setForeground(Color.white);
            boton.setFont(new Font("Tahoma", Font.BOLD, 14));
            boton.setPreferredSize(new Dimension(150, 50));
        }

        gbc.gridx = 0; // Columna
        gbc.gridy = 0;
        panelCentral.add(fila1, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 0, 10);
        panelCentral.add(fila2, gbc);

        return panelCentral;
    }

    public static void main(String[] args) throws Exception{
        new PlantillaSedesOficina();
    }
}
