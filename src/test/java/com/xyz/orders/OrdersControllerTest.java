package com.xyz.orders;

import org.junit.Assert;
import org.junit.Test;

public class OrdersControllerTest {
    OrdersController controller = new OrdersController();

    @Test
    public void updateOrder_Not_matching_up(){
        controller.createOrder(1, "A");
        Order order = new Order(1, "A");

        String expected = "Order Not Found:Order{id=2, name='B'}\n" +
                "Remaining Orders:[Order{id=1, name='A'}]";

        Assert.assertEquals(expected, controller.updateOrder(2, "B"));

        Assert.assertEquals("[" + order.toString() + "]", controller.listAllOrders());
    }

    @Test
    public void cancelOrder_Not_matching_up(){
        controller.createOrder(1, "A");
        Order order = new Order(1, "A");

        Assert.assertEquals("[" + order.toString() + "]", controller.listAllOrders());

        String expected = "Order Not Found:Order{id=2, name='B'}\n" +
                "Remaining Orders:[Order{id=1, name='A'}]";

        Assert.assertEquals(expected, controller.cancelOrder(2, "B"));

        Assert.assertEquals("[" + order.toString() + "]", controller.listAllOrders());
    }

    @Test
    public void listAllOrders_with_createOrder_cancelOrder(){
        Assert.assertEquals("[]", controller.listAllOrders());

        Order order = new Order(1, "A");

        Assert.assertEquals(order, controller.createOrder(1, "A"));

        Assert.assertEquals("[" + order.toString() + "]", controller.listAllOrders());

        String expected = "Order Cancelled:Order{id=1, name='A'}\n" +
                "Remaining Orders:[]";

        Assert.assertEquals(expected, controller.cancelOrder(1, "A"));

        Assert.assertEquals("[]", controller.listAllOrders());
    }

    @Test
    public void listAllOrders_empty_list(){
        Assert.assertEquals("[]", controller.listAllOrders());
    }

}