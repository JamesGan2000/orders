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
        return orderRepository.addItem(id, name);
    }

    @GetMapping("/update-order")
    @ResponseBody
    public String updateOrder(@RequestParam(name = "id", required = false) Integer id,
                          @RequestParam(name="newName", required=false) String newName) {
        boolean foundItem = orderRepository.foundItem(id, newName);

        if(foundItem){
            orderRepository.updateItem(id, newName);
        }

        String result = foundItem ? "Order Updated:" : "Order Not Found:";

        Order order = new Order(id, newName);
        return result + order.toString() + "\n" +
                "Remaining Orders:" + listAllOrders();
    }

    @GetMapping("/cancel-order")
    @ResponseBody
    public String cancelOrder(@RequestParam(name = "id", required = false) Integer id,
                          @RequestParam(name="name", required=false) String name) {
        boolean foundItem = orderRepository.foundItem(id, name);

        if(foundItem){
            orderRepository.deleteItem(id, name);
        }

        String result = foundItem ? "Order Cancelled:" : "Order Not Found:";

        Order order = new Order(id, name);
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
