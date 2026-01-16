package lt.esdc.designpatterns.pipeline;

public final class OrderProcessingChain {
    private final OrderStep first;

    public OrderProcessingChain() {
        OrderStep parse = new ParseInputStep();
        OrderStep discount = new DiscountTokenStep();
        OrderStep noodle = new NoodleTokenStep();
        OrderStep toppings = new ToppingsTokenStep();

        parse.setNext(discount);
        discount.setNext(noodle);
        noodle.setNext(toppings);

        this.first = parse;
    }

    public OrderContext process(String input) {
        OrderContext ctx = new OrderContext(input);
        first.handle(ctx);
        return ctx;
    }
}
