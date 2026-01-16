package lt.esdc.designpatterns.pricing.strategies;

import lt.esdc.designpatterns.pricing.DiscountStrategy;

public final class DiscountStrategyFactory {
    private DiscountStrategyFactory() {
    }

    public static DiscountStrategy fromToken(String token) {
        if (token == null) throw new IllegalArgumentException("Discount token is null");
        String t = token.trim().toLowerCase();

        return switch (t) {
            case "none" -> new NoDiscountStrategy();
            case "student" -> new StudentDiscountStrategy();
            case "loyalty", "loyaltycard", "loyalty_card", "loyalty-card", "card" -> new LoyaltyDiscountStrategy();
            default -> throw new IllegalArgumentException("Unknown discount token: " + token);
        };
    }
}
