package sushine_group.hrm_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sushine_group.hrm_management_system.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        return "cart/cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productID, @RequestParam int quantity) {
        cartService.addToCart(productID, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{productID}")
    public String removeFromCart(@PathVariable Long productID) {
        cartService.removeFromCart(productID);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }


}
