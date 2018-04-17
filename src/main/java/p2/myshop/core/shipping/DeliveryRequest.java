package p2.myshop.core.shipping;

import java.util.List;

public class DeliveryRequest {
    private final List<Shippable> shippables;
    private final Recipient recipient;

    public DeliveryRequest(List<Shippable> shippables, Recipient recipient) {
        this.shippables = shippables;
        this.recipient = recipient;
    }

    public Recipient recipient() {
        return recipient;
    }

    public List<Shippable> shippables() {
        return shippables;
    }

    public double weight() {
        return shippables().stream().mapToDouble(Shippable::weight).sum();
    }
}
