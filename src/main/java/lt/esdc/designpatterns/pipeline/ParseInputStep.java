package lt.esdc.designpatterns.pipeline;

import java.util.ArrayList;
import java.util.List;

public final class ParseInputStep extends AbstractOrderStep {
    @Override
    protected void doHandle(OrderContext ctx) {
        String s = ctx.rawInput();
        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException("Order input is empty");
        }
        String[] parts = s.trim().split("\\s+");
        List<String> tokens = new ArrayList<>();
        for (String p : parts) {
            if (!p.isBlank()) tokens.add(p);
        }
        ctx.tokens(tokens);
    }
}
