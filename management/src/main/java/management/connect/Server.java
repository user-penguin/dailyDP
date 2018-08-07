package management.connect;


import management.Container;
import org.json.JSONArray;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements RemoteConnection {
    public static final String BINDING_NAME = "mng/ConnectService";
    private Container container;

    public Server(){
        container = new Container();
        container.init();
    }

    public static void main(String[] args) throws Exception{
        System.out.print("Starting registry...");
        final Registry registry = LocateRegistry.createRegistry(2021);
        System.out.println(" OK");

        final RemoteConnection service = new Server();
        Remote stub = UnicastRemoteObject.exportObject(service, 0);

        System.out.print("Binding service...");
        registry.bind(BINDING_NAME, stub);
        System.out.println(" OK");

        //service.getEmployeeList();

        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}
