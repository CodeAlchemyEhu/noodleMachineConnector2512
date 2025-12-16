package lt.esdc.designpatterns;

import lt.esdc.designpatterns.controller.NoodleMachineController;
import lt.esdc.designpatterns.controller.NoodleMachineControllerImpl;
import lt.esdc.designpatterns.machine.NewNoodleMachineConnector;
import lt.esdc.designpatterns.machine.NoodleMachineConnector;
import lt.esdc.designpatterns.machine.NoodleMachineV17;
import lt.esdc.designpatterns.machine.NoodleMachineV55Adapter;

public class Main {
    public static void main(String[] args) {
        NoodleMachineV17 machine = new NoodleMachineConnector();
        // NoodleMachineV17 machine = new NoodleMachineV55Adapter(new NewNoodleMachineConnector());

        NoodleMachineController controller = new NoodleMachineControllerImpl(machine);

        String[] order = {"italy", "ramen sesame tofu", "spaghetti", "chowmein chili"};
        controller.processOrder(order);
    }
}
