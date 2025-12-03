package lt.esdc.designpatterns.factory;

import lt.esdc.designpatterns.domain.Region;

public final class RegionalFactoryProvider {
    private static final RegionalFactoryProvider INSTANCE = new RegionalFactoryProvider();
    private RegionalFactoryProvider() {}

    public static RegionalFactoryProvider getInstance() { return INSTANCE; }

    public RegionalFactory get(Region region) {
        return switch (region) {
            case ITALY -> new ItalyFactory();
            case INDIA -> new IndiaFactory();
        };
    }
}
