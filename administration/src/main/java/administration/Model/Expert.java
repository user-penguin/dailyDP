package administration.Model;

import administration.Model.Employee;

public class Expert extends Employee {
    //fields
    private int exId;
    private int departId;
    private String departName;

    public Expert()
    {}

    public Expert(int empId, String firstName, String lastName, String secondName,
                  int accountType, int exId, int departId, String departName)
    {
        super(empId,firstName,lastName,secondName, accountType);
        this.exId = exId;
        this.departId = departId;
        this.departName = departName;
    }

    public int getExId() {
        return exId;
    }
}