package lt.esdc.designpatterns.machine.state;

public final class IgnoredCallException extends RuntimeException {
    public static final IgnoredCallException INSTANCE = new IgnoredCallException();
    private IgnoredCallException() {}
}
