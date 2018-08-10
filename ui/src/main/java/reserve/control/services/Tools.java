package reserve.control.services;

import reserve.control.domain.Role;
import reserve.control.domain.User;

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

    public static ArrayList<HtmlElement> autoFillElements(User user) {
        ArrayList<HtmlElement> elements = new ArrayList<>();

        elements.add(new HtmlElement());
        elements.get(elements.size() - 1).transformToMain();
        elements.get(elements.size() - 1).setActive(true);

        for(int i = 0; i < user.getAuthorities().size(); i++) {
            if(user.getAuthorities().get(i).getAuthority().compareTo("MANAGER") == 0) {
                HtmlElement element = new HtmlElement();
                element.transformToManagment();
                elements.add(element);
            }
            else if(user.getAuthorities().get(i).getAuthority().compareTo("ADMIN") == 0) {
                HtmlElement element = new HtmlElement();
                element.transformToAdministrate();
                elements.add(element);
            }
        }

        return elements;
    }
}
