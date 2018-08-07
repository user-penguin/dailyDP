package reserve.control.web;

import connect.RemoteConnection;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import reserve.control.domain.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

@Controller
public class TodoController {
    @Autowired
    private TodoRepository repository;


    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }		

	// Show all todos
    @RequestMapping(value="/employees")
    public String todoList(Model model) throws Exception {

        Registry registry = LocateRegistry.getRegistry("localhost", 2021);
        RemoteConnection service = (RemoteConnection) registry.lookup("adm/ConnectService");
        JSONArray jArrWithEmployees = new JSONArray(service.getEmployeeList());

        ArrayList<JSONObject> jList = new ArrayList<>();
        for(int i = 0; i < jArrWithEmployees.length(); i++)
            jList.add(jArrWithEmployees.getJSONObject(i));
        model.addAttribute("employeeList", jList);
        return "employees";
    }

    @RequestMapping(value = "/saveEmp", method = RequestMethod.POST)
    public String saveEmp(@RequestParam("addLastName") String lastName, @RequestParam("addFirstName") String firstName,
                          @RequestParam("addSecondName") String secondName, @RequestParam("addAccountType") int accountType)
                            throws Exception{
        JSONObject empData = new JSONObject();
        empData.put("lastName", lastName);
        empData.put("firstName", firstName);
        empData.put("secondName", secondName);
        empData.put("accountType", accountType);

        Registry registry = LocateRegistry.getRegistry("localhost", 2021);
        RemoteConnection service = (RemoteConnection) registry.lookup("adm/ConnectService");
        service.putEmployee(empData.toString());
        return "redirect:employees";
    }

    @RequestMapping(value = "changeEmp", method = RequestMethod.POST)
    public String changeEmp(@RequestParam("chId") int id, @RequestParam("chLastName") String lastName,
                            @RequestParam("chFirstName") String firstName, @RequestParam("chSecondName") String secondName,
                            @RequestParam("chAccountType") int accountType)   throws Exception {
        JSONObject empData = new JSONObject();
        empData.put("lastName", lastName);
        empData.put("firstName", firstName);
        empData.put("secondName", secondName);
        empData.put("accountType", accountType);
        empData.put("empId", id);

        Registry registry = LocateRegistry.getRegistry("localhost", 2021);
        RemoteConnection service = (RemoteConnection) registry.lookup("adm/ConnectService");
        service.changeEmployee(empData.toString());
        return "redirect:employees";
    }
}
