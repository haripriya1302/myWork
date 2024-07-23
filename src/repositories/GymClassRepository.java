package repositories;

import model.GymClass;

import java.util.HashMap;

public class GymClassRepository {

    private GymClassRepository gymClassRepository = null;

    public GymClassRepository getInstance() {
        if(gymClassRepository == null) {
            gymClassRepository = new GymClassRepository();
        }
        return gymClassRepository;
    }

    public HashMap<String, GymClass> classes = new HashMap<>();
    public HashMap<String, GymClass> removedClasses = new HashMap<>();

    public void addClass(String classId, GymClass gymClass) {
        classes.put(classId, gymClass);
    }

    public void removeClass(String classId) {
        removedClasses.put(classId, classes.get(classId));
        classes.remove(classId);
    }

    public GymClass getGymClass(String gymClassId) {
        return classes.get(gymClassId);
    }
    public boolean isValidClass(String classId) {
        if(classes.containsKey(classId)) {
            return true;
        }
        return false;
    }
}
