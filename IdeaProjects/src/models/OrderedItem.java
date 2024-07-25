package models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderedItem {
    private String restaurantId;
    private int itemId;
    private int quantity;
}
