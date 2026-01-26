package lt.esdc.designpatterns.machine.state;

import java.util.concurrent.Callable;

public final class OpenState implements ConnectorState {
    private final StateSwitcher switcher;
    private int failures;

    public OpenState(StateSwitcher switcher) {
        this.switcher = switcher;
    }

    @Override
    public <T> T call(Callable<T> op) throws Exception {
        return op.call();
    }

    @Override
    public void onSuccess() {
    }

    @Override
    public void onFailure(Exception ex) {
        failures++;
        if (failures >= 2) {
            switcher.toClosed();
        }
    }

    @Override
    public String name() {
        return "OPEN";
    }
}
