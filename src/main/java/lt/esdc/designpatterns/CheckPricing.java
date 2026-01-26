package lt.esdc.designpatterns;

import lt.esdc.designpatterns.pipeline.OrderContext;
import lt.esdc.designpatterns.pipeline.OrderProcessingChain;
import lt.esdc.designpatterns.pricing.DiscountStrategy;
import lt.esdc.designpatterns.pricing.strategies.DiscountStrategyFactory;

import java.util.List;

public class CheckPricing {

    public static void main(String[] args) {
        test("italy", "student ramen sesame tofu");
        test("india", "none spaghetti");
        test("italy", "loyalty chowmein chili");
    }

    private static void test(String region, String input) {
        OrderProcessingChain chain = new OrderProcessingChain();
        OrderContext ctx = chain.process(input);

        System.out.println("INPUT: " + input);
        System.out.println("discount=" + ctx.discountToken());
        System.out.println("noodle=" + ctx.noodleToken());
        System.out.println("toppings=" + ctx.toppingTokens());

        double base = basePrice(region, ctx.noodleToken()) + toppingsPrice(ctx.toppingTokens());
        DiscountStrategy strategy = DiscountStrategyFactory.fromToken(ctx.discountToken());
        double total = strategy.apply(base);

        System.out.println("base=" + round2(base) + " total=" + round2(total) + " strategy=" + strategy.name());
        System.out.println();
    }

    private static double basePrice(String region, String noodle) {
        String r = region.toLowerCase();
        String n = noodle.toLowerCase();

        if (r.equals("italy")) {
            return switch (n) {
                case "ramen" -> 3.00;
                case "spaghetti" -> 4.50;
                case "chowmein", "chow_mein", "chow-mein" -> 5.00;
                default -> throw new IllegalArgumentException("Unknown noodle: " + noodle);
            };
        }

        if (r.equals("india")) {
            return switch (n) {
                case "ramen" -> 2.80;
                case "spaghetti" -> 2.20;
                case "chowmein", "chow_mein", "chow-mein" -> 3.80;
                default -> throw new IllegalArgumentException("Unknown noodle: " + noodle);
            };
        }

        throw new IllegalArgumentException("Unknown region: " + region);
    }

    private static double toppingsPrice(List<String> toppings) {
        double sum = 0.0;
        for (String t : toppings) {
            String x = t.toLowerCase();
            sum += switch (x) {
                case "sesame" -> 0.50;
                case "tofu" -> 0.90;
                case "chili" -> 0.65;
                default -> throw new IllegalArgumentException("Unknown topping: " + t);
            };
        }
        return sum;
    }

    private static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}
