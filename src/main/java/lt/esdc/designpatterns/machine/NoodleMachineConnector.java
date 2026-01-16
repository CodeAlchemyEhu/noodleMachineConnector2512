package lt.esdc.designpatterns.machine;

import lt.esdc.designpatterns.machine.state.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoodleMachineConnector implements NoodleMachineV17, StateSwitcher {

    private static final String PATTERN =
            "(?i)^\\d++g \\d++ml \\d++ml \\d++g(?: [a-z]++)*+$";

    private static final Logger logger = LoggerFactory.getLogger(NoodleMachineConnector.class);

    private ConnectorState state = new OpenState(this);

    public String stateName() {
        return state.name();
    }

    @Override
    public void send(String order) {
        logger.info("Connector state: {}", state.name());

        try {
            state.call(() -> {
                sendToRealMachine(order);
                return null;
            });
            state.onSuccess();
        } catch (IgnoredCallException ignored) {
            return;
        } catch (Exception ex) {
            state.onFailure(ex);
            if (ex instanceof RuntimeException re) throw re;
            throw new RuntimeException(ex);
        }
    }

    private void sendToRealMachine(String order) {
        logger.info("Sending order to NoodleMachineV17: {}", order);

        if (order == null || order.isBlank()) {
            throw new IllegalArgumentException("Order string cannot be null or empty.");
        }

        if (!order.matches(PATTERN)) {
            throw new IllegalArgumentException(
                    "Invalid order format: " + order +
                            ". Expected format: <noodleMass>g <water>ml <broth>ml <vegetables>g [toppings...] " +
                            "(e.g. '120g 400ml 250ml 50g sesame tofu')."
            );
        }

        logger.info("Order accepted: {}", order);
    }

    @Override
    public void toOpen() {
        state = new OpenState(this);
    }

    @Override
    public void toClosed() {
        state = new ClosedState(this);
    }

    @Override
    public void toSemiClosed() {
        state = new SemiClosedState(this);
    }
}
