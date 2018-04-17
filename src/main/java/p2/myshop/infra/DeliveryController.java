package p2.myshop.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import p2.myshop.core.catalog.CatalogService;
import p2.myshop.core.catalog.Item;
import p2.myshop.core.catalog.ItemId;
import p2.myshop.core.shipping.DeliveryRequest;
import p2.myshop.core.shipping.DeliveryService;
import p2.myshop.core.shipping.Recipient;
import p2.myshop.core.shipping.RecipientService;
import p2.myshop.core.shipping.Shippable;
import p2.myshop.core.shoppingcart.Cart;
import p2.myshop.core.shoppingcart.CartItem;
import p2.myshop.core.shoppingcart.ShoppingCartService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final p2.myshop.core.shoppingcart.ShoppingCartService shoppingCartService;
    private final p2.myshop.core.shipping.DeliveryService deliveryService;
    private final p2.myshop.core.catalog.CatalogService catalogService;
    private final p2.myshop.core.shipping.RecipientService recipientService;

    @Autowired
    public DeliveryController(ShoppingCartService shoppingCartService,
                              DeliveryService deliveryService,
                              CatalogService catalogService,
                              RecipientService recipientService) {
        this.shoppingCartService = shoppingCartService;
        this.deliveryService = deliveryService;
        this.catalogService = catalogService;
        this.recipientService = recipientService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void deliver(@RequestBody DeliverCommand command) {
        Cart cart = shoppingCartService.loadCart(command.cartId);
        Recipient recipient = recipientService.loadRecipient(command.recipientId);

        List<Shippable> shippables = toShippables(cart);

        deliveryService.process(new DeliveryRequest(shippables, recipient));
    }

    private List<Shippable> toShippables(Cart cart) {
        return cart.items().stream().map(this::toShippable).collect(toList());
    }

    private Shippable toShippable(CartItem cartItem) {
        Item item = catalogService.loadItem(ItemId.from(cartItem.id().asString()));
        return new Shippable(cartItem.label().asString(), item.weight());
    }

    public static class DeliverCommand {
        public String cartId;
        public String recipientId;
    }
}
