package models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Setter
@Getter
public class User {
    private String id;
    private String name;
    private UserType userType;
    private HashSet<String> orderIds;

}
