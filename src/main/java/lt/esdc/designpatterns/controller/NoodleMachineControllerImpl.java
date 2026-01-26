package lt.esdc.designpatterns.controller;

import lt.esdc.designpatterns.domain.DishType;
import lt.esdc.designpatterns.domain.NoodleRecipe;
import lt.esdc.designpatterns.domain.Region;
import lt.esdc.designpatterns.domain.ToppedNoodleRecipe;
import lt.esdc.designpatterns.domain.Topping;
import lt.esdc.designpatterns.factory.RegionalFactory;
import lt.esdc.designpatterns.factory.RegionalFactoryProvider;
import lt.esdc.designpatterns.machine.NoodleMachineV17;
import lt.esdc.designpatterns.pipeline.OrderContext;
import lt.esdc.designpatterns.pipeline.OrderProcessingChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public final class NoodleMachineControllerImpl implements NoodleMachineController {
    private static final Logger log = LoggerFactory.getLogger(NoodleMachineControllerImpl.class);

    private final NoodleMachineV17 machine;
    private final RegionalFactoryProvider provider;
    private final OrderProcessingChain chain;

    public NoodleMachineControllerImpl(NoodleMachineV17 machine) {
        this.machine = machine;
        this.provider = RegionalFactoryProvider.getInstance();
        this.chain = new OrderProcessingChain();
    }

    @Override
    public void processOrder(String[] order) {
        if (order == null || order.length == 0) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        Region region;
        int startIdx;
        try {
            region = Region.from(order[0]);
            startIdx = 1;
        } catch (IllegalArgumentException ex) {
            region = Region.ITALY;
            startIdx = 0;
            log.info("Region not specified. Defaulting to ITALY.");
        }

        if (order.length - startIdx <= 0) {
            throw new IllegalArgumentException("No dishes specified");
        }

        RegionalFactory factory = provider.get(region);

        for (int i = startIdx; i < order.length; i++) {
            OrderContext ctx = chain.process(order[i]);

            DishType dish = DishType.from(ctx.noodleToken());

            List<Topping> toppings = new ArrayList<>();
            for (String t : ctx.toppingTokens()) {
                toppings.add(Topping.from(t));
            }

            NoodleRecipe base = factory.create(dish);
            String cmd = new ToppedNoodleRecipe(base, toppings).toCommand();

            machine.send(cmd);
        }
    }

    private ParsedItem parseItem(String raw) {
        if (raw == null || raw.isBlank()) {
            throw new IllegalArgumentException("Dish line cannot be empty");
        }

        String[] parts = raw.trim().split("\\s+");
        DishType dish = DishType.from(parts[0]);

        List<Topping> toppings = new ArrayList<>();
        for (int i = 1; i < parts.length; i++) {
            toppings.add(Topping.from(parts[i]));
        }

        return new ParsedItem(dish, toppings);
    }

    private record ParsedItem(DishType dish, List<Topping> toppings) {}
}
