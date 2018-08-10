package administration.Model;

import administration.Model.Employee;
import org.json.JSONObject;

public class Manager extends Employee {
    //fields
    private int manId;
    private int departId;

    public Manager()
    {}

    public Manager(int empId, String firstName, String lastName, String secondName,
                   int accountType, int manId, int departId)
    {
        super(empId,firstName,lastName,secondName, accountType);
        this.manId = manId;
        this.departId = departId;
    }

    public Manager(int empId, String firstName, String lastName, String secondName,
                   int accountType, int departId)
    {
        super(empId,firstName,lastName,secondName, accountType);
        this.departId = departId;
    }

    public int getManId() {
        return manId;
    }

    public void setManId(int manId) {
        this.manId = manId;
    }

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();
        obj.put("empId", this.getEmpId());
        obj.put("firstName", this.getFirstName());
        obj.put("lastName", this.getLastName());
        obj.put("secondName", this.getSecondName());
        obj.put("accountType", this.getAccountType());
        obj.put("manId", this.getManId());
        obj.put("departId", this.getDepartId());
        return obj.toString();
    }
}