package p2.myshop.infra.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import p2.myshop.core.shipping.DeliveryRequest;
import p2.myshop.core.shipping.DeliveryService;
import p2.myshop.core.shipping.Recipient;
import p2.myshop.core.shipping.Shippable;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryControllerAsync {

    private final HttpClient httpClient;
    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryControllerAsync(HttpClient httpClient,
                                   DeliveryService deliveryService) {
        this.httpClient = httpClient;
        this.deliveryService = deliveryService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void deliver(@RequestBody DeliverCommand command) {
        CartDTO cartDTO = httpClient.get(command.cartUrl, CartDTO.class);
        CustomerDTO customerDTO = httpClient.get(command.customerUrl, CustomerDTO.class);

        List<Shippable> shippables = toShippables(cartDTO);
        Recipient recipient = toRecipient(customerDTO);

        deliveryService.process(new DeliveryRequest(shippables, recipient));
    }

    private Recipient toRecipient(CustomerDTO customer) {
        return null;
    }

    private List<Shippable> toShippables(CartDTO cart) {
        return null;
    }

    public static class DeliverCommand {
        public String cartUrl;
        public String customerUrl;
    }
}
