package reserve.control.web;

import connect.RemoteConnection;
import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.IOP.ServiceContextHelper;
import org.omg.IOP.ServiceContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import reserve.control.domain.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

@Controller
public class MainController {

    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }		

//	// Show all todos
//    @RequestMapping(value="/admin/employees")
//    public String todoList(Model model) throws Exception {
//
//        Registry registry = LocateRegistry.getRegistry("localhost", 2021);
//        RemoteConnection service = (RemoteConnection) registry.lookup("adm/ConnectService");
//        JSONArray jArrWithEmployees = new JSONArray(service.getEmployeeList());
//
//        ArrayList<JSONObject> jList = new ArrayList<>();
//        for(int i = 0; i < jArrWithEmployees.length(); i++)
//            jList.add(jArrWithEmployees.getJSONObject(i));
//        model.addAttribute("employeeList", jList);
//        return "/admin/employees";
//    }
//
//    @RequestMapping(value="/index")
//    public String mainLoad(Model model) throws Exception {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) authentication.getPrincipal();
//
////        model.addAttribute("username", user.getUsername());
////        model.addAttribute("role", user.getRole());
//
//        return "index";
//    }
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

    @RequestMapping(value="/manager/vacancies")
    public String vacancyList(Model model) throws Exception {

        Registry registry = LocateRegistry.getRegistry("localhost", 2021);
        RemoteConnection admService = (RemoteConnection) registry.lookup("adm/ConnectService");


        JSONObject manager = new JSONObject( admService.getManagerById(empId));

        Registry registry1 = LocateRegistry.getRegistry("localhost", 2121);
        RemoteConnection mngService = (RemoteConnection) registry.lookup("mng/ConnectService");

        JSONArray array = new JSONArray(mngService.getVacancyList(manager.getInt("manId")));
        model.addAttribute("vacanciesList", array);

        return "/admin/employees";
    }
}
