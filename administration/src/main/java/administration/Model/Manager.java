package administration.Model;

import administration.Model.Employee;

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

    public int getManId() {
        return manId;
    }
}