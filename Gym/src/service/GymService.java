package service;

import model.Gym;
import model.GymClass;
import repositories.GymRepository;
import utils.GymUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class GymService {
    private GymUtil gymUtil;
    private GymRepository gymRepository;
    private GymClassService gymClassService;
    private final ReentrantLock lock = new ReentrantLock();


    public GymService(GymUtil gymUtil, GymRepository gymRepository, GymClassService gymClassService) {
        this.gymRepository = gymRepository;
        this.gymUtil = gymUtil;
        this.gymClassService = gymClassService;
    }


    public String addGym(String name, String location, int maxAccomodation) {
        lock.lock();
        try {
            String id = gymUtil.generateUUID();
            while(gymUtil.checkIdExistsInMap(id, gymRepository.gyms.keySet())) {
                id = gymUtil.generateUUID();
            }
            Gym gym = new Gym();
            gym.setName(name);
            gym.setId(id);
            gym.setLocation(location);
            gym.setMaxAccommodation(maxAccomodation);
            gym.setClasses(new ArrayList<>());
            System.out.println(gym.getId2());
            gymRepository.addGym(id, gym);
            return id;
        } finally {
            lock.unlock();
        }


    }

    public synchronized void removeGym(String gymId) {
        if(gymUtil.checkIdExistsInMap(gymId, gymRepository.gyms.keySet())) {
            gymClassService.cancelClasses(gymId);

            gymRepository.removeGym(gymId);
        } else {
            throw new RuntimeException("Gym doesn't exist");
        }
    }
}
