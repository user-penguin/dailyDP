package management;

import management.Model.*;
import management.connect.RemoteConnection;
import management.dbTools.DBRequests;
import org.json.JSONArray;
import org.json.JSONObject;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class Container {
//    private Map<String, Object> container;
    private Map<Integer, HashMap<String, Object>> container;
    public Container() { container = new HashMap<>(); }

    public void init() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 2021);
        RemoteConnection service = (RemoteConnection) registry.lookup("adm/ConnectService");
        JSONArray jsonArray = new JSONArray(service.getManagersList());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = (JSONObject) jsonArray.get(i);
            container.put(obj.getInt("manId"), new HashMap<>());
        }

        DBRequests dbRequests = new DBRequests();
        dbRequests.createDBConnect("root", "");

        for (Integer key : container.keySet()) {
            ListCandidates listCandidates = new ListCandidates();
            listCandidates.fillList(dbRequests.getArrOfCandidatesFromDB(key));

            HashMap<String, Object> hashMap = container.get(key);
            hashMap.put("listCan", listCandidates);

            ListVacancies listVacancies = new ListVacancies();
            listVacancies.fillList(dbRequests.getArrOfVacanciesFromDB(key));

            hashMap.put("listVac", listVacancies);

            ListCOV listCOV = new ListCOV();
            listCOV.fillList(listVacancies);

            hashMap.put("listCOV", listCOV);

            container.put(key, hashMap);
        }
    }

    private void putCandidateToContainer(Candidate candidate, int manId) {
        HashMap<String, Object> map = container.get(manId);
        ListCandidates listCandidates = (ListCandidates) map.get("listCan");

        listCandidates.addCandidate(candidate);
        map.put("listCan", listCandidates);
        container.put(manId, map);
    }

    public void putCandidate(String jsonData) {
        JSONObject empData = new JSONObject(jsonData);
        Candidate candidate = new Candidate(empData.getString("firstName"),
                empData.getString("lastName"), empData.getString("secondName"),
                empData.getString("phone"), empData.getString("email"),
                empData.getString("skills"), empData.getInt("managerId"),
                empData.getInt("statusId"), empData.getInt("resumeId"));

        DBRequests dbRequests = new DBRequests();
        dbRequests.createDBConnect("root", "");

        dbRequests.putCandidate(candidate);
        candidate.setCanId(dbRequests.getCandidateId(candidate));
        putCandidateToContainer(candidate, candidate.getManagerId());
    }

    private void putVacancyToContainer(Vacancy vacancy, int manId) {
        HashMap<String, Object> map = container.get(manId);
        ListVacancies listVacancies = (ListVacancies) map.get("listVac");
        listVacancies.addVacancy(vacancy);
        map.put("listVac", listVacancies);
        container.put(manId, map);
    }

    public void putVacancy(String jsonData) {
        JSONObject empData = new JSONObject(jsonData);
        Vacancy vacancy = new Vacancy(empData.getInt("manId"), empData.getString("title"),
                empData.getString("description"), empData.getString("requirements"),
                empData.getInt("statusId"));

        DBRequests dbRequests = new DBRequests();
        dbRequests.createDBConnect("root", "");

        dbRequests.putVacancy(vacancy);
        vacancy.setVacancyId(dbRequests.getVacancyId(vacancy));
        putVacancyToContainer(vacancy, vacancy.getManager());
    }

    public String getCandidateList(int manId) {
        HashMap<String, Object> map = container.get(manId);

        ListCandidates listCandidates = (ListCandidates) map.get("listCan");
        return listCandidates.toString();
    }

    public String getVacanciesList(int manId) {
        HashMap<String, Object> map = container.get(manId);

        ListVacancies listVacancies = (ListVacancies) map.get("listVac");
        return listVacancies.toString();
    }

    public String getListCOV(int manId) {
        HashMap<String, Object> map = container.get(manId);

        ListCOV listCOV = (ListCOV) map.get("listCOV");
        return listCOV.toString();
    }

    public String getCOV(int manId, int vacId) {
        HashMap<String, Object> map = container.get(manId);

        ListCOV listCOV = (ListCOV) map.get("listCOV");
        return listCOV.getCandidatesOnVacancy(vacId).toString();
    }

    public void putCandidateToVacancy(int candidate, int vacancy) {
        DBRequests dbRequests = new DBRequests();
        dbRequests.createDBConnect("root", "");


    }
}
