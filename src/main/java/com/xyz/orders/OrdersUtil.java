package com.xyz.orders;

import java.util.ArrayList;
import java.util.List;

public class OrdersUtil {
    public String infoFor(List<Order> list) {
        List<String> info = new ArrayList<>();
        for(Order order : list){
            info.add(order.toString());
        }
        return info.toString();
    }

    public String messageFor(boolean foundItem, Integer id, String name, String successMessage){
        String result = foundItem ? successMessage : "Order Not Found:";

        Order order = new Order(id, name);
        return result + order.toString();
    }

    public List<Order> filterBy(List<Order> orders, String name){
        List<Order> matchingOrders = new ArrayList<>();

        for(Order order : orders){
            if(order.getName().equals(name)){
                matchingOrders.add(order);
            }
        }
        return matchingOrders;
    }
}
