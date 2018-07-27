package administration.dbTools;

import org.junit.Test;

import static org.junit.Assert.*;

public class DBRequestsTest {

    @Test
    public void createDBConnect() {
        assertEquals(new DBRequests().createDBConnect("root", ""), true);
    }
}