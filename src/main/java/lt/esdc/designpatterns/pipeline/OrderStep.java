package lt.esdc.designpatterns.pipeline;

public interface OrderStep {
    void handle(OrderContext ctx);
    void setNext(OrderStep next);
}
