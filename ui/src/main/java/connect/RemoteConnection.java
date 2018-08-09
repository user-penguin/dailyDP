package connect;

import org.json.JSONArray;
import org.json.JSONObject;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteConnection extends Remote {
    String getEmployeeList() throws RemoteException;
    String getManagersList() throws RemoteException;
    String getExpertsList() throws RemoteException;
    String test() throws RemoteException;
    void putEmployee(String jsonEmpData) throws RemoteException;
    void changeEmployee(String jsonEmpData) throws RemoteException;
    String getUsers() throws RemoteException;
    String getCandidateList(int manId) throws RemoteException;
    String getVacancyList(int manId) throws RemoteException;
    void putCandidate(String jsonData) throws RemoteException;
    void putVacancy(String jsonData) throws RemoteException;
    void putCandidateToVacancy(int vacId, int canId, int manId) throws RemoteException;
    String getCOV(int manId, int vacId) throws RemoteException;
}
