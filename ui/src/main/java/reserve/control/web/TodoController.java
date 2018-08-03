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
        RemoteConnection service = (RemoteConnection) registry.lookup("sample/ConnectService");
        JSONArray jArrWithEmployees = new JSONArray(service.getEmployeeList());

        ArrayList<JSONObject> jList = new ArrayList<>();
        for(int i = 0; i < jArrWithEmployees.length(); i++)
            jList.add(jArrWithEmployees.getJSONObject(i));
        model.addAttribute("employeeList", jList);
        return "employees";
    }

    @RequestMapping(value = "/saveEmp", method = RequestMethod.POST)
    public String saveEmp(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName,
                          @RequestParam("secondName") String secondName, @RequestParam("typeAccount") int typeAccount) {
        return "redirect:employees";
    }


}
