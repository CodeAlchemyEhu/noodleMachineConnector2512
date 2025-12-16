package lt.esdc.designpatterns.domain;

import java.util.List;

public final class ToppedNoodleRecipe {
    private final NoodleRecipe base;
    private final List<Topping> toppings;

    public ToppedNoodleRecipe(NoodleRecipe base, List<Topping> toppings) {
        this.base = base;
        this.toppings = toppings;
    }

    public String toCommand() {
        String cmd = base.toCommand();
        if (toppings == null || toppings.isEmpty()) return cmd;

        StringBuilder sb = new StringBuilder(cmd);
        for (Topping t : toppings) {
            sb.append(' ').append(t.token());
        }
        return sb.toString();
    }
}
