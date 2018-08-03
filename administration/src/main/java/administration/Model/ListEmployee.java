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

                employee = new Employee(obj.getInt("empId"),
                        obj.getString("firstName"), obj.getString("lastName"),
                        obj.getString("secondName"), obj.getInt("idTypeAccount"));
                employees.add(employee);
            }
        }
    }

    public void addEmployee(Employee employee)
    {
        for(Employee var : employees)
        {
            if (employee.getEmpId() == var.getEmpId())
                return;
        }
        employees.add(employee);
    }

    public void addEmployee(JSONObject obj)
    {
        for(Employee var : employees)
        {
            if (obj.getInt("empId") == var.getEmpId())
                return;
        }
        Employee employee = new Employee(obj.getInt("empId"),
                obj.getString("firstName"), obj.getString("lastName"),
                obj.getString("secondName"), obj.getInt("idTypeAccount"));
        employees.add(employee);
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

    @Override
    public String toString()
    {
        JSONArray array = new JSONArray();
        for (Employee employee : employees){
            JSONObject object = new JSONObject();
            object.put("empId", employee.getEmpId());
            object.put("firstName", employee.getFirstName());
            object.put("lastName", employee.getLastName());
            object.put("secondName", employee.getSecondName());
            object.put("idTypeAccount", employee.getAccountType());
            array.put(object);
        }
        return array.toString();
    }
}