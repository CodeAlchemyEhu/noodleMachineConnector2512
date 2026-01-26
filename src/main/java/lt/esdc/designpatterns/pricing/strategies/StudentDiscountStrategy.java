package lt.esdc.designpatterns.pricing.strategies;

import lt.esdc.designpatterns.pricing.DiscountStrategy;

public final class StudentDiscountStrategy implements DiscountStrategy {
    @Override
    public double apply(double totalPrice) {
        return totalPrice * 0.80;
    }

    @Override
    public String name() {
        return "STUDENT";
    }
}
