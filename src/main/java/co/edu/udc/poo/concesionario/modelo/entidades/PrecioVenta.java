package co.edu.udc.poo.concesionario.modelo.entidades;

public class PrecioVenta {
    public int id;
    public Vehiculo vehiculo;
    public double precioBase;
    public double precioExtras;
    public double descuentosContado = 0.05;
    public double descuentosMixto = 0.025;
    public double precioFinal;
    public String tipoDePago;
    public MetodoPago metodoPago;

    public PrecioVenta() {
        precioBase = 51990000;
        precioExtras = 4900000;
        vehiculo = new Vehiculo();
        if(precioBase == 0 && precioExtras > 0) {
            tipoDePago = "Contado";
            vehiculo.mantenimientos = "incluido";
       
        }
        else{
            vehiculo.mantenimientos = "no incluido";
        }

    }

    public PrecioVenta(int id,double precioBase, double precioExtras) {
        this.id = id;
        this.precioBase = precioBase;
        this.precioExtras = precioExtras;
        if(precioBase == 0 && precioExtras > 0) {
            tipoDePago = "Contado";
            vehiculo.mantenimientos = "incluido";

        }
        else{
            vehiculo.mantenimientos = "no incluido";
        }
        precioFinal = precioFinal();
    }

    public double precioFinal() {
        if(tipoDePago.equals("Credito")){
            return precioBase + precioExtras;
        }
        double contado =  precioBase + precioExtras - ((precioBase + precioExtras) * descuentosContado);
        double mixto = precioBase + precioExtras - ((precioBase + precioExtras) * descuentosMixto);
        return tipoDePago.equals("Contado") ? contado : mixto;
    }
}