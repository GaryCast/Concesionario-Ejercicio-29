package co.edu.udc.poo.concesionario.domain.model;

import co.edu.udc.poo.concesionario.domain.enums.Satisfaccion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClienteTest {



    @Test
    void setReferidoPor_shouldSetReferidoCorrectly_whenValidValueIsProvided() {
        // Arrange
        Cliente cliente = new Cliente(
                "123456",
                "Juan Perez",
                "juan.perez@example.com",
                "123456789",
                "Antioquia",
                "Medellin",
                "Calle 123 #45-67",
                "Email",
                Satisfaccion.excelente
        );

        String referido = "Alejandro";

        // Act
        cliente.setReferidoPor(referido);

        // Assert
        assertEquals(referido, cliente.getReferidoPor());
    }

    @Test
    void setReferidoPor_shouldSetReferenciaToNull_whenNullValueIsProvided() {
        // Arrange
        Cliente cliente = new Cliente(
                "654321",
                "Maria Gonzalez",
                "maria.gonzalez@example.com",
                "987654321",
                "Cundinamarca",
                "Bogota",
                "Carrera 10 #20-30",
                "Telefono",
                Satisfaccion.medio
        );

        cliente.setReferidoPor(null);

        assertNull(cliente.getReferidoPor());
    }

    @Test
    void setReferidoPor_shouldUpdateReferido_whenValueIsChanged() {
        // Arrange
        Cliente cliente = new Cliente(
                "789456",
                "Carlos Lopez",
                "carlos.lopez@example.com",
                "321654987",
                "Valle del Cauca",
                "Cali",
                "Diagonale 12 #34-56",
                "Social Media",
                Satisfaccion.excelente
        );

        String initialReferido = "Luis";
        String updatedReferido = "Sandra";

        cliente.setReferidoPor(initialReferido);
        cliente.setReferidoPor(updatedReferido);

        assertEquals(updatedReferido, cliente.getReferidoPor());
    }
}