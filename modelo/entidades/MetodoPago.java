package co.edu.udc.poo.concesionario.modelo.entidades;

public class MetodoPago {
    public double contado;
    public double credito;
    public String tipoDePago;

    public MetodoPago() {
        contado = 15000000;
        credito = 45000000;

        if(credito == 0 && contado > 0 ){
            tipoDePago = "Contado";
        }else if (contado == 0 && credito > 0){
            tipoDePago = "Credito";
        }else if (contado > 0 && credito > 0){
            tipoDePago = "Mixto";
        }
    }

    public MetodoPago(double contado, double credito) {
        this.contado = contado;
        this.credito = credito;
        if(credito == 0 && contado > 0 ){
            tipoDePago = "Contado";
        }else if (contado == 0 && credito > 0){
            tipoDePago = "Credito";
        }else if (contado > 0 && credito > 0){
            tipoDePago = "Mixto";
        }
    }
}