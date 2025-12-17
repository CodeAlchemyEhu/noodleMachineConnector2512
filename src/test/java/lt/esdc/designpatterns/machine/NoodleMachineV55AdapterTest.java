package lt.esdc.designpatterns.machine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoodleMachineV55AdapterTest {

    @Test
    void should_delegate_call_to_v55_machine() {
        FakeV55Machine machine = new FakeV55Machine();
        NoodleMachineV55Adapter adapter = new NoodleMachineV55Adapter(machine);

        adapter.send("120g 400ml 250ml 50g sesame");

        assertTrue(machine.makeCalled);
        assertEquals("120g 400ml 250ml 50g sesame", machine.lastOrder);
    }

    @Test
    void should_close_session_even_if_make_fails() {
        FakeV55Machine machine = new FakeV55Machine(true);
        NoodleMachineV55Adapter adapter = new NoodleMachineV55Adapter(machine);

        assertThrows(RuntimeException.class,
                () -> adapter.send("120g 400ml 250ml 50g"));

        assertTrue(machine.sessionClosed);
    }

    private static final class FakeV55Machine implements NoodleMachineV55 {

        boolean makeCalled;
        boolean sessionClosed;
        boolean failOnMake;
        String lastOrder;

        FakeV55Machine() {
            this(false);
        }

        FakeV55Machine(boolean failOnMake) {
            this.failOnMake = failOnMake;
        }

        @Override
        public String getToken() {
            return "token";
        }

        @Override
        public String openSession(String token) {
            return "session";
        }

        @Override
        public void makeNoodle(String token, String session, String noodle) {
            makeCalled = true;
            lastOrder = noodle;
            if (failOnMake) {
                throw new RuntimeException("boom");
            }
        }

        @Override
        public void closeSession(String token, String session) {
            sessionClosed = true;
        }
    }
}
