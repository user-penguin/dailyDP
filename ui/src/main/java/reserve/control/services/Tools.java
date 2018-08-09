package reserve.control.services;

import reserve.control.domain.Role;

import java.util.ArrayList;
import java.util.List;

public class Tools {

    public static List<Role> getRoles(int numOfRole) {
        ArrayList<Role> result = new ArrayList<>();
        if(numOfRole == 1) {
            result.add(Role.MANAGER);
        }
        else if(numOfRole == 2) {
            result.add(Role.EXPERT);
        }
        else if(numOfRole == 3) {
            result.add(Role.ADMIN);
        }
        else if(numOfRole == 4) {
            result.add(Role.MANAGER);
            result.add(Role.EXPERT);
        }
        else if(numOfRole == 5) {
            result.add(Role.MANAGER);
            result.add(Role.ADMIN);
        }
        else if(numOfRole == 6) {
            result.add(Role.EXPERT);
            result.add(Role.ADMIN);
        }
        else if(numOfRole == 1) {
            result.add(Role.MANAGER);
            result.add(Role.EXPERT);
            result.add(Role.ADMIN);
        }
        return result;
    }
}
