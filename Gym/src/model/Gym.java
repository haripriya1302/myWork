package model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Gym {
    private static AtomicInteger random = new AtomicInteger(1);
    private String id;
    private int id2;
    private String name;
    private String location;
    private int maxAccommodation;
    public  List<String> classes;

    public Gym(){
        this.id2 = random.getAndIncrement();
    }


}
