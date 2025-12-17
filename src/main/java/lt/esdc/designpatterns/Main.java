package lt.esdc.designpatterns;

import lt.esdc.designpatterns.facade.NoodleOrderFacade;

public class Main {
    public static void main(String[] args) {
        NoodleOrderFacade facade = NoodleOrderFacade.withLegacyMachine();
        facade.order("italy",
                "ramen sesame tofu",
                "spaghetti",
                "chowmein chili");
    }
}
