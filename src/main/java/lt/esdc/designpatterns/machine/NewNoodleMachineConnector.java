package lt.esdc.designpatterns.machine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.UUID;

public class NewNoodleMachineConnector implements NoodleMachineV55 {

    private static final Logger log = LoggerFactory.getLogger(NewNoodleMachineConnector.class);

    private static final String ORDER_PATTERN =
            "(?i)^\\d++g \\d++ml \\d++ml \\d++g(?: [a-z]++)*+$";

    private final Sleeper sleeper;
    private final long tokenDelayMs;
    private final long noodleDelayMs;

    private String currentToken;
    private String currentSession;

    public NewNoodleMachineConnector() {
        this(new ThreadSleeper(), 5000L, 2000L);
    }

    public NewNoodleMachineConnector(Sleeper sleeper, long tokenDelayMs, long noodleDelayMs) {
        this.sleeper = Objects.requireNonNull(sleeper, "sleeper");
        this.tokenDelayMs = tokenDelayMs;
        this.noodleDelayMs = noodleDelayMs;
    }

    @Override
    public String getToken() {
        log.info("Retrieving token...");

        currentToken = UUID.randomUUID().toString().replace("-", "");
        sleep(tokenDelayMs);

        return currentToken;
    }

    @Override
    public String openSession(String token) {
        validateToken(token);

        log.info("Opening session...");

        currentSession = UUID.randomUUID().toString().replace("-", "");
        return currentSession;
    }

    @Override
    public void makeNoodle(String token, String session, String noodle) {
        validateToken(token);
        validateSession(session);
        validateOrder(noodle);

        log.info("Preparing noodle... {}", noodle);
        sleep(noodleDelayMs);
        log.info("Ready");
    }

    @Override
    public void closeSession(String token, String session) {
        validateToken(token);
        validateSession(session);

        log.info("Closing session...");
        currentSession = null;
    }

    private void validateToken(String token) {
        if (token == null || !token.equals(currentToken)) {
            throw new IllegalArgumentException("Invalid or expired token.");
        }
    }

    private void validateSession(String session) {
        if (currentSession == null) {
            throw new IllegalStateException("No active session.");
        }
        if (!session.equals(currentSession)) {
            throw new IllegalArgumentException("Invalid session.");
        }
    }

    private void validateOrder(String orderString) {
        if (orderString == null || orderString.isBlank()) {
            throw new IllegalArgumentException("Order string cannot be null or empty.");
        }

        if (!orderString.matches(ORDER_PATTERN)) {
            throw new IllegalArgumentException(
                    "Invalid order format: '" + orderString +
                            "'. Expected format: <noodleMass>g <water>ml <broth>ml <vegetables>g [toppings...]"
            );
        }
    }

    private void sleep(long ms) {
        try {
            sleeper.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted during connector operation", e);
        }
    }

    public interface Sleeper {
        void sleep(long ms) throws InterruptedException;
    }

    private static final class ThreadSleeper implements Sleeper {
        @Override
        public void sleep(long ms) throws InterruptedException {
            Thread.sleep(ms);
        }
    }
}
