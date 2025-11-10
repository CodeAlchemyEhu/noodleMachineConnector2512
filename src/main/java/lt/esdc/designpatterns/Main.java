package lt.esdc.designpatterns;

import lt.esdc.designpatterns.controller.NoodleMachineController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger  logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        NoodleMachineController controller = null;

        String[] order = {"ramen", "spaghetti", "chowmein"};
        controller.processOrder(order);

    }
}