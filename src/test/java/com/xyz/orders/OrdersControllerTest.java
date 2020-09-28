package com.xyz.orders;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrdersControllerTest {
    @InjectMocks OrdersController controller;

    @Mock OrderHandler orderHandler;
    @Mock OrdersUtil ordersUtil;
    @Mock List<Order> orders;

    final private Integer ID = 123;
    final private String NAME = "XYZ";

    //this test class is to show-case Mockito testing
    // [to show behavior-driven-testing] - because it delegates the Handler class to do the work

    @Test
    public void createOrder(){
        controller.createOrder(ID, NAME);
        verify(orderHandler).addItem(ID, NAME);
    }

    @Test
    public void updateOrder_matching_up(){
        String expected = "Order Updated:Order{id=1, name='A'}";

        when(orderHandler.foundItem(ID)).thenReturn(true);
        when(ordersUtil.messageFor(true, ID, NAME, "order Updated")).thenReturn(expected);

        assertEquals(expected, controller.updateOrder(ID, NAME));
    }

    @Test
    public void updateOrder_Not_matching_up(){
        String expected = "Order Not Found:Order{id=1, name='A'}";

        when(orderHandler.foundItem(ID)).thenReturn(false);
        when(ordersUtil.messageFor(false, ID, NAME, "order Updated")).thenReturn(expected);

        assertEquals(expected, controller.updateOrder(ID, NAME));
    }

    @Test
    public void cancelOrder_matching_up(){
        String expected = "Order Cancelled:Order{id=1, name='A'}";

        when(orderHandler.foundItem(ID, NAME)).thenReturn(true);
        when(ordersUtil.messageFor(true, ID, NAME, "order Cancelled")).thenReturn(expected);

        assertEquals(expected, controller.cancelOrder(ID, NAME));
    }

    @Test
    public void cancelOrder_Not_matching_up(){
        String expected = "Order Not Found:Order{id=1, name='A'}";

        when(orderHandler.foundItem(ID, NAME)).thenReturn(false);
        when(ordersUtil.messageFor(false, ID, NAME, "order Cancelled")).thenReturn(expected);

        assertEquals(expected, controller.cancelOrder(ID, NAME));
    }

    @Test
    public void listByName(){
        // the main purpose of this test is NOT to test how ordersUtil.infoFor() method build message
        // the important thing is to make sure whatever message built by the Util method gets returned back
        when(orderHandler.showItemsFor(NAME)).thenReturn(orders);
        when(ordersUtil.infoFor(orders)).thenReturn("someMsg");
        assertEquals("someMsg", controller.listByName(NAME));
    }

    @Test
    public void listAllOrders(){
        // the main purpose of this test is NOT to test how ordersUtil.infoFor() method build message
        // the important thing is to make sure whatever message built by the Util method gets returned back
        when(orderHandler.showAllItems()).thenReturn(orders);
        when(ordersUtil.infoFor(orders)).thenReturn("someMsg");
        assertEquals("someMsg", controller.listAllOrders());
    }
}