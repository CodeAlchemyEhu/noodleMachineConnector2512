package lt.esdc.designpatterns.controller;

import lt.esdc.designpatterns.domain.DishType;
import lt.esdc.designpatterns.domain.Region;
import lt.esdc.designpatterns.factory.RegionalFactory;
import lt.esdc.designpatterns.factory.RegionalFactoryProvider;
import lt.esdc.designpatterns.machine.NoodleMachineV17;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NoodleMachineControllerImpl implements NoodleMachineController {
    private static final Logger log = LoggerFactory.getLogger(NoodleMachineControllerImpl.class);

    private final NoodleMachineV17 machine;
    private final RegionalFactoryProvider provider;

    public NoodleMachineControllerImpl(NoodleMachineV17 machine) {
        this.machine = machine;
        this.provider = RegionalFactoryProvider.getInstance(); // Singleton
    }

    @Override
    public void processOrder(String[] order) {
        if (order == null || order.length == 0)
            throw new IllegalArgumentException("Order must contain at least one item");

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

        if (order.length - startIdx <= 0)
            throw new IllegalArgumentException("No dishes specified");

        RegionalFactory factory = provider.get(region);

        for (int i = startIdx; i < order.length; i++) {
            DishType dish = DishType.from(order[i]);
            String cmd = factory.create(dish).toCommand();
            machine.send(cmd);
        }
    }
}
