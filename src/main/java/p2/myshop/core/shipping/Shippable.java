package p2.myshop.core.shipping;

public class Shippable {

    private final String label;
    private final double weight;

    public Shippable(String label, double weight) {
        this.label = label;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }
}
