package lt.esdc.designpatterns.machine.state;

import java.util.concurrent.Callable;

public final class SemiClosedState implements ConnectorState {
    private final StateSwitcher switcher;
    private boolean used;

    public SemiClosedState(StateSwitcher switcher) {
        this.switcher = switcher;
    }

    @Override
    public <T> T call(Callable<T> op) throws Exception {
        if (used) throw IgnoredCallException.INSTANCE;

        used = true;
        return op.call();
    }

    @Override
    public void onSuccess() {
        switcher.toOpen();
    }

    @Override
    public void onFailure(Exception ex) {
        switcher.toClosed();
    }

    @Override
    public String name() {
        return "SEMI-CLOSED";
    }
}
