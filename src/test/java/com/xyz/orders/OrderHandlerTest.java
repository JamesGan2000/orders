package com.xyz.orders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderHandlerTest {

    @InjectMocks OrderHandler handler;
    @Mock OrderRepository orderRepository;
    @Mock OrdersUtil ordersUtil;

    @Mock Order order;
    @Mock List<Order> list;

    final private Integer ID = 123;
    final private String NAME = "XYZ";

    //this test class is to show-case Mockito testing
    // [to show behavior-driven-testing] - because it delegates the Repository class to do the work

    @Test
    public void showAllItems(){
        when(orderRepository.showAllItems()).thenReturn(list);
        assert list == handler.showAllItems();
    }

    @Test
    public void showItemsFor(){
        List<Order> filteredList = new ArrayList<>();
        filteredList.add(order);

        when(orderRepository.showAllItems()).thenReturn(list);
        when(ordersUtil.filterBy(list, NAME)).thenReturn(filteredList);
        assert filteredList == handler.showItemsFor(NAME);
    }

    @Test
    public void updateItem(){
        handler.updateItem(ID, NAME);
        verify(orderRepository).updateItem(ID, NAME);
    }

    @Test
    public void deleteItem(){
        handler.deleteItem(ID, NAME);
        verify(orderRepository).deleteItem(ID, NAME);
    }

    @Test
    public void addItem(){
        when(orderRepository.addItem(ID, NAME)).thenReturn(order);
        assert order == handler.addItem(ID, NAME);
    }

    @Test
    public void foundItem_by_id_false(){
        when(orderRepository.foundItem(ID)).thenReturn(false);
        assert !handler.foundItem(ID);
    }

    @Test
    public void foundItem_by_id_true(){
        when(orderRepository.foundItem(ID)).thenReturn(true);
        assert handler.foundItem(ID);
    }

    @Test
    public void foundItem_by_id_name_false(){
        when(orderRepository.foundItem(ID, NAME)).thenReturn(false);
        assert !handler.foundItem(ID, NAME);
    }

    @Test
    public void foundItem_by_id_name_true(){
        when(orderRepository.foundItem(ID, NAME)).thenReturn(true);
        assert handler.foundItem(ID, NAME);
    }
}