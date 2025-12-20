package lt.esdc.designpatterns.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToppingTest {

    @Test
    void should_parse_valid_topping() {
        assertEquals(Topping.SESAME, Topping.from("sesame"));
        assertEquals(Topping.TOFU, Topping.from("tofu"));
        assertEquals(Topping.CHILI, Topping.from("chili"));
    }

    @Test
    void should_be_case_insensitive() {
        assertEquals(Topping.SESAME, Topping.from("SeSaMe"));
    }

    @Test
    void should_throw_exception_for_unknown_topping() {
        assertThrows(IllegalArgumentException.class, () -> Topping.from("cheese"));
    }
}
