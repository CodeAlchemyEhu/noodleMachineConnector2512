package lt.esdc.designpatterns.pricing.strategies;

import lt.esdc.designpatterns.pricing.DiscountStrategy;

public final class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double apply(double totalPrice) {
        return totalPrice;
    }

    @Override
    public String name() {
        return "NONE";
    }
}
