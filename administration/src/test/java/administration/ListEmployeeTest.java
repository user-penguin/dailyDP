package administration;

import administration.Model.Employee;
import administration.Model.ListEmployee;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListEmployeeTest {

    @Test
    public void checkFillListJSONArrayToArrayList() throws JSONException {
        JSONArray employees = new JSONArray("[{\"lastName\":\"кобзев\",\"firstName\":\"дмитрий\",\"id\":1,\"idTypeAccount\":\"1\",\"secondName\":\"игоревич\"},\n" +
                "         {\"lastName\":\"демченко\",\"firstName\":\"алексей\",\"id\":2,\"idTypeAccount\":\"2\",\"secondName\":\"григорьевич\"},\n" +
                "         {\"lastName\":\"алексеев\",\"firstName\":\"панч\",\"id\":3,\"idTypeAccount\":\"5\",\"secondName\":\"лайнович\"}]");


        //[{"lastName":"кобзев","firstName":"дмитрий","id":1,"idTypeAccount":"1","secondName":"игоревич"},
         //{"lastName":"демченко","firstName":"алексей","id":2,"idTypeAccount":"2","secondName":"григорьевич"},
         //{"lastName":"алексеев","firstName":"панч","id":3,"idTypeAccount":"5","secondName":"лайнович"}]

        ListEmployee listEmployee = new ListEmployee();
        listEmployee.fillList(employees);
        Employee emp = new Employee(2, "алексей", "демченко", "григорьевич",2);

        //for (int i = 0; i < 4; i++) {
            assertEquals(listEmployee.getEmployeeById(2).getEmpId(), emp.getEmpId());
        //}
    }
}