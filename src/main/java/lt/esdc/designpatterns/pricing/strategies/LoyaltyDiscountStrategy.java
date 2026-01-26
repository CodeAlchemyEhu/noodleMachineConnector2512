package lt.esdc.designpatterns.pricing.strategies;

import lt.esdc.designpatterns.pricing.DiscountStrategy;

public final class LoyaltyDiscountStrategy implements DiscountStrategy {
    @Override
    public double apply(double totalPrice) {
        return totalPrice * 0.90;
    }

    @Override
    public String name() {
        return "LOYALTY";
    }
}
