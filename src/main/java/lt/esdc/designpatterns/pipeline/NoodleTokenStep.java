package lt.esdc.designpatterns.pipeline;

public final class NoodleTokenStep extends AbstractOrderStep {
    @Override
    protected void doHandle(OrderContext ctx) {
        if (ctx.tokens().size() < 2) {
            throw new IllegalArgumentException("Expected: <discount> <noodle> [toppings...]");
        }
        ctx.noodleToken(ctx.tokens().get(1));
    }
}
