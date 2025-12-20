package lt.esdc.designpatterns.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToppedNoodleRecipeTest {

    @Test
    void should_return_base_command_when_no_toppings() {
        NoodleRecipe base = new NoodleRecipe.Builder()
                .noodle(120)
                .water(400)
                .broth(250)
                .vegetables(50)
                .build();

        ToppedNoodleRecipe recipe = new ToppedNoodleRecipe(base, List.of());

        assertEquals(base.toCommand(), recipe.toCommand());
    }

    @Test
    void should_append_toppings_to_base_command() {
        NoodleRecipe base = new NoodleRecipe.Builder()
                .noodle(120)
                .water(400)
                .broth(250)
                .vegetables(50)
                .build();

        ToppedNoodleRecipe recipe = new ToppedNoodleRecipe(base, List.of(Topping.SESAME, Topping.TOFU));

        assertEquals("120g 400ml 250ml 50g sesame tofu", recipe.toCommand());
    }
}
