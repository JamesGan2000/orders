package com.xyz.orders;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHandler {
    private OrderRepository orderRepository = new OrderRepository();
    private OrdersUtil ordersUtil = new OrdersUtil();

    //this class represent service-layer to delegate backend repository to do the real work

    public Order addItem(Integer id, String name){
        return orderRepository.addItem(id, name);
    }

    public boolean foundItem(Integer id) {
        return orderRepository.foundItem(id);
    }

    public boolean foundItem(Integer id, String name) {
        return orderRepository.foundItem(id, name);
    }

    public void deleteItem(Integer id, String name){
        orderRepository.deleteItem(id, name);
    }

    public void updateItem(Integer id, String newName){
        orderRepository.updateItem(id, newName);
    }

    public List<Order> showAllItems(){
        return orderRepository.showAllItems();
    }

    public List<Order> showItemsFor(String name){
        List<Order> orders = orderRepository.showAllItems();
        return ordersUtil.filterBy(orders, name);
    }
}
