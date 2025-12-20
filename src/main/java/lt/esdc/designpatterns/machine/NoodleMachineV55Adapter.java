package lt.esdc.designpatterns.machine;

import java.util.Objects;

public final class NoodleMachineV55Adapter implements NoodleMachineV17 {

    private final NoodleMachineV55 machine;

    public NoodleMachineV55Adapter(NoodleMachineV55 machine) {
        this.machine = Objects.requireNonNull(machine, "machine");
    }

    @Override
    public void send(String order) {
        Objects.requireNonNull(order, "order");

        String token = machine.getToken();
        String session = machine.openSession(token);
        try {
            machine.makeNoodle(token, session, order);
        } finally {
            machine.closeSession(token, session);
        }
    }
}
