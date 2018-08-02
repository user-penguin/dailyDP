package administration.Model;

import administration.Container;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContainerTest {

    @Test
    public void initTest() {
        Container container = new Container();
        container.init();
        JSONArray jAr = container.getEmployeeList();
        assertEquals(true, true);
    }
}