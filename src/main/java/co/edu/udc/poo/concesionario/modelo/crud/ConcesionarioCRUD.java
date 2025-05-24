package co.edu.udc.poo.concesionario.modelo.crud;

import co.edu.udc.poo.concesionario.modelo.entidades.*;
import co.edu.udc.poo.concesionario.util.Datos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class ConcesionarioCRUD {
    public static ArrayList<Concesionario> concesionarios = new ArrayList<>();
    public static  void main(String[] args) throws Exception{
        for(Concesionario concesionario : listarTodo()){
            System.out.println("nombre concesionario: "+concesionario.nombre);
        }
    }
    public static void agregar(Concesionario concesionario)throws Exception{
        cargarDatos();
        for (Concesionario concesionario1 : concesionarios) {
            if (concesionario1.nif.equals(concesionario.nif)){
                throw new Exception("Ya existe un concesionario con ese nif");
            }
        }
        concesionarios.add(concesionario);
        guardar();
    }

    public static void cargarDatos() throws Exception{
        Gson gson = new Gson();
        FileReader lector = new FileReader("databaseJson/Concesionarios.json");
        Type ConcesionariosTypeList = new TypeToken<ArrayList<Concesionario>>(){}.getType();
        concesionarios = gson.fromJson(lector, ConcesionariosTypeList);
    }

    public static void guardar() throws Exception{
        Gson gson = new Gson();
        String json = gson.toJson(concesionarios);
        Datos.escribirArchivo("databaseJson/Concesionarios.json", json);
    }

    public static Concesionario buscar(String nif) throws Exception{
        cargarDatos();
        for (Concesionario concesionario : concesionarios) {
            if (concesionario.nif.equals(nif)){
                return concesionario;
            }
        }
        throw new Exception("No existe un concesionario con ese nif");
    }

    public static void editar(Concesionario concesionario) throws Exception{
        cargarDatos();
        Scanner entrada = new Scanner(System.in);
        for (Concesionario concesionario1 : concesionarios) {
            if (concesionario1.nif.equals(concesionario.nif)){
                int index = concesionarios.indexOf(concesionario1);
                System.out.println("que quieres editar");

                System.out.println("1)Nombre: " + concesionario.nombre);
                System.out.println("2)NIF: " + concesionario.nif);
                System.out.println("3)Departamento: " + concesionario.departamento);
                System.out.println("4)Ciudad: " + concesionario.ciudad);
                System.out.println("5)Direccion: " + concesionario.direccion);
                System.out.println("6)Telefono: " + concesionario.telefono);
                System.out.println("7)Correo: " + concesionario.correo);
                System.out.println("8)Marcas: ");
                System.out.println("9)Bodega: " + concesionario.bodega.nombre);
                System.out.println("10)Lista de clientes: " + concesionario.listaClientes);
                System.out.println("11)Oficinas: " + concesionario.oficinas);
                System.out.println("12)Servicios oficiales: " + concesionario.serviciosOficiales);
                System.out.println("13)Ventas: " + concesionario.ventas);
                System.out.println("14)Vendedores: " + concesionario.vendedores);
                System.out.println("15)Vehiculos: " + concesionario.vehiculos);
                int opcion = Integer.parseInt(entrada.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.println("nuevo nombre: ");
                        String nombre = entrada.nextLine();
                        concesionario.nombre = nombre;
                        concesionarios.set(index,concesionario);
                        guardar();
                        break;
                    case 2:
                        System.out.println("nuevo nif: ");
                        String nif = entrada.nextLine();
                        concesionario.nif = nif;
                        concesionarios.set(index,concesionario);
                        guardar();
                        break;
                    case 3:
                        System.out.println("nuevo departamento: ");
                        String departamento = entrada.nextLine();
                        concesionario.departamento = departamento;
                        concesionarios.set(index,concesionario);
                        guardar();
                        break;
                    case 4:
                        System.out.println("nueva ciudad: ");
                        String ciudad = entrada.nextLine();
                        concesionario.ciudad = ciudad;
                        concesionarios.set(index,concesionario);
                        guardar();
                        break;
                    case 5:
                        System.out.println("nueva direccion: ");
                        String direccion = entrada.nextLine();
                        concesionario.direccion = direccion;
                        concesionarios.set(index,concesionario);
                        guardar();
                        break;
                    case 6:
                        System.out.println("nuevo telefono: ");
                        String telefono = entrada.nextLine();
                        concesionario.telefono = telefono;
                        concesionarios.set(index,concesionario);
                        guardar();
                        break;
                    case 7:
                        System.out.println("nuevo correo: ");
                        String correo = entrada.nextLine();
                        concesionario.correo = correo;
                        concesionarios.set(index,concesionario);
                        guardar();
                        break;
                    case 8:
                        System.out.println("nueva marca: ");
                        String nombreMarca = entrada.nextLine();
                        Marca marca = new Marca(nombreMarca);
                        concesionario.marcas.add(marca);
                        concesionarios.set(index,concesionario);
                        guardar();
                        break;
                    case 9:
                        System.out.println("nueva bodega: ");
                        String nombreBodega = entrada.nextLine();
                        System.out.println("Ubicación: ");
                        String ubicacion = entrada.nextLine();
                        Bodega bodega = new Bodega(nombreBodega,ubicacion);
                        concesionario.bodega = bodega;
                        concesionarios.set(index,concesionario);
                        guardar();
                        break;
                    case 10:
                        cargarDatos();
                        for (Cliente cliente : concesionario.listaClientes) {
                            System.out.println(cliente.nombre+" "+cliente.cedula);
                        }
                        System.out.println("1) Agregar cliente");
                        System.out.println("2) Eliminar cliente");
                        String opcionCliente = entrada.nextLine();
                        if (opcionCliente.equals("1")){ // nombre, cedula, departamento, ciudad, direccion, telefono, correo
                            System.out.println("Nombre de cliente: ");
                            String nombreCliente = entrada.nextLine();
                            System.out.println("Cedula de cliente: ");
                            String cedulaCliente = entrada.nextLine();
                            System.out.println("Departamento de cliente: ");
                            String departamentoCliente = entrada.nextLine();
                            System.out.println("Ciudad de cliente: ");
                            String ciudadCliente = entrada.nextLine();
                            System.out.println("Direccion de cliente: ");
                            String direccionCliente = entrada.nextLine();
                            System.out.println("Telefono de cliente: ");
                            String telefonoCliente = entrada.nextLine();
                            System.out.println("Correo de cliente: ");
                            String correoCliente = entrada.nextLine();
                            Cliente cliente = new Cliente(nombreCliente,cedulaCliente,departamentoCliente,ciudadCliente,direccionCliente,telefonoCliente,correoCliente);
                            concesionario.listaClientes.add(cliente);
                            guardar();
                        }else if (opcionCliente.equals("2")){
                            System.out.println("Cedula del cliente a eliminar: ");
                            String cedulaCliente = entrada.nextLine();
                            Boolean condicion = false;
                            for (Cliente cliente : concesionario.listaClientes) {
                                if (cliente.cedula.equals(cedulaCliente)){
                                    concesionario.listaClientes.remove(cliente);
                                    condicion = true;
                                    break;
                                }
                            }
                            if(!condicion){
                                throw  new Exception("Cliente no existe ");
                            }
                        }
                        break;
                    case 11:
                        cargarDatos();
                        for (Oficina oficina : concesionario.oficinas) {
                            System.out.println(oficina.nombre+" "+oficina.nif);
                        }
                        System.out.println("1) Agregar oficina");
                        System.out.println("2) Eliminar oficina");
                        String opcionOficina = entrada.nextLine();
                        if (opcionOficina.equals("1")){ // nombre, nif, departamento, ciudad, direccion, telefono, correo, marca, vendedor
                            System.out.println("nombre de la oficina: ");
                            String nombreOficina = entrada.nextLine();
                            System.out.println("nif de la oficina: ");
                            String nifOficina = entrada.nextLine();
                            System.out.println("nuevo departamento: ");
                            String departamentoOficina = entrada.nextLine();
                            System.out.println("ciudad de la oficina: ");
                            String ciudadOficina = entrada.nextLine();
                            System.out.println("direccion de la oficina: ");
                            String direccionOficina = entrada.nextLine();
                            System.out.println("telefono de la oficina: ");
                            String telefonoOficina = entrada.nextLine();
                            System.out.println("correo de la oficina: ");
                            String correoOficina = entrada.nextLine();
                            Oficina nueva = new Oficina(nombreOficina,nifOficina,departamentoOficina,ciudadOficina,direccionOficina,telefonoOficina,correoOficina);
                            concesionario.oficinas.add(nueva);
                            concesionarios.set(index,concesionario);
                            guardar();

                        }else if (opcionOficina.equals("2")){
                            System.out.println("nif de la oficina a eliminar");
                            String nifOficina = entrada.nextLine();
                            Boolean condicion = false;
                            for (Oficina oficina : concesionario.oficinas) {
                                if (oficina.nif.equals(nifOficina)){
                                concesionario.oficinas.remove(oficina);
                                condicion = true;
                                break;
                                }
                            }
                            if(!condicion) {
                                throw new Exception("Oficina no existe ");
                            }
                        }
                        break;
                    case 12:
                        cargarDatos();
                        for (ServicioOficial servicioOficial : concesionario.serviciosOficiales) {
                            System.out.println(servicioOficial.nombre+" "+servicioOficial.nif);
                        }
                        System.out.println("1) Agregar servicio oficial");
                        System.out.println("2) Eliminar servicio oficial");
                        String opcionServicioOficial = entrada.nextLine();
                        if (opcionServicioOficial.equals("1")){ // nombre, nif, departamento, ciudad, direccion, telefono, correo, marca, vendedor
                            System.out.println("nombre del servicio oficial: ");
                            String nombreServicioOficial = entrada.nextLine();
                            System.out.println("nif del servicio oficial: ");
                            String nifServicioOficial = entrada.nextLine();
                            System.out.println("nuevo departamento: ");
                            String departamentoOficina = entrada.nextLine();
                            System.out.println("ciudad del servicio oficial: ");
                            String ciudadServicioOficial = entrada.nextLine();
                            System.out.println("direccion del servicio oficial: ");
                            String direccionServicioOficial = entrada.nextLine();
                            System.out.println("telefono del servicio oficial: ");
                            String telefonoServicioOficial = entrada.nextLine();
                            System.out.println("correo del servicio oficial: ");
                            String correServicioOficial = entrada.nextLine();
                            ServicioOficial nuevo = new ServicioOficial(nombreServicioOficial,nifServicioOficial,departamentoOficina,ciudadServicioOficial,direccionServicioOficial,telefonoServicioOficial,correServicioOficial);
                            concesionario.serviciosOficiales.add(nuevo);
                            concesionarios.set(index,concesionario);
                            guardar();
                        }else if (opcionServicioOficial.equals("2")){
                            System.out.println("nif del servicio oficial a eliminar");
                            String nifServicioOficial = entrada.nextLine();
                            Boolean condicion = false;
                            for (ServicioOficial servicioOficial : concesionario.serviciosOficiales) {
                                if (servicioOficial.nif.equals(nifServicioOficial)){
                                    concesionario.serviciosOficiales.remove(servicioOficial);
                                    condicion = true;
                                    break;
                                }
                            }
                            if(!condicion) {
                                throw new Exception("Servicio Oficial no existe ");
                            }
                        }
                        break;
                    case 13:
                        cargarDatos();
                        for (Ventas ventas : concesionario.ventas){
                            System.out.println(ventas.registroVenta);
                        }
                        System.out.println("1) Agregar Venta.");
                        System.out.println("2) Eliminar Venta");
                        String opcionVentas = entrada.nextLine();
                        if (opcionVentas.equals("1")) {//registroVenta,fechaHora, vendedor, precioFinal, vehiculo, ubicacion
                            System.out.println("Registro de la venta: ");
                            String registroVenta = entrada.nextLine();
                            System.out.println("Fecha y hora de la venta: ");
                            Date fechaHora = Date.valueOf(entrada.nextLine());
                            System.out.println("Nif del vendedor: ");
                            String nifVendedor = entrada.nextLine();
                            System.out.println("Precio final de la venta: ");
                            double precioFinal = Double.parseDouble(entrada.nextLine());
                            System.out.println("Nif del vehiculo: ");
                            String nifVehiculo = entrada.nextLine();
                            System.out.println("Ubicacion del vehiculo: ");
                            VendedorCRUD crudVendedor = new VendedorCRUD();
                            Vendedor vendedor = crudVendedor.buscar(nifVendedor);
                            PrecioVenta precioVenta = new PrecioVenta();
                            MetodoPago metodo = new MetodoPago();
                            Ventas nueva = new Ventas(registroVenta,fechaHora,vendedor,precioVenta,metodo,0,precioFinal,new Vehiculo(),nifVehiculo,new Vehiculo().ubicacion,fechaHora,new Cliente());
                            concesionario.ventas.add(nueva);
                            concesionarios.set(index,concesionario);
                            guardar();
                        } else if (opcionVentas.equals("2")) {
                            System.out.println("Registro de la venta a eliminar: ");
                            String registroVenta = entrada.nextLine();
                            Boolean condicion = false;
                            for (Ventas ventas : concesionario.ventas) {
                                if (ventas.registroVenta.equals(registroVenta)){
                                    concesionario.ventas.remove(ventas);
                                    condicion = true;
                                    break;
                                }
                            }
                            if(!condicion) {
                                throw new Exception("Venta no existe ");
                            }
                        }
                        break;
                    case 14:
                        cargarDatos();
                        for (Vendedor vendedor : concesionario.vendedores) {
                            System.out.println(vendedor.nombre+" "+vendedor.nif);
                        }
                        System.out.println("1) Agregar vendedor");
                        System.out.println("2) Eliminar vendedor");
                        String opcionVendedor = entrada.nextLine();
                        if (opcionVendedor.equals("1")) {//nombre, telefono, correo, nif
                            System.out.println("Nombre del vendedor: ");
                            String nombreVendedor = entrada.nextLine();
                            System.out.println("Sexo del vendedor: ");
                            String sexoVendedor = entrada.nextLine();
                            System.out.println("Edad del vendedor: ");
                            String edadVendedor = entrada.nextLine();
                            System.out.println("Direccion del vendedor: ");
                            String direccionVendedor = entrada.nextLine();
                            System.out.println("Telefono del vendedor: ");
                            String telefonoVendedor = entrada.nextLine();
                            System.out.println("Correo del vendedor: ");
                            String correoVendedor = entrada.nextLine();
                            System.out.println("Nif del vendedor: ");
                            String nifVendedor = entrada.nextLine();
                            Vendedor nuevoVendedor = new Vendedor(nombreVendedor,sexoVendedor,edadVendedor,direccionVendedor,telefonoVendedor,correoVendedor,nifVendedor,0,0);
                            concesionario.vendedores.add(nuevoVendedor);
                            concesionarios.set(index,concesionario);
                            guardar();
                        } else if (opcionVendedor.equals("2")) {
                            System.out.println("Nif del vendedor a eliminar: ");
                            String nifVendedor = entrada.nextLine();
                            Boolean condicion = false;
                            for (Vendedor vendedor : concesionario.vendedores) {
                                if (vendedor.nif.equals(nifVendedor)){
                                    concesionario.vendedores.remove(vendedor);
                                    condicion = true;
                                }
                            }
                            if(!condicion) {
                                throw new Exception("Vendedor no existe ");
                            }
                        }
                        break;
                    case 15:
                        cargarDatos();
                        for (Vehiculo vehiculo : concesionario.vehiculos) {
                            System.out.println(vehiculo.numeroBastidor);
                        }
                        System.out.println("1) Agregar vehiculo");
                        System.out.println("2) Eliminar vehiculo");
                        String opcionVehiculo = entrada.nextLine();
                        if (opcionVehiculo.equals("1")) {//numero de bastidor
                            System.out.println("Numero de bastidor: ");
                            String numeroBastidor = entrada.nextLine();
                            guardar();
                        } else if (opcionVehiculo.equals("2")) {
                            System.out.println("Numero de bastidor a eliminar: ");
                            String numeroBastidor = entrada.nextLine();
                            Boolean condicion = false;
                            for (Vehiculo vehiculo : concesionario.vehiculos) {
                                if (vehiculo.numeroBastidor.equals(numeroBastidor)){
                                    concesionario.vehiculos.remove(vehiculo);
                                    condicion = true;
                                }
                            }
                            if (!condicion){
                                throw new Exception("Vehiculo no existe");
                            }
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Opción desconocida: " + opcion);
                }
            }
        }
    }

    public static void eliminar(String nif) throws Exception{
        cargarDatos();
        Boolean condicion = false;
        for (Concesionario concesionario : concesionarios) {
            if (concesionario.nif.equals(nif)){
                concesionarios.remove(concesionario);
                guardar();
                condicion = true;
                break;
            }
        }
        if(!condicion) {
            throw new Exception("Concesionario no existe ");
        }

    }

    public static ArrayList<Concesionario> listarTodo() throws Exception{
        cargarDatos();
        return concesionarios;
    }

    public static Integer contar() throws Exception{
        return listarTodo().size();
    }
}
