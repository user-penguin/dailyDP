package connect;


import administration.Container;
import org.json.JSONArray;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements RemoteConnection {
    public static final String BINDING_NAME = "sample/ConnectService";
    private Container container;

    @Override
    public String getEmployeeList() {
        return container.getEmployeeList().toString();
    }

    @Override
    public JSONArray getExpertsList() {
        return null;
    }

    @Override
    public JSONArray getManagersList() {
        return null;
    }

    @Override
    public String test() {
        return container.getEmployeeList().toString();
    }

    public Server(){
        container = new Container();
        container.init();
    }

    public static void main(String[] args) throws Exception{
        //Container container = new Container();

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
