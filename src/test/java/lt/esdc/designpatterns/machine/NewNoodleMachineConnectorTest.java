package lt.esdc.designpatterns.machine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewNoodleMachineConnectorTest {

    @Test
    void should_run_full_lifecycle_without_sleep() {
        NewNoodleMachineConnector.Sleeper noSleep = ms -> {};
        NewNoodleMachineConnector connector = new NewNoodleMachineConnector(noSleep, 0L, 0L);

        String token = connector.getToken();
        String session = connector.openSession(token);

        assertDoesNotThrow(() ->
                connector.makeNoodle(token, session, "120g 400ml 250ml 50g sesame tofu")
        );

        assertDoesNotThrow(() -> connector.closeSession(token, session));
    }

    @Test
    void should_reject_invalid_order_format() {
        NewNoodleMachineConnector.Sleeper noSleep = ms -> {};
        NewNoodleMachineConnector connector = new NewNoodleMachineConnector(noSleep, 0L, 0L);

        String token = connector.getToken();
        String session = connector.openSession(token);

        assertThrows(IllegalArgumentException.class,
                () -> connector.makeNoodle(token, session, "ramen sesame"));
    }
}
