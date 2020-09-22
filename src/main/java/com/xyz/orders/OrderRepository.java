package com.xyz.orders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderRepository {
    List<Order> allItems = new ArrayList<>();

    public void addItem(Order order){
        allItems.add(order);
    }

    public boolean foundItem(Integer id, String name) {
        return allItems.contains(new Order(id, name));
    }

    public void deleteItem(Order order){
        allItems.remove(order);
    }

    public List<Order> showAllItems(){
        Collections.sort(allItems, new SortById());
        return allItems;
    }
}
