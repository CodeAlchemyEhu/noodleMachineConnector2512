package lt.esdc.designpatterns.machine;

public interface NoodleMachineV55 {
    String getToken();
    String openSession(String token);
    void makeNoodle(String token, String session, String noodle);
    void closeSession(String token, String session);
}
