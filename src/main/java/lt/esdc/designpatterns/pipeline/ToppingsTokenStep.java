package lt.esdc.designpatterns.pipeline;

import java.util.ArrayList;
import java.util.List;

public final class ToppingsTokenStep extends AbstractOrderStep {
    @Override
    protected void doHandle(OrderContext ctx) {
        List<String> toppings = new ArrayList<>();
        for (int i = 2; i < ctx.tokens().size(); i++) {
            toppings.add(ctx.tokens().get(i));
        }
        ctx.toppingTokens(toppings);
    }
}
