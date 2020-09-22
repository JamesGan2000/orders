package com.xyz.orders;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {
    OrderRepository orderRepository = new OrderRepository();

    @Test
    void deleteItem_indeed_deleted(){
        orderRepository.addItem(1, "A");
        orderRepository.addItem(2, "B");
        orderRepository.addItem(3, "C");

        Integer size1 = orderRepository.allItems.size();

        orderRepository.deleteItem(2, "B");

        assert orderRepository.foundItem(1, "A");
        assert !orderRepository.foundItem(2, "B");
        assert orderRepository.foundItem(3, "C");

        Integer size2 = orderRepository.allItems.size();

        assert size2 == size1 -1;
    }

    @Test
    void updateItem(){
        orderRepository.addItem(1, "A");

        assert !orderRepository.foundItem(1, "B");
        assert !orderRepository.foundItem(2, "A");

        orderRepository.updateItem(1, "B");

        assert orderRepository.foundItem(1, "B");
        assert !orderRepository.foundItem(1, "A");
    }

    @Test
    void foundItem_false(){
        orderRepository.addItem(1, "A");

        assert !orderRepository.foundItem(1, "B");
        assert !orderRepository.foundItem(2, "A");
    }

    @Test
    void foundItem_true(){
        // item not found [before being added]
        assert !orderRepository.foundItem(1, "A");

        orderRepository.addItem(1, "A");

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

        orderRepository.addItem(1, "A");

        Integer size2 = orderRepository.allItems.size();
        assertTrue(orderRepository.allItems.contains(order));

        assertTrue(size2 == size1 + 1);
    }
}