package com.xyz.orders;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrdersController {
    private OrderHandler orderHandler = new OrderHandler();
    private OrdersUtil ordersUtil = new OrdersUtil();

    // the controller class delegate service-layer class to do the work
    // the controller class should not have very complicated coding logic/implementation

    @GetMapping("/create-order")
    @ResponseBody
    public Order createOrder(@RequestParam(name = "id") Integer id,
                          @RequestParam(name="name", required=false, defaultValue="Default-Name") String name) {
        return orderHandler.addItem(id, name);
    }

    @GetMapping("/update-order")
    @ResponseBody
    public String updateOrder(@RequestParam(name = "id", required = false) Integer id,
                          @RequestParam(name="newName", required=false) String newName) {
        boolean foundItem = orderHandler.foundItem(id);

        if(foundItem){
            orderHandler.updateItem(id, newName);
        }

        return ordersUtil.messageFor(foundItem, id, newName, "order Updated");
    }

    @GetMapping("/cancel-order")
    @ResponseBody
    public String cancelOrder(@RequestParam(name = "id", required = false) Integer id,
                          @RequestParam(name="name", required=false) String name) {
        boolean foundItem = orderHandler.foundItem(id, name);

        if(foundItem){
            orderHandler.deleteItem(id, name);
        }

        return ordersUtil.messageFor(foundItem, id, name, "order Cancelled");
    }

    @GetMapping("/list-by-name/{name}")
    @ResponseBody
    public String listByName(@PathVariable("name") String name) {
        return ordersUtil.infoFor(orderHandler.showItemsFor(name));
    }

    @GetMapping("/list-all-order")
    @ResponseBody
    public String listAllOrders() {
        return ordersUtil.infoFor(orderHandler.showAllItems());
    }
}
