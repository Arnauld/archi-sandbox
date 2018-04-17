package p2.myshop.core.shipping;

public class DeliveryService {
    public double calculateOrderWeight(DeliveryRequest deliveryRequest) {
        return deliveryRequest.weight();
    }

    public void process(DeliveryRequest deliveryRequest) {
        double weight = calculateOrderWeight(deliveryRequest);
        //...
    }
}
