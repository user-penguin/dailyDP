package connect;


import administration.Container;
import administration.Model.ListManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements RemoteConnection {
    public static final String BINDING_NAME = "adm/ConnectService";
    private Container container;

    @Override
    public String getEmployeeList() {
        return container.getEmployeeList().toString();
    }

    @Override
    public String getExpertsList() {
        return container.getExpertList().toString();
    }

    @Override
    public String getManagersList() { return container.getManagerList().toString(); }

    @Override
    public String test() {
        return container.getEmployeeList().toString();
    }

    @Override
    public void putEmployee(String jsonEmpData) {
        container.putEmployee(jsonEmpData);
    }

    @Override
    public void changeEmployee(String jsonEmpData) {
        container.changeEmployee(jsonEmpData);
    }

    @Override
    public String getUsers() { return container.getUsers(); }

    @Override
    public String getManagerById(int empId) throws RemoteException {
        JSONArray listManager = container.getManagerList();
        for (int i = 0; i < listManager.length(); i++) {
            JSONObject obj = (JSONObject) listManager.get(i);
            if (obj.getInt("empId") == empId)
                return obj.toString();
        }
        return null;
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
