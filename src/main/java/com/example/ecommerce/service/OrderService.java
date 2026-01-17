package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderDTO;
import com.example.ecommerce.entity.cart.CartItem;
import com.example.ecommerce.entity.customer.CustomerEntity;
import com.example.ecommerce.entity.order.Order;
import com.example.ecommerce.entity.order.OrderItem;
import com.example.ecommerce.entity.order.OrderStatus;
import com.example.ecommerce.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderMapper orderMapper;

    public OrderDTO checkout(Integer customerId) {

        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        var cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        List<CartItem> cartItems = cartItemRepository.findByCart(cart);

        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("Cart is Empty");
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderStatus(OrderStatus.CREATED);


        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> {

                            OrderItem item = new OrderItem();
                            item.setOrder(order);
                            item.setProductId(cartItem.getProduct().getId());
                            item.setQuantity(cartItem.getQuantity());
                            item.setImage(cartItem.getProduct().getImage());
                            item.setName(cartItem.getProduct().getName());
                            item.setPriceCents(cartItem.getProduct().getPriceCents());
                            return item;
                        }
                ).toList();

        int totalPrice = orderItems.stream().mapToInt(i -> i.getPriceCents() * i.getQuantity()).sum();

        order.setTotalPriceCents(totalPrice);
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        cartItemRepository.deleteAll(cartItems);

        return orderMapper.toOrderDTO(order);
    }

    public List<OrderDTO> getOrderByCustomerId(Integer customerId) {
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        return orderRepository.findByCustomerId(customerId)
                .stream()
                .map(orderMapper::toOrderDTO)
                .toList();
    }

    public OrderDTO getOrderByOrderId(Long orderId) {
        var Order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        return orderMapper.toOrderDTO(Order);
    }
}
