package com.xyz.orders;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    List<Order> allItems = new ArrayList<>();

    public Order addItem(Integer id, String name){
        Order order = new Order(id, name);
        allItems.add(order);
        return order;
    }

    public boolean foundItem(Integer id) {
        for(Order order : allItems){
            if(order.getId().equals(id)){
                return true;
            }
        }

        return false;
    }

    public boolean foundItem(Integer id, String name) {
        return allItems.contains(new Order(id, name));
    }

    public void deleteItem(Integer id, String name){
        allItems.remove(new Order(id, name));
    }

    public void updateItem(Integer id, String newName){
        for(Order order : allItems){
            if(order.getId().equals(id)){
                order.setName(newName);
            }
        }
    }

    public List<Order> showAllItems(){
        allItems.sort(new SortById());
        return allItems;
    }
}
