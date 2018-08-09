package management.connect;

import org.json.JSONArray;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteConnection extends Remote {
    //methods
    String getCandidateList(int manId) throws RemoteException;
    String getVacancyList(int manId) throws RemoteException;
    void putCandidate(String jsonData) throws RemoteException;
    void putVacancy(String jsonData) throws RemoteException;
    String getManagersList() throws RemoteException;
    void putCandidateToVacancy(int vacId, int canId, int manId) throws RemoteException;
    String getCOV(int manId, int vacId) throws RemoteException;
}
