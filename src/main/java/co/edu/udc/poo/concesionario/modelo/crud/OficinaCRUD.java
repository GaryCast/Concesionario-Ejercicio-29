package co.edu.udc.poo.concesionario.modelo.crud;

import co.edu.udc.poo.concesionario.modelo.entidades.*;
import co.edu.udc.poo.concesionario.util.Datos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;

public class OficinaCRUD {
    public static ArrayList<Oficina> listaOficinas = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        agregar(new Oficina());
    }

    public static void agregar(Oficina oficina) throws Exception {
        boolean existe = false;
        for(Oficina oficina1 : listarTodo()){
            if(oficina1.nif.equals(oficina.nif)){
                throw new Exception("Oficina ya existe");
            }
        }
        listaOficinas.add(oficina);
        guardar();
    }

    public static Oficina buscar(String nif) throws Exception {
        for(Oficina oficina: listarTodo()){
            if(oficina.nif.equals(nif)){
                return oficina;
            }
        }
        throw new Exception("Oficina no encontrada");
    }

    public static void editar(Oficina oficina) throws Exception {
        for(Oficina office: listarTodo()){
            if(office.nif.equals(oficina.nif)){
                int index = listaOficinas.indexOf(office);
                Scanner entrada = new Scanner(System.in);
                System.out.println("Editar oficina");
                System.out.println("1) Nombre: "+office.nombre);
                System.out.println("2) Departamento: "+office.departamento);
                System.out.println("3) Ciudad: "+office.ciudad);
                System.out.println("4) Dirección: "+office.direccion);
                System.out.println("5) Telefono: "+office.telefono);
                System.out.println("6) Correo: "+office.correo);
                System.out.println("7) Bodega: "+office.bodega.nombre);
                System.out.println("8) Clientes");
                System.out.println("9) ventas");
                System.out.println("10) Vehiculos");
                System.out.println("11) Vendedores");

                System.out.println("Elige la opción a editar (1-13)");
                String opcion = entrada.nextLine();
                switch (opcion){
                    case "1":
                        System.out.println("Nuevo nombre: ");
                        String nombre = entrada.nextLine();
                        office.nombre = nombre;
                        listaOficinas.set(index,office);
                        guardar();
                        break;
                    case "2":
                        System.out.println("Nuevo departamento: ");
                        String departamento = entrada.nextLine();
                        office.departamento = departamento;
                        listaOficinas.set(index,office);
                        guardar();
                        break;
                    case "3":
                        System.out.println("Nueva ciudad: ");
                        String ciudad = entrada.nextLine();
                        office.ciudad = ciudad;
                        listaOficinas.set(index,office);
                        guardar();
                        break;
                    case "4":
                        System.out.println("Nueva dirección: ");
                        String direccion = entrada.nextLine();
                        office.direccion = direccion;
                        listaOficinas.set(index,office);
                        guardar();
                        break;
                    case "5":
                        System.out.println("Nuevo telefono: ");
                        String telefono = entrada.nextLine();
                        office.telefono = telefono;
                        listaOficinas.set(index,office);
                        guardar();
                        break;
                    case "6":
                        System.out.println("Nuevo correo: ");
                        String correo = entrada.nextLine();
                        office.correo = correo;
                        listaOficinas.set(index,office);
                        guardar();
                        break;
                    case "7":
                        System.out.println("Editar Bodega");
                        BodegaCRUD.editar(BodegaCRUD.buscar(office.bodega.nombre));
                        break;
                    case "8":
                        System.out.println("Editar Clientes");
                        System.out.println("1) Agregar cliente");
                        System.out.println("2) Eliminar cliente");
                        System.out.println("3) editar Cliente");
                        System.out.println("Elige la opción deseada (1-3): ");
                        String opcionCliente = entrada.nextLine();
                        switch (opcionCliente){
                            case "1":
                                System.out.println("Agregar cliente existente en el sistema");
                                System.out.println("Nombre del cliente: ");
                                String nombreCliente = entrada.nextLine();
                                System.out.println("Cedula del cliente: ");
                                String cedulaCliente = entrada.nextLine();
                                System.out.println("Departamento del cliente: ");
                                String departamentoCliente = entrada.nextLine();
                                System.out.println("Ciudad del cliente: ");
                                String ciudadCliente = entrada.nextLine();
                                System.out.println("Direccion del cliente: ");
                                String direccionCliente = entrada.nextLine();
                                System.out.println("Telefono del cliente: ");
                                String telefonoCliente = entrada.nextLine();
                                System.out.println("Correo del cliente: ");
                                String correoCliente = entrada.nextLine();
                                Cliente nuevoCliente = new Cliente(nombreCliente,cedulaCliente,departamentoCliente,ciudadCliente,direccionCliente,telefonoCliente,correoCliente);
                                ClienteCRUD.agregar(nuevoCliente);
                                office.listaClientes.add(nuevoCliente);
                                listaOficinas.set(index,office);
                                guardar();
                                break;
                            case "2":
                                System.out.println("Eliminar cliente");
                                for(Cliente cliente: office.listaClientes){
                                    System.out.println(cliente.nombre+" id: "+cliente.cedula);
                                }
                                System.out.println("Seleccione el cliente que desea eliminar(id): ");
                                String cedulaClienteEliminar = entrada.nextLine();
                                office.listaClientes.remove(ClienteCRUD.buscar(cedulaClienteEliminar));
                                break;
                            case "3":
                                System.out.println("Editar cliente");
                                for(Cliente cliente: office.listaClientes){
                                    System.out.println(cliente.nombre+" id: "+cliente.cedula);
                                }
                                System.out.println("Seleccione el cliente que desea editar(id): ");
                                String cedulaClienteEditar = entrada.nextLine();
                                ClienteCRUD.editar(ClienteCRUD.buscar(cedulaClienteEditar));
                                Cliente editado = ClienteCRUD.buscar(cedulaClienteEditar);
                                office.listaClientes.set(index,editado);
                                guardar();
                                break;
                            default:
                                throw new Exception("Opcion no valida");
                        }
                        break;
                    case "9":
                        System.out.println("Editar ventas");
                        System.out.println("1) Agregar venta");
                        System.out.println("2) Eliminar venta");
                        System.out.println("3) editar venta");
                        System.out.println("Elige la opción deseada (1-3): ");
                        String opcionVenta = entrada.nextLine();
                        switch (opcionVenta){
                            case "1":
                                System.out.println("Registrar nueva  venta");
                                System.out.println("Registro de venta: ");
                                String registroVenta = entrada.nextLine();
                                System.out.println("Fecha y hora de la venta: ");
                                Date fechaHora = Date.valueOf(entrada.nextLine());
                                System.out.println("Vendedor: ");
                                for(Vendedor vendedor: office.vendedores){
                                    System.out.println(vendedor.nombre+" id: "+vendedor.nif);
                                }
                                String nifVendedor = entrada.nextLine();
                                Vendedor vendedor=null;
                                for(Vendedor elegido: office.vendedores){
                                    if (elegido.nif.equals(nifVendedor)){
                                        vendedor = elegido;
                                        break;
                                    }
                                }
                                System.out.println("Metodo de pago");
                                MetodoPago metodoPago = new MetodoPago();
                                System.out.println("id: ");
                                int id = Integer.parseInt(entrada.nextLine());
                                System.out.println("Pago de contado: ");
                                double contado = Double.parseDouble(entrada.nextLine());
                                System.out.println("Pago a credito: ");
                                double credito = Double.parseDouble(entrada.nextLine());
                                System.out.println("tipo de pago: ");
                                String tipoDePago = entrada.nextLine();
                                metodoPago.tipoDePago = tipoDePago;
                                metodoPago.credito = credito;
                                metodoPago.contado = contado;
                                metodoPago.id = id;
                                MetodoPagoCRUD.agregar(metodoPago);
                                System.out.println("Descuentos: ");
                                double descuentos = Double.parseDouble(entrada.nextLine());
                                System.out.println("Precio final: ");
                                double precioFinal = Double.parseDouble(entrada.nextLine());
                                System.out.println("Vehiculo: ");
                                for(Vehiculo vehiculo: office.vehiculos){
                                    System.out.println("tipo de vehiculo: "+vehiculo.tipoVehiculo+" numero de bastidor: "+vehiculo.numeroBastidor);
                                }
                                System.out.println("Escriba el numero de bastidor del vehiculo vendido: ");
                                String matricula = entrada.nextLine();
                                Vehiculo vehiculo=null;
                                for(Vehiculo elegido: office.vehiculos){
                                    if (elegido.numeroBastidor.equals(matricula)){
                                        vehiculo = elegido;
                                    }
                                }
                                System.out.println("precio extra: ");
                                double precioExtra = Double.parseDouble(entrada.nextLine());
                                PrecioVenta precioVenta = new PrecioVenta(0,vehiculo.precioBase,precioExtra);
                                String ubicacion = office.departamento+","+office.ciudad+" - "+office.direccion;
                                System.out.println("Fecha de entrega: ");
                                Date fechaEntrega = Date.valueOf(entrada.nextLine());
                                System.out.println("Cliente: ");
                                for(Cliente elegido: office.listaClientes){
                                    System.out.println(elegido.nombre+" id: "+elegido.cedula);
                                }
                                System.out.println("escriba el id del cliente que realizo la compra: ");
                                String cedulaClienteVenta = entrada.nextLine();
                                Cliente cliente = null;
                                cliente = ClienteCRUD.buscar(cedulaClienteVenta);
                                Ventas nuevaVenta = new Ventas(registroVenta,fechaHora,vendedor,precioVenta,metodoPago,descuentos,precioFinal,vehiculo,matricula,ubicacion,fechaEntrega,cliente);
                                office.ventas.add(nuevaVenta);
                                listaOficinas.set(index,office);
                                guardar();
                                break;
                            case "2":
                                System.out.println("Eliminar venta");
                                for(Ventas venta: office.ventas){
                                    System.out.println(venta.registroVenta+ "cliente "+ venta.cliente.nombre+" id: "+venta.cliente.cedula + " fecha: "+venta.fechaHora + " total vendido: $" + venta.precioFinal);
                                }
                                System.out.println("Seleccione la venta que desea eliminar: ");
                                String registroVentaEliminar = entrada.nextLine();
                                for(Ventas venta: office.ventas){
                                    if(venta.registroVenta.equals(registroVentaEliminar)){
                                        office.ventas.remove(venta);
                                        listaOficinas.set(index,office);
                                        guardar();
                                        break;
                                    }
                                }
                                break;
                            case "3":
                                System.out.println("Editar venta");
                                for(Ventas venta: office.ventas){
                                    System.out.println(venta.registroVenta+ "cliente "+ venta.cliente.nombre+" id: "+venta.cliente.cedula + " fecha: "+venta.fechaHora + " total vendido: $" + venta.precioFinal);
                                }
                                System.out.println("Seleccione la venta que desea editar (registroVenta): ");
                                String registroVentaEditar = entrada.nextLine();
                                /*VentasCRUD.editar(VentasCRUD.buscar(registroVentaEditar));*/
                                break;
                        }
                        break;
                    case "10":
                        System.out.println("Editar vehiculos");
                        System.out.println("1) Agregar vehiculo");
                        System.out.println("2) Eliminar vehiculo");
                        System.out.println("que desea hacer?: ");
                        int opcionVehiculo = Integer.parseInt(entrada.nextLine());
                        switch (opcionVehiculo){
                            case 1:
                                System.out.println("el vehiculo a agregar pertenece a la marca?: ");
                                int  count = 1;
                                for(Marca marca : MarcaCRUD.listarTodo()){
                                    System.out.println(count+") "+marca);
                                    count++;
                                }
                                count = 1;
                                System.out.println("Elige el numero de la marca o crea una nueva (escribiendo el nombre): ");
                                String marca = entrada.nextLine();
                                Marca elegida = null;
                                if(marca.matches("\\d+")){
                                    marca = ListaMarcaCRUD.getListaDeMarcas().get(Integer.parseInt(marca)-1);
                                     elegida = MarcaCRUD.listarTodo().get(Integer.parseInt(marca));
                                }else{
                                    ListaMarcaCRUD.agregarMarca(marca);
                                }
                                System.out.println("el vehiculo a agregar pertenece a la carroceria?: ");
                                for (Carroceria carroceria: elegida.Carroceria){
                                    System.out.println(count+") "+carroceria);
                                    count++;
                                }
                                count = 1;
                                System.out.println("Elige el numero de la carroceria : ");
                                Carroceria elegidaCarroceria = elegida.Carroceria.get(Integer.parseInt(entrada.nextLine())-1);
                                System.out.println("elija el modelo del vehiculo: ");
                                for (Modelo mod: elegidaCarroceria.modelos){
                                    System.out.println(count+") "+mod);
                                    count++;
                                }
                                String modelo = entrada.nextLine();
                                Modelo elegidoModelo = elegidaCarroceria.modelos.get(Integer.parseInt(modelo)-1);
                                System.out.println("selecciona el tipo de modelo");
                                System.out.println("1) sencillo");
                                System.out.println("2) premium");
                                String tipoModelo = entrada.nextLine();
                                if(tipoModelo.equals("1")){
                                    tipoModelo = "Sencillo";
                                } else if (tipoModelo.equals("2")) {
                                    tipoModelo = "Premium";
                                }

                                String numeroBastidor = "esperar a crear VehiculoCRUD";
                                System.out.println("elija el precio base del vehiculo: ");
                                double precioBase = Double.parseDouble(entrada.nextLine());
                                System.out.println("elija el precio extra del vehiculo: ");
                                double precioExtra = Double.parseDouble(entrada.nextLine());
                                System.out.println("elija el tipo de vehiculo: ");
                                String tipoVehiculo = entrada.nextLine();


                        }

                        break;
                    case "11":
                        System.out.println("Editar vendedores");
                        System.out.println("1) Agregar vendedor");
                        System.out.println("2) Eliminar vendedor");
                        String opcionVendedor = entrada.nextLine();
                        VendedorCRUD crudVendedor = new VendedorCRUD();

                        
                        if(opcionVendedor.equals("1")){

                            System.out.println("Ingrese los detalles del nuevo vendedor:");
                            System.out.print("Nombre: ");
                            String nombreVendedor = entrada.nextLine();

                            System.out.print("Sexo: ");
                            String sexo = entrada.nextLine();

                            System.out.print("Edad: ");
                            String edad = entrada.nextLine();

                            System.out.print("Departamento: ");
                            String departamentoVendedor = entrada.nextLine();

                            System.out.print("Ciudad: ");
                            String ciudadVendedor = entrada.nextLine();

                            System.out.print("Dirección: ");
                            String direccionVendedor = entrada.nextLine();

                            System.out.print("Teléfono: ");
                            String telefonoVendedor = entrada.nextLine();

                            System.out.print("Correo: ");
                            String correoVendedor = entrada.nextLine();

                            System.out.print("NIF: ");
                            String nif = entrada.nextLine();

                            ArrayList<Ventas> ventas = new ArrayList<>();

                            Vendedor nuevoVendedor = new Vendedor(nombreVendedor,sexo,edad,direccionVendedor,telefonoVendedor,correoVendedor,nif,0,0);
                            crudVendedor.agregar(nuevoVendedor);
                            office.vendedores.add(nuevoVendedor);
                            guardar();
                        }else if(opcionVendedor.equals("2")){
                            for(Vendedor vendedor: office.vendedores){
                                System.out.println(vendedor.nombre+" id: "+vendedor.nif);
                            }
                            System.out.println("Digite el id del vendedor que desea eliminar: ");
                            String idVendedor = entrada.nextLine();
                            crudVendedor.eliminar(crudVendedor.buscar(idVendedor));
                            office.vendedores.remove(crudVendedor.buscar(idVendedor));
                        }else{
                            throw new Exception("Opción invalida");
                        }
                        break;

                }
                entrada.close();
                guardar();
                break;
            }
        }
        throw new Exception("Oficina no existe!");
    }

    public static void eliminar(Oficina oficina) throws Exception{
        cargarOficinas();
        boolean encontrada = false;
        for(Oficina office: listaOficinas){
            if(office.nif.equals(oficina.nif)){
                listaOficinas.remove(office);
                guardar();
                System.out.println("Oficina eliminada exitosamente");
                return;
            }
        }
        if(!encontrada){
            throw new Exception("Oficina con nif: "+oficina.nif+" no existe");
        }
    }

    public static ArrayList<Oficina> listarTodo() throws Exception {
        cargarOficinas();
        return listaOficinas;
    }

    public static Integer contar() throws Exception {
        return listarTodo().size();
    }

    public static void cargarOficinas()throws Exception{
        Gson gson = new Gson();
        FileReader lector = new FileReader("databaseJson/Oficinas.json");
        Type oficinaListType = new TypeToken<ArrayList<Oficina>>(){}.getType();
        listaOficinas = gson.fromJson(lector,oficinaListType);
    }

    public static void guardar(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaOficinas);
        Datos.escribirArchivo("databaseJson/Oficinas.json",json);
    }
}
