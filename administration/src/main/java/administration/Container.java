package administration;

import administration.Model.Employee;
import administration.Model.ListEmployee;
import administration.Model.ListExpert;
import administration.Model.ListManager;
import administration.dbTools.DBRequests;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public void putEmployeeToContainer(Employee employee) {
        ListEmployee listEmployee = (ListEmployee) container.get("listEmp");
        listEmployee.addEmployee(employee);
        container.put("listEmp", listEmployee);
    }

    public void putEmployee(String jsonEmpData) {
        JSONObject empData = new JSONObject(jsonEmpData);
        Employee employee = new Employee(empData.getString("firstName"), empData.getString("lastName"),
                empData.getString("secondName"), empData.getInt("accountType"));

        DBRequests dbRequests = new DBRequests();
        dbRequests.createDBConnect("root", "");

        dbRequests.putEmployee(employee);
        employee.setEmpId(dbRequests.getEmployeeId(employee));
        putEmployeeToContainer(employee);
    }

    public JSONArray getEmployeeList(){
        ListEmployee listEmployee = (ListEmployee) container.get("listEmp");
        return new JSONArray(listEmployee.toString());
    }
    //todo при инициализации заполняются все нужные листы
}
