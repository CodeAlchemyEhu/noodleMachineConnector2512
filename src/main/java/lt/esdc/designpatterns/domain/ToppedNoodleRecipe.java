package lt.esdc.designpatterns.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public final class ToppedNoodleRecipe {

    private final NoodleRecipe base;
    private final List<Topping> toppings;

    public ToppedNoodleRecipe(NoodleRecipe base, List<Topping> toppings) {
        this.base = Objects.requireNonNull(base, "base");
        Objects.requireNonNull(toppings, "toppings");

        for (Topping t : toppings) {
            Objects.requireNonNull(t, "topping");
        }

        this.toppings = new ArrayList<>(toppings);
    }

    public String toCommand() {
        String baseCommand = base.toCommand();
        if (toppings.isEmpty()) {
            return baseCommand;
        }

        StringBuilder sb = new StringBuilder(baseCommand);
        for (Topping topping : toppings) {
            sb.append(' ')
                    .append(topping.name().toLowerCase(Locale.ROOT));
        }
        return sb.toString();
    }
}
