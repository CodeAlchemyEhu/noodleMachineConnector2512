package lt.esdc.designpatterns.pricing;

public interface DiscountStrategy {

    double apply(double totalPrice);

    String name();
}
