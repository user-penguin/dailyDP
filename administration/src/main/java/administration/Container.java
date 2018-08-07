package administration;

import administration.Model.*;
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

        correctDBs(employee, dbRequests);
    }

    public void changeEmployee(String empData) {
        JSONObject obj = new JSONObject(empData);
        Employee employee = new Employee(obj.getInt("empId"), obj.getString("firstName"),
                obj.getString("lastName"), obj.getString("secondName"), obj.getInt("accountType"));

        DBRequests dbRequests = new DBRequests();
        dbRequests.createDBConnect("root", "");

        dbRequests.updateEmployee(employee);
        putEmployeeToContainer(employee);
        correctDBs(employee, dbRequests);
    }

    public void deleteManager(Employee employee, DBRequests dbRequests) {
        dbRequests.deleteManager(employee);
        ListManager listManager = (ListManager) container.get("listMan");
        listManager.deleteManagerByEmpId(employee.getEmpId());
    }

    public void deleteExpert(Employee employee, DBRequests dbRequests) {
        dbRequests.deleteExpert(employee);
        ListExpert listExpert = (ListExpert) container.get("listExp");
        listExpert.deleteExpertByEmpId(employee.getEmpId());
    }

    public void putManagerToContainer(Manager manager) {
        ListManager listManager = (ListManager) container.get("listMan");
        listManager.addManager(manager);
        container.put("listMan", listManager);
    }

    public void putExpertToContainer(Expert expert) {
        ListExpert listExpert = (ListExpert) container.get("listExp");
        listExpert.addExpert(expert);
        container.put("listExp", listExpert);
    }

    private void correctDBs(Employee employee, DBRequests dbRequests){
        Manager manager = new Manager(employee.getEmpId(), employee.getFirstName(), employee.getLastName(),
                employee.getSecondName(), employee.getAccountType(), 1);

        Expert expert = new Expert(employee.getEmpId(), employee.getFirstName(), employee.getLastName(),
                employee.getSecondName(), employee.getAccountType(), 1);

        //добавляем нужное
        if(employee.getAccountType() == 1 || employee.getAccountType() == 4 ||
                employee.getAccountType() == 5 || employee.getAccountType() == 7)
        {
            if (dbRequests.getManagerId(manager) == 0) {
                dbRequests.putManager(manager);
                manager.setManId(dbRequests.getManagerId(manager));
                putManagerToContainer(manager);
            }
        }
        if (employee.getAccountType() == 2 || employee.getAccountType() == 4 ||
                employee.getAccountType() == 6 || employee.getAccountType() == 7)
        {
            if (dbRequests.getExpertId(expert) == 0) {
                dbRequests.putExpert(expert);
                expert.setExId(dbRequests.getExpertId(expert));
                putExpertToContainer(expert);
            }
        }

        if (employee.getAccountType() == 3 || employee.getAccountType() == 5 ||
                employee.getAccountType() == 6 || employee.getAccountType() == 7)
        {
            //
        }

        //удаляем лишнее
        if (employee.getAccountType() == 1 || employee.getAccountType() == 3 ||
                employee.getAccountType() == 5)
        {
            if (dbRequests.getExpertId(expert) != 0)
                deleteExpert(employee,dbRequests);
        }

        if (employee.getAccountType() == 2 || employee.getAccountType() == 3 ||
                employee.getAccountType() == 6)
        {
            if (dbRequests.getManagerId(manager) != 0)
                deleteManager(employee,dbRequests);
        }
    }

    public JSONArray getEmployeeList(){
        ListEmployee listEmployee = (ListEmployee) container.get("listEmp");
        return new JSONArray(listEmployee.toString());
    }

    public JSONArray getManagerList() {
        ListManager listManager = (ListManager) container.get("listMan");
        return new JSONArray(listManager.toString());
    }

    public JSONArray getExpertList() {
        ListExpert listExpert = (ListExpert) container.get("listExp");
        return new JSONArray(listExpert.toString());
    }
    //todo при инициализации заполняются все нужные листы
}
