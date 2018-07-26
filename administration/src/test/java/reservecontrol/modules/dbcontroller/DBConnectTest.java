package reservecontrol.modules.dbcontroller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBConnectTest {

    @Test
    public void checkDBConnect() {
        assertEquals(new DBRequests().createDBConnect("admin", "1488"), true);
    }


    @Test
    public void readTest() {
        DBRequests DBR = new DBRequests();
        DBR.createDBConnect("admin", "1488");
        DBR.getArrOfEmployeeFromDB();
    }
}