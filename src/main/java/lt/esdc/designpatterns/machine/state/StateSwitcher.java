package lt.esdc.designpatterns.machine.state;

public interface StateSwitcher {
    void toOpen();
    void toClosed();
    void toSemiClosed();
}
