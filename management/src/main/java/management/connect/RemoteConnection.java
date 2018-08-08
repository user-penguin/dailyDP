package management.connect;

import org.json.JSONArray;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteConnection extends Remote {
    //methods
    String getCandidateList() throws RemoteException;
    String getVacancyList() throws RemoteException;
    void putCandidate(String jsonData) throws RemoteException;
    void putVacancy(String jsonData) throws RemoteException;
}
