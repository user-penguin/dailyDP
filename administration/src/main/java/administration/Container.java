package administration;

import administration.Model.ListEmployee;
import administration.Model.ListExpert;
import administration.Model.ListManager;
import administration.dbTools.DBRequests;
import org.json.JSONArray;
import java.util.HashMap;
import java.util.Map;

public class Container {
    private Map<String, Object> container;

    public Container() {
        container = new HashMap<>();
    }

    public void init() {
        DBRequests dbRequests = new DBRequests();
        dbRequests.createDBConnect("root", "");

        ListEmployee listEmployee = new ListEmployee();
        listEmployee.fillList(dbRequests.getArrOfEmployeeFromDB());

        container.put("listEmp", listEmployee);

        ListManager listManager = new ListManager();
        listManager.fillList(dbRequests.getArrOfManagersFromDB());

        container.put("listMan", listManager);

        ListExpert listExpert = new ListExpert();
        listExpert.fillList(dbRequests.getArrOfExpertsFromDB());

        container.put("listExp", listExpert);
    }

    public JSONArray getEmployeeList(){
        ListEmployee listEmployee = (ListEmployee) container.get("listEmp");
        return new JSONArray(listEmployee.toString());
    }
    //todo при инициализации заполняются все нужные листы
}
