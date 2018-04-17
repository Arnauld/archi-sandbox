package p2.myshop.core.shoppingcart;

public class CartItem {
    private final CartItemId id;
    private final Price price;
    private final Quantity quantity;
    private final Label label;

    public CartItem(CartItemId id, Price price, Quantity quantity, Label label) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.label = label;
    }

    public CartItemId id() {
        return id;
    }

    public Label label() {
        return label;
    }

    public Price price() {
        return price;
    }

    public Quantity quantity() {
        return quantity;
    }
}
