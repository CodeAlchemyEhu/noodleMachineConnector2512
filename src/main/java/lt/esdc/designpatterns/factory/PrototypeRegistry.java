package lt.esdc.designpatterns.factory;

import lt.esdc.designpatterns.domain.DishType;
import lt.esdc.designpatterns.domain.NoodleRecipe;
import lt.esdc.designpatterns.domain.Region;

import java.util.EnumMap;
import java.util.Map;

public final class PrototypeRegistry {
    private static final PrototypeRegistry INSTANCE = new PrototypeRegistry();

    private final Map<Region, Map<DishType, NoodleRecipe>> registry = new EnumMap<>(Region.class);

    private PrototypeRegistry() {
        Map<DishType, NoodleRecipe> italy = new EnumMap<>(DishType.class);
        italy.put(DishType.RAMEN, new NoodleRecipe.Builder().noodle(100).water(250).broth(150).vegetables(40).build());
        italy.put(DishType.SPAGHETTI, new NoodleRecipe.Builder().noodle(150).water(100).broth(50).vegetables(30).build());
        italy.put(DishType.CHOWMEIN, new NoodleRecipe.Builder().noodle(120).water(150).broth(40).vegetables(30).build());

        Map<DishType, NoodleRecipe> india = new EnumMap<>(DishType.class);
        india.put(DishType.RAMEN, new NoodleRecipe.Builder().noodle(120).water(300).broth(150).vegetables(70).build());
        india.put(DishType.SPAGHETTI, new NoodleRecipe.Builder().noodle(140).water(120).broth(60).vegetables(60).build());
        india.put(DishType.CHOWMEIN, new NoodleRecipe.Builder().noodle(130).water(100).broth(70).vegetables(80).build());

        registry.put(Region.ITALY, italy);
        registry.put(Region.INDIA, india);
    }

    public static PrototypeRegistry getInstance() {
        return INSTANCE;
    }

    public NoodleRecipe prototypeOf(Region region, DishType dish) {
        return registry.get(region).get(dish).clone();
    }
}
