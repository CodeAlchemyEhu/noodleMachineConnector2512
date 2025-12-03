package lt.esdc.designpatterns;

import lt.esdc.designpatterns.controller.NoodleMachineController;
import lt.esdc.designpatterns.controller.NoodleMachineControllerImpl;
import lt.esdc.designpatterns.machine.NoodleMachineConnector;

public class Main {
    public static void main(String[] args) {
        NoodleMachineController controller =
                new NoodleMachineControllerImpl(new NoodleMachineConnector());

        String[] order = {"italy", "ramen", "spaghetti", "chowmein"};
        controller.processOrder(order);
    }
}
