package administration.Model;

import administration.Model.Employee;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListEmployee {
    private ArrayList<Employee> employees;

    public ListEmployee() {
        employees = new ArrayList<Employee>();
    }

    public void fillList(JSONArray listEmployees){
        if (listEmployees != null) {
            JSONObject obj;
            Employee employee;
            for (int i = 0; i < listEmployees.length(); i++) {
                obj = (JSONObject) listEmployees.get(i);

                employee = new Employee(obj.getInt("id"),
                        obj.getString("firstName"), obj.getString("lastName"),
                        obj.getString("secondName"), obj.getInt("idTypeAccount"));
                employees.add(employee);
            }
        }
    }


    public ArrayList<Employee> getManagersList(){
        ArrayList<Employee> managers = new ArrayList<Employee>();
        for (Employee var : employees) {
            if (var.getAccountType() == 1 || var.getAccountType() == 4 || var.getAccountType() == 5 || var.getAccountType() == 7)
                managers.add(var);
        }
        return managers;
    }

    public ArrayList<Employee> getExpertsList(){
        ArrayList<Employee> experts = new ArrayList<Employee>();
        for (Employee var : employees) {
            if (var.getAccountType() == 2 || var.getAccountType() == 4 || var.getAccountType() == 6 || var.getAccountType() == 7)
                experts.add(var);
        }
        return experts;
    }

    public ArrayList<Employee> getEmployees()
    {
        return employees;
    }

    public Employee getEmployeeById(int id)
    {
        for (Employee var : employees) {
            if (var.getEmpId() == id)
                return var;
        }
        return null;
    }
}