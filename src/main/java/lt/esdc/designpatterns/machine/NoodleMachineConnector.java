package lt.esdc.designpatterns.machine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoodleMachineConnector implements NoodleMachineV17 {

    // Regex for pattern: "<number>g <number>ml <number>ml <number>g"
    private static final String PATTERN = "^\\d+g \\d+ml \\d+ml \\d+g$";

    private static final Logger logger = LoggerFactory.getLogger(NoodleMachineConnector.class);

    @Override
    public void send(String order) {

        if (order == null || order.isBlank()) {
            throw new IllegalArgumentException("Order string cannot be null or empty.");
        }

        if (!order.matches(PATTERN)) {
            throw new IllegalArgumentException(
                    "Invalid order format: " + order +
                            ". Expected format: <noodleMass>g <water>ml <broth>ml <vegetables>g " +
                            "(e.g. '120g 400ml 250ml 50g')."
            );
        }

        logger.info("Preparing noodles: {}", order);
        logger.info("Boiling water...");
        logger.info("Cooking noodles...");
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Machine interrupted while preparing noodles.", e);
        }
        logger.info("Noodles ready! Enjoy your meal!");
    }
}
