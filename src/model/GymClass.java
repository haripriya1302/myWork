package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GymClass {
    private String classId;
    private ClassType classType;
    private String gymId;
    private int maxLimit;
    private int startTime;
    private int endTime;
    private ClassStatus classStatus;
    private List<String> bookingIds;
}
