package lt.esdc.designpatterns.facade;

import lt.esdc.designpatterns.controller.NoodleMachineController;
import lt.esdc.designpatterns.controller.NoodleMachineControllerImpl;
import lt.esdc.designpatterns.machine.*;

import java.util.Objects;

public final class NoodleOrderFacade {

    private final NoodleMachineController controller;

    private NoodleOrderFacade(NoodleMachineController controller) {
        this.controller = controller;
    }

    public static NoodleOrderFacade withMachine(NoodleMachineV17 machine) {
        Objects.requireNonNull(machine, "machine");
        return new NoodleOrderFacade(new NoodleMachineControllerImpl(machine));
    }

    public static NoodleOrderFacade withLegacyMachine() {
        return withMachine(new NoodleMachineConnector());
    }

    public static NoodleOrderFacade withNewMachine() {
        return withMachine(
                new NoodleMachineV55Adapter(
                        new TokenCachingProxy(new NewNoodleMachineConnector())
                )
        );
    }

    public void order(String... items) {
        Objects.requireNonNull(items, "items");
        controller.processOrder(items);
    }
}
