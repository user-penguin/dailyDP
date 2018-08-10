package reserve.control.web;

import connect.RemoteConnection;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import reserve.control.domain.*;
import reserve.control.services.HtmlElement;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import static java.util.stream.Collectors.joining;

@Controller
public class MainController {

    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }		

//	// Show all todos
    @RequestMapping(value="/employees")
    public String todoList(Model model) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
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


        Registry registry = LocateRegistry.getRegistry("localhost", 2021);
        RemoteConnection service = (RemoteConnection) registry.lookup("adm/ConnectService");
        JSONArray jArrWithEmployees = new JSONArray(service.getEmployeeList());

        ArrayList<JSONObject> jList = new ArrayList<>();
        for(int i = 0; i < jArrWithEmployees.length(); i++)
            jList.add(jArrWithEmployees.getJSONObject(i));


        model.addAttribute("employeeList", jList);
        model.addAttribute("activity", elements);

        return "/employees";
    }

    @RequestMapping(value="/index")
    public String mainLoad(Model model) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
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

        model.addAttribute("username", user.getUsername().toString());
        model.addAttribute("activity", elements);

        return "index";
    }
//
//    @RequestMapping(value = "/saveEmp", method = RequestMethod.POST)
//    public String saveEmp(@RequestParam("addLastName") String lastName, @RequestParam("addFirstName") String firstName,
//                          @RequestParam("addSecondName") String secondName, @RequestParam("addAccountType") int accountType)
//                            throws Exception{
//        JSONObject empData = new JSONObject();
//        empData.put("lastName", lastName);
//        empData.put("firstName", firstName);
//        empData.put("secondName", secondName);
//        empData.put("accountType", accountType);
//
//        Registry registry = LocateRegistry.getRegistry("localhost", 2021);
//        RemoteConnection service = (RemoteConnection) registry.lookup("adm/ConnectService");
//        service.putEmployee(empData.toString());
//        return "redirect:/admin/employees";
//    }
//
//    @RequestMapping(value = "/changeEmp", method = RequestMethod.POST)
//    public String changeEmp(@RequestParam("chId") int id, @RequestParam("chLastName") String lastName,
//                            @RequestParam("chFirstName") String firstName, @RequestParam("chSecondName") String secondName,
//                            @RequestParam("chAccountType") int accountType)   throws Exception {
//        JSONObject empData = new JSONObject();
//        empData.put("lastName", lastName);
//        empData.put("firstName", firstName);
//        empData.put("secondName", secondName);
//        empData.put("accountType", accountType);
//        empData.put("empId", id);
//
//        Registry registry = LocateRegistry.getRegistry("localhost", 2021);
//        RemoteConnection service = (RemoteConnection) registry.lookup("adm/ConnectService");
//        service.changeEmployee(empData.toString());
//        return "redirect:/admin/employees";
//    }
}
