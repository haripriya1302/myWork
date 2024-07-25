package models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
@Setter@Getter
public class Order {
    AtomicInteger counter = new AtomicInteger(1);

    private int id;
    private String restaurantName;
    private List<OrderedItem> orderedItems;
    private OrderStatus orderStatus;
    public Order(String restaurantName, List<OrderedItem> orderedItems, OrderStatus orderStatus) {
        this.id = counter.getAndIncrement();
        this.restaurantName = restaurantName;
        this.orderedItems = orderedItems;
        this.orderStatus = orderStatus;
    }
}
