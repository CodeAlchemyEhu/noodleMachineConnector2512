package lt.esdc.designpatterns.factory;

import lt.esdc.designpatterns.domain.DishType;
import lt.esdc.designpatterns.domain.NoodleRecipe;

public interface RegionalFactory {
    NoodleRecipe create(DishType dish); // factory method
}
