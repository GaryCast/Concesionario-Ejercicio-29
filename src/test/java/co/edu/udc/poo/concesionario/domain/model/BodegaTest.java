package co.edu.udc.poo.concesionario.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BodegaTest {

    @Test
    void GetIdWithDefaultConstructor() {
        // Arrange & Act
        Bodega bodega = new Bodega();

        // Assert
        assertNotNull(bodega.getId(), "El id no puede ser null.");
        assertTrue(bodega.getId() > 0, "el id debe ser un valos positivo");
    }

    @Test
    void GetIdWithParameterizedConstructor() {
        // Arrange & Act
        Bodega bodega = new Bodega("Bodega Norte", "Ubicacion Norte");

        // Assert
        assertNotNull(bodega.getId(), "id no puede ser null.");
        assertTrue(bodega.getId() > 0, "id debe tener un valor positivo");
    }

    @Test
    void GetIdIsUniqueForMultipleInstances() {
        // Arrange
        Bodega bodega1 = new Bodega();
        Bodega bodega2 = new Bodega();
        Bodega bodega3 = new Bodega();

        // Assert
        assertNotEquals(bodega1.getId(), bodega2.getId(), "IDs debe ser unico para diferentes instancias.");
        assertNotEquals(bodega2.getId(), bodega3.getId(), "IDs debe ser unico para diferentes instancias.");
        assertNotEquals(bodega1.getId(), bodega3.getId(), "IDs debe ser unico para diferentes instancias.");
    }

    @Test
    void GetIdAfterSettingIdManually() {
        // Arrange
        Bodega bodega = new Bodega();
        Long newId = 100L;

        // Act
        bodega.setId(newId);

        // Assert
        assertEquals(newId, bodega.getId(), "El id manualmente establecido debe ser correctamente recuperado.");
    }
}