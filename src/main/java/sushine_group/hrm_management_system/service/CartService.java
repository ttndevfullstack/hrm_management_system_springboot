package sushine_group.hrm_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import sushine_group.hrm_management_system.model.CartItem;
import sushine_group.hrm_management_system.model.Product;
import sushine_group.hrm_management_system.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();

    @Autowired
    private ProductRepository productRepository;

    public void addToCart(Long productID, int quantity) {
        Product product = productRepository.findById(productID).orElseThrow(() -> new IllegalArgumentException("Product not found: " + productID));
        cartItems.add(new CartItem(product, quantity));
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void removeFromCart(Long productID) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(productID));
    }

    public void clearCart() {
        cartItems.clear();
    }
}
