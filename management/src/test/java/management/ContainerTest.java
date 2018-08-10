package management;

import management.connect.RemoteConnection;
import org.junit.Test;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static org.junit.Assert.*;

public class ContainerTest {

    @Test
    public void testConn() throws Exception, AccessException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 2021);
        RemoteConnection service = (RemoteConnection) registry.lookup("adm/ConnectService");
        int a = 2 + 3;
    }

}