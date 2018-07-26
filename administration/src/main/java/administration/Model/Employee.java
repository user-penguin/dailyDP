package administration.Model;

public class Employee {
    //fields
    private int empId;
    private String firstName;
    private String lastName;
    private String secondName;
    //1 - manager, 2 - expert, 3 - admin,
    //4 - manager/expert, 5 - manager/admin
    //6 - expert/admin, 7 - all
    private int accountType;

    public Employee()
    {
    }

    public Employee(int id, String firstName, String lastName, String secondName, int accountType)
    {
        this.empId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.accountType = accountType;
    }

    public int getAccountType() {
        return accountType;
    }

    public int getEmpId() { return empId; }
}