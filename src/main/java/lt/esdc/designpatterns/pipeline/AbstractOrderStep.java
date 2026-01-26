package lt.esdc.designpatterns.pipeline;

public abstract class AbstractOrderStep implements OrderStep {
    private OrderStep next;

    @Override
    public final void setNext(OrderStep next) {
        this.next = next;
    }

    @Override
    public final void handle(OrderContext ctx) {
        doHandle(ctx);
        if (next != null) {
            next.handle(ctx);
        }
    }

    protected abstract void doHandle(OrderContext ctx);
}
