package reserve.control.domain;

public class Manager extends Employee {
    //fields
    private int manId;
    private int departId;
    private String departName;

    public Manager()
    {}

    public Manager(int empId, String firstName, String lastName, String secondName,
                   int accountType, int manId, int departId, String departName)
    {
        super(empId,firstName,lastName,secondName, accountType);
        this.manId = manId;
        this.departId = departId;
        this.departName = departName;
    }

    public int getManId() {
        return manId;
    }
}