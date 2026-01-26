package lt.esdc.designpatterns;

import lt.esdc.designpatterns.machine.NoodleMachineConnector;

public class StateCheckLegacy {
    public static void main(String[] args) {
        NoodleMachineConnector c = new NoodleMachineConnector();

        String ok = "120g 400ml 250ml 50g sesame";
        String bad = "wrong";

        System.out.println(c.stateName());
        try { c.send(bad); } catch (Exception ignored) {}
        System.out.println(c.stateName());
        try { c.send(bad); } catch (Exception ignored) {}
        System.out.println(c.stateName());

        for (int i = 0; i < 5; i++) {
            c.send(ok);
        }
        System.out.println(c.stateName());

        c.send(ok);
        System.out.println(c.stateName());
    }
}
