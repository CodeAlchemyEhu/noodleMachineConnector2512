package lt.esdc.designpatterns.machine;

import java.util.Objects;

public final class TokenCachingProxy implements NoodleMachineV55 {

    private final NoodleMachineV55 target;
    private String cachedToken;

    public TokenCachingProxy(NoodleMachineV55 target) {
        this.target = Objects.requireNonNull(target, "target");
    }

    @Override
    public synchronized String getToken() {
        if (cachedToken == null) {
            cachedToken = target.getToken();
        }
        return cachedToken;
    }

    @Override
    public String openSession(String token) {
        return target.openSession(token);
    }

    @Override
    public void makeNoodle(String token, String session, String noodle) {
        target.makeNoodle(token, session, noodle);
    }

    @Override
    public void closeSession(String token, String session) {
        target.closeSession(token, session);
    }

    public synchronized void invalidateToken() {
        cachedToken = null;
    }
}
