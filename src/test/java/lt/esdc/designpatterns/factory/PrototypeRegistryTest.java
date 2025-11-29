package lt.esdc.designpatterns.factory;

import lt.esdc.designpatterns.domain.DishType;
import lt.esdc.designpatterns.domain.NoodleRecipe;
import lt.esdc.designpatterns.domain.Region;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrototypeRegistryTest {

    @Test
    void shouldReturnCopyOfRecipe() {
        PrototypeRegistry registry = PrototypeRegistry.getInstance();

        NoodleRecipe r1 = registry.prototypeOf(Region.ITALY, DishType.RAMEN);
        NoodleRecipe r2 = registry.prototypeOf(Region.ITALY, DishType.RAMEN);

        // They must NOT be same object
        assertNotSame(r1, r2);

        // But their content must match
        assertEquals(r1.toCommand(), r2.toCommand());
    }

    @Test
    void shouldReturnCorrectItalyRecipe() {
        PrototypeRegistry registry = PrototypeRegistry.getInstance();

        NoodleRecipe ramen = registry.prototypeOf(Region.ITALY, DishType.RAMEN);

        assertEquals("100g 250ml 150ml 40g", ramen.toCommand());
    }

    @Test
    void shouldReturnCorrectIndiaRecipe() {
        PrototypeRegistry registry = PrototypeRegistry.getInstance();

        NoodleRecipe ramen = registry.prototypeOf(Region.INDIA, DishType.RAMEN);

        assertEquals("120g 300ml 150ml 70g", ramen.toCommand());
    }

    @Test
    void shouldThrowWhenRegionUnknown() {
        PrototypeRegistry registry = PrototypeRegistry.getInstance();

        assertThrows(IllegalArgumentException.class, () ->
                registry.prototypeOf(null, DishType.RAMEN)
        );
    }

    @Test
    void shouldThrowWhenDishUnknown() {
        PrototypeRegistry registry = PrototypeRegistry.getInstance();

        assertThrows(IllegalArgumentException.class, () ->
                registry.prototypeOf(Region.ITALY, null)
        );
    }
}
