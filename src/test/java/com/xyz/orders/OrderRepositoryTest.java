package com.xyz.orders;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {
    OrderRepository orderRepository = new OrderRepository();

    @Test
    void deleteItem_indeed_deleted(){
        Order order1 = new Order(1, "A");
        Order order2 = new Order(2, "B");
        Order order3 = new Order(3, "C");

        orderRepository.addItem(order1);
        orderRepository.addItem(order2);
        orderRepository.addItem(order3);

        Integer size1 = orderRepository.allItems.size();

        orderRepository.deleteItem(order2);

        assert orderRepository.foundItem(1, "A");
        assert !orderRepository.foundItem(2, "B");
        assert orderRepository.foundItem(3, "C");

        Integer size2 = orderRepository.allItems.size();

        assert size2 == size1 -1;
    }

    @Test
    void foundItem_false(){
        Order order = new Order(1, "A");
        orderRepository.addItem(order);

        assert !orderRepository.foundItem(1, "B");
        assert !orderRepository.foundItem(2, "A");
    }

    @Test
    void foundItem_true(){
        // item not found [before being added]
        assert !orderRepository.foundItem(1, "A");

        Order order = new Order(1, "A");
        orderRepository.addItem(order);

        Integer size1 = orderRepository.allItems.size();

        // item found [after being added]
        assert orderRepository.foundItem(1, "A");

        Integer size2 = orderRepository.allItems.size();

        // foundItem should not change the list behind the repository
        assert size2 == size1;
    }

    @Test
    void addItem(){
        Order order = new Order(1, "A");
        Integer size1 = orderRepository.allItems.size();
        assertFalse(orderRepository.allItems.contains(order));

        orderRepository.addItem(order);

        Integer size2 = orderRepository.allItems.size();
        assertTrue(orderRepository.allItems.contains(order));

        assertTrue(size2 == size1 + 1);
    }
}