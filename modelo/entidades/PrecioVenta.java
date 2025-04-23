package co.edu.udc.poo.concesionario.modelo.entidades;

public class PrecioVenta {
    public Vehiculo vehiculo;
    public double precioBase;
    public double precioExtras;
    public double descuentosContado = 0.05;
    public double descuentosMixto = 0.025;
    public double precioFinal;
    public String tipoDePago;

    public PrecioVenta() {
        precioBase = 51990000;
        precioExtras = 4900000;
        if(precioBase == 0 && precioExtras > 0) {
            tipoDePago = "Contado";
            vehiculo.mantenimientos = "incluido";
       
        }
        else{
            vehiculo.mantenimientos = "no incluido";
        }

    }

    public PrecioVenta(double precioBase, double precioExtras) {
        this.precioBase = precioBase;
        this.precioExtras = precioExtras;
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