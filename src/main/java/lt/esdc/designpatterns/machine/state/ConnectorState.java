package lt.esdc.designpatterns.machine.state;

import java.util.concurrent.Callable;

public interface ConnectorState {
    <T> T call(Callable<T> op) throws Exception;
    void onSuccess();
    void onFailure(Exception ex);
    String name();
}
