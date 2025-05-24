package co.edu.udc.poo.concesionario.modelo.entidades;

public class MetodoPago {
    public int id;
    public Double contado;
    public Double credito;
    public String tipoDePago;

    public MetodoPago() {
        id = 1;
        contado = 15000000.00;
        credito = 45000000.00;

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