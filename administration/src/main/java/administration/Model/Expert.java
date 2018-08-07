package administration.Model;

import administration.Model.Employee;

public class Expert extends Employee {
    //fields
    private int exId;
    private int departId;

    public Expert()
    {}

    public Expert(int empId, String firstName, String lastName, String secondName,
                  int accountType, int exId, int departId)
    {
        super(empId,firstName,lastName,secondName, accountType);
        this.exId = exId;
        this.departId = departId;
    }

    public Expert(int empId, String firstName, String lastName, String secondName,
                  int accountType, int departId)
    {
        super(empId,firstName,lastName,secondName, accountType);
        this.departId = departId;
    }

    public int getExId() {
        return exId;
    }

    public void setExId(int exId){
        this.exId = exId;
    }

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }
}