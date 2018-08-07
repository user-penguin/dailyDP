package connect;

import org.json.JSONArray;
import org.json.JSONObject;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteConnection extends Remote {
    String getEmployeeList() throws RemoteException;
    JSONArray getManagersList() throws RemoteException;
    JSONArray getExpertsList() throws RemoteException;
    String test() throws RemoteException;
    void putEmployee(String jsonEmpData) throws RemoteException;
    void changeEmployee(String jsonEmpData) throws RemoteException;
}
