package lt.esdc.designpatterns.machine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoodleMachineConnector implements NoodleMachineV17 {

    private static final String PATTERN =
            "(?i)^\\d++g \\d++ml \\d++ml \\d++g(?: [a-z]++)*+$";

    private static final Logger logger = LoggerFactory.getLogger(NoodleMachineConnector.class);

    @Override
    public void send(String order) {
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
}
