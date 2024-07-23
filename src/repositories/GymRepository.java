package repositories;

import model.Gym;

import java.util.ArrayList;
import java.util.HashMap;

public class GymRepository {
    private GymRepository gymRepository = null;
    public GymRepository getInstance() {
        if(gymRepository == null) {
            gymRepository = new GymRepository();
        }
        return gymRepository;
    }
    public HashMap<String, Gym> gyms = new HashMap<>();
    public HashMap<String, Gym> removedGyms = new HashMap<>();

    public void addGym(String gymId, Gym gym) {
        gyms.put(gymId, gym);
    }

    public void removeGym(String gymId) {
        removedGyms.put(gymId, gyms.get(gymId));
        gyms.remove(gymId);
    }

    public Gym getGym(String gymId) {
        return gyms.get(gymId);
    }

    public boolean isGymValid(String gymId) {
        return gyms.containsKey(gymId);
    }

    public void addClassToGym(String classId, String gymId) {
        if(removedGyms.containsKey(gymId)) {
            throw new RuntimeException("Cannot make edits, gym is Inactive");
        }
        Gym gym = gyms.get(gymId);
        gym.getClasses().add(classId);
        gyms.put(gymId, gym);
    }

    public void updateGym(String gymId, Gym gym) {
        gyms.put(gymId, gym);
    }
}
