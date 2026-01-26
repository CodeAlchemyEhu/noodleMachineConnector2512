package lt.esdc.designpatterns;

import lt.esdc.designpatterns.facade.NoodleOrderFacade;

public class StateCheck {
    public static void main(String[] args) {
        NoodleOrderFacade facade = NoodleOrderFacade.withNewMachine();

        System.out.println("1) OK order");
        try { facade.order("italy", "ramen sesame tofu"); } catch (Exception ignored) {}

        System.out.println("2) BAD order (should throw)");
        try { facade.order("italy", ""); } catch (Exception ignored) {}

        System.out.println("3) BAD order again (should throw, now state should become CLOSED)");
        try { facade.order("italy", ""); } catch (Exception ignored) {}

        System.out.println("4) 5 calls while CLOSED (should be ignored)");
        for (int i = 1; i <= 5; i++) {
            try { facade.order("italy", "ramen sesame tofu"); } catch (Exception ignored) {}
        }

        System.out.println("5) One more call (SEMI-CLOSED allows 1 request)");
        try { facade.order("italy", "ramen sesame tofu"); } catch (Exception ignored) {}
    }
}
