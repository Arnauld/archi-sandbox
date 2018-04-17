package p2.myshop.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import p2.myshop.core.catalog.CatalogService;
import p2.myshop.core.shipping.DeliveryService;
import p2.myshop.core.shipping.RecipientService;
import p2.myshop.core.shoppingcart.ShoppingCartService;

@Configuration
public class ServiceConfiguration {

    @Bean
    public CatalogService catalogService() {
        return new CatalogService();
    }

    @Bean
    public DeliveryService deliveryService() {
        return new DeliveryService();
    }

    @Bean
    public ShoppingCartService shoppingCartService() {
        return new ShoppingCartService();
    }

    @Bean
    public RecipientService recipientService() {
        return new RecipientService();
    }
}
