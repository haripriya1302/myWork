package utils;

import java.util.Set;
import java.util.UUID;

public class GymUtil {

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public boolean checkIdExistsInMap(String id, Set<String> keys) {
        if(keys.contains(id)) {
            return true;
        }
        return false;
    }
}
