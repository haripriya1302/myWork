package models;

import lombok.Data;

import java.util.HashSet;

@Data
public class Menu {
    private String id;
    private HashSet<Item> availableItems;
    private HashSet<Item> unavailableItems;
}
