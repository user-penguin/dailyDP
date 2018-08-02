package connect;

import org.json.JSONArray;
import org.json.JSONObject;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteConnection extends Remote {
    JSONArray getEmployeeList() throws RemoteException;
    JSONArray getManagersList() throws RemoteException;
    JSONArray getExpertsList() throws RemoteException;
}
