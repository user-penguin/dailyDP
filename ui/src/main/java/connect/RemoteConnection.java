package connect;

import org.json.JSONObject;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteConnection extends Remote {
    JSONObject getEmployeeList() throws RemoteException;
    JSONObject getManagersList() throws RemoteException;
    JSONObject getExpertsList() throws RemoteException;
}
