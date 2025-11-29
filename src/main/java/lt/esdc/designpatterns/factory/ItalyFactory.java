package lt.esdc.designpatterns.factory;

import lt.esdc.designpatterns.domain.DishType;
import lt.esdc.designpatterns.domain.NoodleRecipe;
import lt.esdc.designpatterns.domain.Region;

public final class ItalyFactory implements RegionalFactory {
    private final PrototypeRegistry registry = PrototypeRegistry.getInstance();
    @Override public NoodleRecipe create(DishType dish) {
        return registry.prototypeOf(Region.ITALY, dish);
    }
}
