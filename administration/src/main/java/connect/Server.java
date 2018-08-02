package connect;

import administration.AdmConsumer;
import administration.Container;
import administration.Model.ListEmployee;
import administration.Model.ListExpert;
import administration.Model.ListManager;
import administration.dbTools.DBRequests;
import org.json.JSONObject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class Server implements RemoteConnection {
    public static final String BINDING_NAME = "connect/ConnectService";
    private Container container;

    @Override
    public JSONObject getEmployeeList() {
        return container.getEmployeeList();
    }

    @Override
    public JSONObject getExpertsList() {
        return null;
    }

    @Override
    public JSONObject getManagersList() {
        return null;
    }

    public Server(){
        container = new Container();
        container.init();
    }

    public static void main(String[] args) throws Exception{
        //Container container = new Container();

        System.out.print("Starting registry...");
        final Registry registry = LocateRegistry.createRegistry(8080);
        System.out.println(" OK");

        final RemoteConnection service = new Server();
        Remote stub = UnicastRemoteObject.exportObject(service, 0);

        System.out.print("Binding service...");
        registry.bind(BINDING_NAME, stub);
        System.out.println(" OK");

        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}
