package management.connect;


import management.Container;
import org.json.JSONArray;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements RemoteConnection {
    public static final String BINDING_NAME = "mng/ConnectService";
    private Container container;

    @Override
    public String getCandidateList(int manId) throws RemoteException {
        return container.getCandidateList(manId);
    }

    @Override
    public String getVacancyList(int manId) throws RemoteException {
        return container.getVacanciesList(manId);
    }

    @Override
    public void putCandidate(String jsonData) throws RemoteException {
        container.putCandidate(jsonData);
    }

    @Override
    public void putVacancy(String jsonData) throws RemoteException {
        container.putVacancy(jsonData);
    }

    @Override
    public String getManagersList() throws RemoteException {
        return null;
    }

    public Server() throws RemoteException, NotBoundException {
        container = new Container();
        container.init();
    }

    public static void main(String[] args) throws Exception{
        System.out.print("Starting registry...");
        final Registry registry = LocateRegistry.createRegistry(2121);
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
