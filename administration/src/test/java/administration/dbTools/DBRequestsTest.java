package administration.dbTools;

import administration.Model.Employee;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBRequestsTest {

    @Test
    public void createDBConnect() {
        assertEquals(new DBRequests().createDBConnect("root", ""), true);
    }

    @Test
    public void putEmpTest() {
        Employee person = new Employee(3, "Dan", "Ololev", "Grogorich", 2);
        DBRequests req = new DBRequests();
        req.createDBConnect("root", "");
        req.putEmployee(person);
        System.out.println(req.getEmployeeId(person));
    }
}