package co.edu.udc.poo.concesionario.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehiculoTest {


    @Test
    public void testAntiguedadEnMeses_ValidYear_CurrentYear() {

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.añoFabrica = String.valueOf(LocalDate.now().getYear());
        int antiguedad = vehiculo.antiguedadEnMeses();

        assertEquals(0, antiguedad, "la antiguedadEnMeses debe ser 0 para un vehiculo fabricado este año.");
    }

    @Test
    public void testAntiguedadEnMeses_ValidYear_PreviousYear() {
        // Test when the vehicle is manufactured in the previous year
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.añoFabrica = String.valueOf(LocalDate.now().getYear() - 1);
        int antiguedad = vehiculo.antiguedadEnMeses();

        assertEquals(12, antiguedad, "la antiguedadEnMeses debe ser 12 para un vehiculo fabricado el año anterior.");
    }

    @Test
    public void testAntiguedadEnMeses_ValidYear_MultipleYearsAgo() {
        // Test when the vehicle is manufactured several years ago
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.añoFabrica = String.valueOf(LocalDate.now().getYear() - 5);
        int antiguedad = vehiculo.antiguedadEnMeses();

        assertEquals(60, antiguedad, "la antiguedadEnMeses debe ser correcta para un vehiculo fabricado 5 años atrás.");
    }

    @Test
    public void testAntiguedadEnMeses_InvalidYear_NonNumericString() {
        // Test when the manufacturing year is non-numeric
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.añoFabrica = "invalidYear";
        int antiguedad = vehiculo.antiguedadEnMeses();

        assertEquals(0, antiguedad, "la antiguedadEnMeses debe ser 0 para un vehiculo fabricado con un año no numeric.");
    }

    @Test
    public void testAntiguedadEnMeses_YearInTheFuture() {
        // Test when the vehicle is manufactured in the future
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.añoFabrica = String.valueOf(LocalDate.now().getYear() + 1);
        int antiguedad = vehiculo.antiguedadEnMeses();

        assertEquals(-12, antiguedad, "la antiguedadEnMeses debe calcular meses negativos para vehiculos fabricados en el futuro.");
    }

    @Test
    public void testAntiguedadEnMeses_ValidYear_EdgeCase() {
        // Test for an edge case: vehicle from the year 2000
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.añoFabrica = "2000";
        int antiguedad = vehiculo.antiguedadEnMeses();

        int expected = (LocalDate.now().getYear() - 2000) * 12 + LocalDate.now().getMonthValue() - 1;
        assertEquals(expected, antiguedad, "la antiguedadEnMeses debe calcular correctamente para vehiculos fabricados en el año 2000.");
    }
}