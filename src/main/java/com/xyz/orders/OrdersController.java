package com.xyz.orders;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrdersController {
    private OrderRepository orderRepository = new OrderRepository();

    @GetMapping("/create-order")
    @ResponseBody
    public Order createOrder(@RequestParam(name = "id", required = true) Integer id,
                          @RequestParam(name="name", required=false, defaultValue="Default-Name") String name) {
        Order order = new Order(id, name);
        orderRepository.addItem(order);
        return order;
    }

    @GetMapping("/cancel-order")
    @ResponseBody
    public String cancelOrder(@RequestParam(name = "id", required = false) Integer id,
                          @RequestParam(name="name", required=false) String name) {
        Order order = new Order(id, name);
        boolean foundItem = orderRepository.foundItem(id, name);

        if(foundItem){
            orderRepository.deleteItem(order);
        }

        String result = foundItem ? "Cancel Order Success:" : "Order Not Found:";
        return result + order.toString() + "\n" +
                "Remaining Orders:" + listAllOrders();
    }

    @GetMapping("/list-all-order")
    @ResponseBody
    public String listAllOrders() {
        List<Order> orders = orderRepository.showAllItems();
        List<String> info = new ArrayList<>();
        for(Order order : orders){
            info.add(order.toString());
        }
        return info.toString();
    }
}
