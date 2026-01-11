package lt.esdc.designpatterns;

import lt.esdc.designpatterns.facade.NoodleOrderFacade;

public class Main {
    public static void main(String[] args) {
        NoodleOrderFacade facadeNew = NoodleOrderFacade.withNewMachine();
        facadeNew.order("italy", "none ramen sesame tofu");
        NoodleOrderFacade facade = NoodleOrderFacade.withLegacyMachine();
        facade.order("italy",
                "student ramen sesame tofu",
                "none spaghetti",
                "none chowmein chili");
    }
}
