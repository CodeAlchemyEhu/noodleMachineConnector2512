package lt.esdc.designpatterns.machine;

import java.util.UUID;

public class NewNoodleMachineConnector implements NoodleMachineV55 {
    private static final String ORDER_PATTERN =
            "(?i)^\\d+g \\d+ml \\d+ml \\d+g(?: [a-z]+)*$";

    private String currentToken;
    private String currentSession;

    @Override
    public String getToken() {
        System.out.println("Retrieving token...");

        currentToken = UUID.randomUUID().toString().replace("-", "");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Unexpected interruption during coffee preparation", e);
        }

        return currentToken;
    }

    @Override
    public String openSession(String token) {
        validateToken(token);

        System.out.println("Opening session...");

        currentSession = UUID.randomUUID().toString().replace("-", "");
        return currentSession;
    }

    @Override
    public void makeNoodle(String token, String session, String coffee) {
        validateToken(token);
        validateSession(session);
        validateOrder(coffee);

        System.out.println("Preparing noodle... " + coffee);
        System.out.println("..........");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Unexpected interruption during noodle preparation", e);
        }
        System.out.println("Ready");
    }

    @Override
    public void closeSession(String token, String session) {
        validateToken(token);
        validateSession(session);

        System.out.println("Closing session...");
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
                            "'. Expected format: Expected format: <noodleMass>g <water>ml <broth>ml <vegetables>g [toppings...]"
            );
        }
    }
}
