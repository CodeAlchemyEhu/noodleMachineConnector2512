package lt.esdc.designpatterns.machine.state;

import lt.esdc.designpatterns.machine.state.IgnoredCallException;

import java.util.concurrent.Callable;

public final class ClosedState implements ConnectorState {
    private final StateSwitcher switcher;
    private int ignoredLeft = 5;

    public ClosedState(StateSwitcher switcher) {
        this.switcher = switcher;
    }

    @Override
    public <T> T call(Callable<T> op) {
        ignoredLeft--;
        if (ignoredLeft <= 0) {
            switcher.toSemiClosed();
        }
        throw IgnoredCallException.INSTANCE;
    }

    @Override
    public void onSuccess() {
    }

    @Override
    public void onFailure(Exception ex) {
    }

    @Override
    public String name() {
        return "CLOSED";
    }
}
