package com.bai3.hrm_management_system.service;

import com.bai3.hrm_management_system.model.CartItem;
import com.bai3.hrm_management_system.model.Order;
import com.bai3.hrm_management_system.model.OrderDetail;
import com.bai3.hrm_management_system.repository.OrderDetailRepository;
import com.bai3.hrm_management_system.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CartService cartService;

    @Transactional
    public Order createOrder(String customerName, String address, String email, String note, String payment, List<CartItem> cartItems) {
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setAdress(address);
        order.setEmail(email);
        order.setNote(note);
        order.setPayment(payment);
        order = orderRepository.save(order);

        for (CartItem item : cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            orderDetailRepository.save(detail);
        }

        cartService.clearCart();
        return order;
    }
}
