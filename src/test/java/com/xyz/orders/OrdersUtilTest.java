package com.xyz.orders;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrdersUtilTest {
    private OrdersUtil util = new OrdersUtil();
    private List<Order> list = new ArrayList<>();

    // each method of the OrdersUtil class has some kind of algorithm to build output based on input paramaters
    // so some representative test cases are in place to cover each method's implementation
    // [in this case, would not use Mock object or Mockito]

    @Test
    public void filterBy_non_empty_list(){
        list.add(new Order(1, "A"));
        list.add(new Order(2, "B"));
        list.add(new Order(3, "C"));

        List<Order> expected = new ArrayList<>();
        expected.add(new Order(2, "B"));

        assertEquals(expected, util.filterBy(list, "B"));
    }

    @Test
    public void filterBy_empty_list(){
        assertEquals(list, util.filterBy(list, "A"));
    }

    @Test
    public void messageFor_updating_order_with_itemNotFound(){
        assertEquals("Order Not Found:Order{id=1, name='A'}", util.messageFor(false, 1, "A", "Order Updated:"));
    }

    @Test
    public void messageFor_updating_order_with_itemFound(){
        assertEquals("Order Updated:Order{id=1, name='A'}", util.messageFor(true, 1, "A", "Order Updated:"));
    }

    @Test
    public void infoFor_empty_list(){
        assert "[]" == util.infoFor(list);
    }

    @Test
    public void infoFor_one_item(){
        list.add(new Order(1, "A"));
        assertEquals("[Order{id=1, name='A'}]", util.infoFor(list));
    }

    @Test
    public void infoFor_multiple_items(){
        list.add(new Order(1, "A"));
        list.add(new Order(2, "B"));
        list.add(new Order(3, "C"));
        String expected = "[Order{id=1, name='A'}, Order{id=2, name='B'}, Order{id=3, name='C'}]";
        assertEquals(expected, util.infoFor(list));
    }

}