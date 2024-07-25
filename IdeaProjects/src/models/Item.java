package models;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Setter
@Getter
public class Item {

    AtomicInteger identifier = new AtomicInteger(1);
    private int id;
    private String name;
    private double price;

    public Item(String name, double price) {
        this.id = identifier.getAndIncrement();
        this.name = name;
        this.price = price;
    }
    public Item(int id) {
        this.id = id;
    }
}
