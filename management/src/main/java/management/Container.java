package management;

import management.Model.*;
import management.dbTools.DBRequests;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Container {
    private Map<String, Object> container;
    private int manId;

    public Container() { container = new HashMap<>(); }
    public Container(int manId) {
        container = new HashMap<>();
        this.manId = manId;
    }

    public void init() {
        DBRequests dbRequests = new DBRequests();
        dbRequests.createDBConnect("root", "");

        ListCandidates listCandidates = new ListCandidates();
        listCandidates.fillList(dbRequests.getArrOfCandidatesFromDB(manId));

        container.put("listCan", listCandidates);

        ListVacancies listVacancies = new ListVacancies();
        listVacancies.fillList(dbRequests.getArrOfVacanciesFromDB(manId));

        container.put("listVac", listVacancies);
    }

    private void putCandidateToContainer(Candidate candidate) {
        ListCandidates listCandidates = (ListCandidates) container.get("listCan");
        listCandidates.addCandidate(candidate);
        container.put("listCan", listCandidates);
    }

    public void putCandidate(String jsonEmpData) {
        JSONObject empData = new JSONObject(jsonEmpData);
        Candidate candidate = new Candidate(empData.getString("firstName"),
                empData.getString("lastName"), empData.getString("secondName"),
                empData.getString("phone"), empData.getString("email"),
                empData.getString("skills"), empData.getInt("vacancyId"),
                empData.getInt("statusId"), empData.getInt("resumeId"));

        DBRequests dbRequests = new DBRequests();
        dbRequests.createDBConnect("root", "");

        dbRequests.putCandidate(candidate);
        candidate.setCanId(dbRequests.getCandidateId(candidate));
        putCandidateToContainer(candidate);
    }

    private void putVacancyToContainer(Vacancy vacancy) {
        ListVacancies listVacancies = (ListVacancies) container.get("listVac");
        listVacancies.addVacancy(vacancy);
        container.put("listVac", listVacancies);
    }

    public void putVacancy(String jsonEmpData) {
        JSONObject empData = new JSONObject(jsonEmpData);
        Vacancy vacancy = new Vacancy(empData.getInt("manId"), empData.getString("title"),
                empData.getString("description"), empData.getString("requirements"),
                empData.getInt("statusId"));

        DBRequests dbRequests = new DBRequests();
        dbRequests.createDBConnect("root", "");

        dbRequests.putVacancy(vacancy);
        vacancy.setVacancyId(dbRequests.getVacancyId(vacancy));
        putVacancyToContainer(vacancy);
    }
}
