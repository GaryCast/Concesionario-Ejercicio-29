package co.edu.udc.poo.concesionario.modelo.entidades;

import co.edu.udc.poo.concesionario.modelo.crud.MarcaCRUD;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Concesionario {
    public String nombre;
    public String nif;
    public String departamento;
    public String ciudad;
    public String direccion;
    public String telefono;
    public String correo;
    public ArrayList<Marca> marcas = new ArrayList<>();
    public Bodega bodega;
    public int numeroBastidor = 0;
    public List<Cliente> listaClientes;
    public List<Oficina> oficinas ;
    public List<ServicioOficial> serviciosOficiales;
    public List<Ventas> ventas;
    public List<Vendedor> vendedores;
    public List<Vehiculo> vehiculos;

    public Concesionario() {
        nombre= "Mtto de Aires";
        nif= "73_198_988";
        departamento="Bolivar";
        ciudad="Cartagena";
        direccion="Condominio Carioca";
        telefono="3022979717";
        correo="gjcm@hotmail.es";
        listaClientes = new ArrayList<>();
        marcas=new ArrayList<>();
        oficinas = new  ArrayList<>();
        serviciosOficiales = new  ArrayList<>();
        ventas = new ArrayList<>();
        vendedores = new ArrayList<>();
        vehiculos = new ArrayList<>();
        bodega = new Bodega();

    }

    public Concesionario(String nombre, String departamento, String ciudad,
    String direccion, String nif, String telefono, String correo)throws Exception {
        this.nombre = nombre;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.nif = nif;
        this.telefono = telefono;
        this.correo = correo;
        for(Marca m: MarcaCRUD.listarTodo()){
            this.marcas.add(m);
        }
        ventas = new ArrayList<>();
        vehiculos = new ArrayList<>();

    }

    public List<Vehiculo> getVehiculos(){
        List<Vehiculo> VehiculosDisponibles = new ArrayList<>();
        for(Oficina oficina : oficinas){
            VehiculosDisponibles.addAll(oficina.VehiculosDisponiblesInSitu);
        }
        for(ServicioOficial servicioOficial : serviciosOficiales){
            VehiculosDisponibles.addAll(servicioOficial.VehiculosDisponiblesInSitu);
        }
        VehiculosDisponibles.addAll(bodega.VehiculosDisponiblesInSitu);
        return VehiculosDisponibles;

    }

    public List<Vehiculo> getVehiculosNuevos(){
        List<Vehiculo> vehiculosNuevos = new ArrayList<>();
        for(Vehiculo vehiculo: getVehiculos()){
            if(vehiculo.nuevo){
                vehiculosNuevos.add(vehiculo);
            }else{
                continue;
            }
        }
        return vehiculosNuevos;
    }

    public Vehiculo registrarVehiculo(boolean nuevo, String añoFabrica, String cilindraje, String potenciaFiscal, Integer precioBase,List<Caracteristicas> basico,
    String color, String numeroBastidor, String ubicacion, String transmision, String traccion, String combustible, String eficienciaCombustible){
        Vehiculo nuevoVehiculo = new Vehiculo(nuevo, añoFabrica, cilindraje, potenciaFiscal, precioBase, basico,color, numeroBastidor, ubicacion, transmision, traccion, combustible, eficienciaCombustible);
        return nuevoVehiculo;
    }

    public void agregarVehiculo(String nif,String ubicacion, Vehiculo vehiculo){
        vehiculo.numeroBastidor= String.valueOf(numeroBastidor);
        vehiculo.ubicacion=ubicacion;
        switch (ubicacion) {
            case "oficina":
                for(Oficina oficina: oficinas){
                    if(oficina.nif.equals(nif)){
                        oficina.VehiculosDisponiblesInSitu.add(vehiculo);
                    }
                }
                break;
            case "servicioOficial":
                for(ServicioOficial servicioOficial: serviciosOficiales){
                    if(servicioOficial.nif.equals(nif)){
                        servicioOficial.VehiculosDisponiblesInSitu.add(vehiculo);
                    }
                }
            break;
            case "bodega":
                bodega.VehiculosDisponiblesInSitu.add(vehiculo);
                break;
            default:
                System.out.println("Ubicación no reconida!");
                break;
        }
        numeroBastidor++;
    }

    public List<Vehiculo> filtrarVehiculos(String busqueda, String Valor){
        List<Vehiculo> resultado = new ArrayList<>();
        switch (busqueda) {
            case "marca":
                for(Vehiculo vehiculo: getVehiculos()){
                    if(vehiculo.Marca.equals(Valor)){
                        resultado.add(vehiculo);
                    }
                }
                break;
            case "carroceria":
                for(Vehiculo vehiculo: getVehiculos()){
                    if(vehiculo.carroceria.equalsIgnoreCase(Valor)){
                        resultado.add(vehiculo);
                    }
                }
            break;

        }
        return resultado;
    }

    public List<Vehiculo> modelosMarca(String marca){
    List<Vehiculo> resultado = new ArrayList<>();
    for(Vehiculo vehiculo: getVehiculos()){
        if(vehiculo.Marca.equals(marca)){
            System.out.println(vehiculo.Modelo);
            resultado.add(vehiculo);
        }
    }
    return resultado;
    }

    public List<Vehiculo> CarroceriaPorMarca(String marca){
        List<Vehiculo> resultado = new ArrayList<>();
        for(Vehiculo vehiculo: getVehiculos()){
            if(vehiculo.Marca.equals(marca)){
                System.out.println(vehiculo.carroceria);
                resultado.add(vehiculo);
            }
        }
        return resultado;
    }
    public List<Vehiculo> CarroceriaPorModelo(String modelo){
        List<Vehiculo> resultado = new ArrayList<>();
        for(Vehiculo vehiculo: getVehiculos()){
            if(vehiculo.Modelo.equals(modelo)){
                System.out.println(vehiculo.carroceria);
                resultado.add(vehiculo);
            }
        }
        return resultado;
    }



    public double calcularVentaTotalVendedor(String nif){
        double total = 0;
        for(Ventas venta: ventas){
            if(venta.vendedor.nif.equals(nif)){
                total += venta.precioVenta.precioFinal();
            }
        }
        return total;
    }

    public List<Ventas> ventasPorVendedor(String nif){
        List<Ventas> resultado = new ArrayList<>();
        for(Ventas venta: ventas){
            if(venta.vendedor.nif.equals(nif)){
                resultado.add(venta);
            }
        }
        return resultado;  
    }

    public double calcularPromedioVendedor(String nif){
        double total = 0;
        int contador = 0;
        for(Ventas venta: ventas){
            if(venta.vendedor.nif.equals(nif)){
                total += venta.precioVenta.precioFinal();
                contador++;
            }
        }
        return total/contador;
    }

    public double ventaMayorVendedor(String nif){
        double mayor = 0;
        for(Ventas venta: ventas){
            if(venta.vendedor.nif.equals(nif)){
                if(venta.precioVenta.precioFinal() > mayor){
                    mayor = venta.precioVenta.precioFinal();
                }
            }
        }
        return mayor;
    }

    public double ventasPorMarca(String marca){
        double total = 0;
        for(Ventas venta: ventas){
            if(venta.vehiculo.Marca.equals(marca)){
                total += venta.precioVenta.precioFinal();
            }
        }
        return total;
    }

    public double ventasPorCarroceria(String carroceria){
        double total = 0;
        for(Ventas venta: ventas){
            if(venta.vehiculo.carroceria.equals(carroceria)){
                total += venta.precioVenta.precioFinal();
            }
        }
        return total;
    }

    public double ventasPorModelo(String modelo){
        double total = 0;
        for(Ventas venta: ventas){
            if(venta.vehiculo.Modelo.equals(modelo)){
                total += venta.precioVenta.precioFinal();
            }
        }
        return total;
    }

    public String modeloMasVendido(){
        String modeloMasVendido = "";
        double mayor = 0;
        for(Vehiculo vehiculo: getVehiculos()){
            double total = 0;
            for(Ventas venta: ventas){
                if(venta.vehiculo.Modelo.equals(vehiculo.Modelo)){
                    total += venta.precioVenta.precioFinal();
                }
            }
            if(total > mayor){
                mayor = total;
                modeloMasVendido = vehiculo.Modelo;
            }
        }
        return modeloMasVendido;
    }


    public ArrayList<Ventas> ventasPorCiudad(String ciudad){
        ArrayList<Ventas> resultado = new ArrayList<>();
        for(Ventas venta: ventas){
            if(venta.vendedor.ciudad.equals(ciudad)){
                resultado.add(venta);
            }
        }
        return resultado;
    }

    public double ventasPorCiudadTotal(String ciudad){
        double total = 0;
        for(Ventas venta: ventas){
            if(venta.vendedor.ciudad.equals(ciudad)){
                total += venta.precioVenta.precioFinal();
            }
        }
        return total;
    }
    public double ventasPorDepartamentoTotal(String departamento){
        double total = 0;
        for(Ventas venta: ventas){
            if(venta.vendedor.departamento.equals(departamento)){
                total += venta.precioVenta.precioFinal();
            }
        }
        return total;
    }
    public List<Ventas> ventasPorDepartamento(String departamento){
        List<Ventas> resultado = new ArrayList<>();
        for(Ventas venta: ventas){
            if(venta.vendedor.departamento.equals(departamento)){
                resultado.add(venta);
            }
        }
        return resultado;
    }

    public double ventasPorOficina(String nif){
        double total = 0;
        for(Ventas venta: ventas){
            if(venta.vendedor.nif.equals(nif)){
                total += venta.precioVenta.precioFinal();
            }
        }
        return total;
    }

    public double ventasPorServicioOficial(String nif){
        double total = 0;
        for(Ventas venta: ventas){
            if(venta.vendedor.nif.equals(nif)){
                total += venta.precioVenta.precioFinal();
            }
        }
        return total;
    }

    public double ventasPorMetodoPago(String metodoPago){
        double total = 0;
        for(Ventas venta: ventas){
            switch(metodoPago){
                case "contado":
                    System.out.println();
                    if(venta.precioVenta.metodoPago.contado > 0){
                        total += venta.precioVenta.precioFinal();
                    }
                    break;
                case "credito":
                    if(venta.precioVenta.metodoPago.credito > 0){
                        total += venta.precioVenta.precioFinal();
                    }
                    break;
                case "mixto":
                    if(venta.precioVenta.metodoPago.contado > 0 && venta.precioVenta.metodoPago.credito > 0){
                        total += venta.precioVenta.precioFinal();
                    }
                    break;
            }
        }
        return total;
    }

    public double ventasPorAñoFabricacion(String anio){
        double total = 0;
        for(Ventas venta: ventas){
            if(venta.vehiculo.añoFabrica.equals(anio)){
                total += venta.precioVenta.precioFinal();
            }
        }
        return total;
    }

    public List<Vehiculo> vehiculosConMantenimiento(){
        List<Vehiculo> vehiculosConMantenimiento = new ArrayList<>();
        for(Vehiculo vehiculo: getVehiculos()){
            if(vehiculo.mantenimientos.equals("incluido")){
                vehiculosConMantenimiento.add(vehiculo);
            }
        }
        return vehiculosConMantenimiento;
    }
    public List<Vehiculo> vehiculosSinMantenimiento(){
        List<Vehiculo> vehiculosSinMantenimiento = new ArrayList<>();
        for(Vehiculo vehiculo: getVehiculos()){
            if(vehiculo.mantenimientos.equals("no incluido")){
                vehiculosSinMantenimiento.add(vehiculo);
            }
        }
        return vehiculosSinMantenimiento;
    }

    public List<MetodoPago> metodosDePagoPorCliente(String cedula){
        List<MetodoPago> metodosDePago = new ArrayList<>();
        for(Ventas venta: ventas){
            if(venta.cliente.cedula.equals(cedula)){
                metodosDePago.add(venta.metodoPago);
            }
        }
        return metodosDePago;
    }

    public List<Cliente> clientesPorVendedor(String nif){
        List<Cliente> clientes = new ArrayList<>();
        for(Ventas venta: ventas){
            if(venta.vendedor.nif.equals(nif)){
                clientes.add(venta.cliente);
            }
        }
        return clientes;
    }

    public List<Cliente> clientesPorCiudad(String ciudad){
        List<Cliente> clientes = new ArrayList<>();
        for(Ventas venta: ventas){
            if(venta.vendedor.ciudad.equals(ciudad)){
                clientes.add(venta.cliente);
            }
        }
        return clientes;
    }
    public List<Cliente> clientesPorDepartamento(String departamento){
        List<Cliente> clientes = new ArrayList<>();
        for(Ventas venta: ventas){
            if(venta.vendedor.departamento.equals(departamento)){
                clientes.add(venta.cliente);
            }
        }
        return clientes;
    }
    public List<Cliente> clientesPorOficina(String nif){
        List<Cliente> clientes = new ArrayList<>();
        for(Ventas venta: ventas){
            if(venta.vendedor.nif.equals(nif)){
                clientes.add(venta.cliente);
            }
        }
        return clientes;
    }   

    public List<Integer> satisfacciónPorCliente(){
        List<Integer> satisfaccion = new ArrayList<>();
        for(Cliente cliente: listaClientes){
            if(cliente.nivelSatisfaccion >= 0){
                satisfaccion.add(cliente.nivelSatisfaccion);
            }
        }
        return satisfaccion;
    }

    public List<Cliente> clientesReferidos(){
        List<Cliente> clientes = new ArrayList<>();
        for(Cliente cliente: clientes){
            if(cliente.referidoPor != null){
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public Cliente clienteConMasCompras(){
        Cliente clienteConMasCompras = null;
        int mayor = 0;
        for(Cliente cliente: listaClientes){
            if(cliente.vehiculosComprados.size() > mayor){
                mayor = cliente.vehiculosComprados.size();
                clienteConMasCompras = cliente;
            }
        }
        return clienteConMasCompras;
    }

    public double promedioDeCompraPorCliente(String nif){
        double total = 0;
        int contador = 0;
        for(Ventas venta: ventas){
            if(venta.cliente.cedula.equals(nif)){
                total += venta.precioVenta.precioFinal();
                contador++;
            }
        }
        return total/contador;
    }

    public List<ServicioOficial> serviciosOficiales(){
        return serviciosOficiales;
    }

    public List<Vehiculo> vehiculosVendidosPorFecha(Date fechaI, Date fechaF){
        List<Vehiculo> vehiculos = new ArrayList<>();
        for(Ventas venta: ventas){
            if(venta.fechaEntrega.equals(fechaI) && venta.fechaEntrega.equals(fechaF)){
                vehiculos.add(venta.vehiculo);
            }
        }
        return vehiculos;
    }

    public List<String> coloresMasVendidos(){
        List<String> colores = new ArrayList<>();
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;
        int c4 = 0;
        int c5 = 0;
        for(Ventas venta: ventas){
            if(venta.vehiculo.color.equals("negro")){
                c1++;
            }else if(venta.vehiculo.color.equals("rojo")){
                c2++;
            }else if(venta.vehiculo.color.equals("azul")){
                c3++;
            }else if(venta.vehiculo.color.equals("verde")){
                c4++;
            }else if(venta.vehiculo.color.equals("amarillo")){
                c5++;
            }
        }
        colores.add("Negro: "+ c1);
        colores.add("Rojo: "+ c2);
        colores.add("Azul: "+ c3);
        colores.add("Verde: "+ c4);
        colores.add("Amarillo: "+ c5);
        return colores;
    }

    public List<Vendedor> vendedores(){
        return vendedores;
    }

    public Vendedor vendedorConMasClientes(){
        int clientes = 0;
        Vendedor vendedorConMasClientes = null;
        for(Vendedor vendedor: vendedores){
            if(vendedor.ventas.size() > clientes){
                clientes = vendedor.ventas.size();
                vendedorConMasClientes = vendedor;
            }
        }
        return vendedorConMasClientes;
    }

    public List<Vendedor> vendedoresPorCiudad(String ciudad){
        List<Vendedor> vendedores = new ArrayList<>();
        for(Vendedor vendedor: vendedores){
            if(vendedor.ciudad.equals(ciudad)){
                vendedores.add(vendedor);
            }
        }
        return vendedores;
    }

    public List<Vendedor> vendedoresPorDepartamento(String departamento){
        List<Vendedor> vendedores = new ArrayList<>();
        for(Vendedor vendedor: vendedores){
            if(vendedor.departamento.equals(departamento)){
                vendedores.add(vendedor);
            }
        }
        return vendedores;
    }


    public List<Concesionario> concesionariosPorMarca(String marca){
        List<Concesionario> concesionarios = new ArrayList<>();
        for(Concesionario concesionario: concesionarios){
            if(concesionario.marcas.equals(marca)){
                concesionarios.add(concesionario);
            }
        }
        return concesionarios;
    }

    public List<Ventas> ventasSuperiorA(double precio){
        List<Ventas> ventas = new ArrayList<>();
        for(Ventas venta: ventas){
            if(venta.precioVenta.precioFinal() >= precio){
                ventas.add(venta);
            }
        }
        return ventas;
    }

    public double porcentajeDeSatisfaccionClientes(){
        double satisfaccion = 0;
        int contador = 0;
        for(Cliente cliente: listaClientes){
            if(cliente.nivelSatisfaccion > 0){
                satisfaccion += cliente.nivelSatisfaccion;
                contador++;
            }
        }
        return satisfaccion/contador;
    }

    public ArrayList<Integer> extrasMasVendidos() {
        int contador = 0;
        int extra1 = 0;
        int extra2 = 0;
        int extra3 = 0;
        int extra4 = 0;
        int extra5 = 0;
        for (Ventas venta : ventas) {
            if (venta.extras.size() == 1) {
                extra1++;
            } else if (venta.extras.size() == 2) {
                extra2++;
            } else if (venta.extras.size() == 3) {
                extra3++;
            } else if (venta.extras.size() == 4) {
                extra4++;
            } else if (venta.extras.size() == 5) {
                extra5++;
            }
        }
        ArrayList<Integer> extras = new ArrayList<>();
        extras.add(extra1);
        extras.add(extra2);
        extras.add(extra3);
        extras.add(extra4);
        extras.add(extra5);
        return extras;
    }

    public Concesionario concesionarioConMasVentas(){
        Concesionario concesionarioConMasVentas = null;
        double mayor = 0;
        for(Concesionario concesionario: oficinas){
            if(concesionario.ventas.size() > mayor){
                mayor = concesionario.ventas.size();
                concesionarioConMasVentas = concesionario;
            }
        }
        return concesionarioConMasVentas.concesionarioConMasVentas();
    }

    public List<Concesionario> concesionariosPorCiudad(String ciudad){
        List<Concesionario> concesionarios = new ArrayList<>();
        for(Concesionario concesionario: concesionarios){
            if(concesionario.ciudad.equals(ciudad)){
                concesionarios.add(concesionario);
            }
        }
        return concesionarios;
    }

    public List<Concesionario> concesionariosPorDepartamento(String departamento){
        List<Concesionario> concesionarios = new ArrayList<>();
        for(Concesionario concesionario: concesionarios){
            if(concesionario.departamento.equals(departamento)){
                concesionarios.add(concesionario);
            }
        }
        return concesionarios;
    }

    public List<ServicioOficial> serviciosOficialesPorCiudad(String ciudad){
        List<ServicioOficial> serviciosOficiales = new ArrayList<>();
        for(ServicioOficial servicioOficial: serviciosOficiales){
            if(servicioOficial.ciudad.equals(ciudad)){
                serviciosOficiales.add(servicioOficial);
            }
        }
        return serviciosOficiales;
    }

    public List<ServicioOficial> serviciosOficialesPorDepartamento(String departamento){
        List<ServicioOficial> serviciosOficiales = new ArrayList<>();
        for(ServicioOficial servicioOficial: serviciosOficiales){
            if(servicioOficial.departamento.equals(departamento)){
                serviciosOficiales.add(servicioOficial);
            }
        }
        return serviciosOficiales;
    }   

}