package com.softuni.gameshop.web;

import com.softuni.gameshop.model.DTO.*;
import com.softuni.gameshop.model.DTO.order.MyOrdersDTO;
import com.softuni.gameshop.model.DTO.order.OrderDTO;
import com.softuni.gameshop.model.DTO.order.OrderDetailsDTO;
import com.softuni.gameshop.service.OrderService;
import com.softuni.gameshop.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class OrderController {

    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;

    @ModelAttribute("orderDTO")
    public OrderDTO initOrderDTO() {
        return new OrderDTO();
    }

    public OrderController(ShoppingCartService shoppingCartService, OrderService orderService) {
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public String order(Model model){

        if (shoppingCartService.getCurrentUser().getShoppingCart() == null || shoppingCartService.getCartItems().isEmpty()){
            return "redirect:/cart";
        }
        if (shoppingCartService.getCurrentUser().getEmail() == null){
            model.addAttribute("confirmed", false);
        } else {
            model.addAttribute("confirmed", true);
        }

        List<CartItemDTO> cartItems = this.shoppingCartService.getCartItems();
        BigDecimal totalPrice = this.shoppingCartService.getCartTotalPrice();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        return "order";
    }

    @PostMapping("/order")
    public String finishOrder(@Valid OrderDTO orderDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderDTO", orderDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.orderDTO", bindingResult);

            return "redirect:/order";
        }

        this.orderService.addOrder(orderDTO);

        redirectAttributes.addFlashAttribute("orderSuccessMessage", "Your order was successfully completed!");

        return "redirect:/my-orders";
    }

    @GetMapping("/my-orders")
    public String getMyOrders(Model model){
        List<MyOrdersDTO> orders = this.orderService.getMyOrders();
        model.addAttribute("orders", orders);
        return "my-orders";
    }

    @GetMapping("/my-orders/{id}")
    public String viewOrderDetails(@PathVariable Long id, Model model) {
        OrderDetailsDTO orderDetails = this.orderService.getOrderDetailsById(id);
        model.addAttribute("orderDetails", orderDetails);
        return "order-details";
    }


}
