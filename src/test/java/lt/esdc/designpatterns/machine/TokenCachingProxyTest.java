package lt.esdc.designpatterns.machine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenCachingProxyTest {

    @Test
    void should_cache_token_and_call_target_only_once() {
        FakeV55 target = new FakeV55();
        TokenCachingProxy proxy = new TokenCachingProxy(target);

        String t1 = proxy.getToken();
        String t2 = proxy.getToken();

        assertEquals(t1, t2);
        assertEquals(1, target.tokenCalls);
    }

    @Test
    void should_fetch_new_token_after_invalidate() {
        FakeV55 target = new FakeV55();
        TokenCachingProxy proxy = new TokenCachingProxy(target);

        String t1 = proxy.getToken();
        proxy.invalidateToken();
        String t2 = proxy.getToken();

        assertNotEquals(t1, t2);
        assertEquals(2, target.tokenCalls);
    }

    private static final class FakeV55 implements NoodleMachineV55 {
        int tokenCalls;

        @Override
        public String getToken() {
            tokenCalls++;
            return "token-" + tokenCalls;
        }

        @Override
        public String openSession(String token) {
            return "session";
        }

        @Override
        public void makeNoodle(String token, String session, String noodle) {}

        @Override
        public void closeSession(String token, String session) {}
    }
}
