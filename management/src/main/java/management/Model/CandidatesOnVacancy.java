package management.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CandidatesOnVacancy {
    private int vacancyId;
    private ArrayList<Integer> candidateIds;

    public CandidatesOnVacancy(int vacancyId, ArrayList<Integer> candidateIds) {
        this.vacancyId = vacancyId;
        this.candidateIds = candidateIds;
    }

    public CandidatesOnVacancy() {
        this.candidateIds = new ArrayList<>();
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    public ArrayList<Integer> getCandidateIds() {
        return candidateIds;
    }

    public void setCandidateIds(ArrayList<Integer> candidateIds) {
        this.candidateIds = candidateIds;
    }

    @Override
    public String toString() {
        JSONArray var1 = new JSONArray();
        JSONObject object = new JSONObject();
        object.put("vacId", vacancyId);
        var1.put(object);

        JSONArray var2 = new JSONArray();
        for(int i = 0; i < candidateIds.size(); i++) {
            JSONObject var3 = new JSONObject();
            var3.put("canId", candidateIds.get(i));
            var2.put(var3);
        }
        var1.put(var2);
        return var1.toString();
    }

    public void addCandidate(int canId) {
        for(int i = 0; i < candidateIds.size(); i++) {
            if (candidateIds.get(i) == canId)
                return;
        }
        candidateIds.add(canId);
    }

    public void fillList(JSONArray list) {
        JSONObject obj;
        for (int i = 0; i < list.length(); i++) {
            obj = (JSONObject) list.get(i);
            vacancyId = obj.getInt("vacId");
            candidateIds.add(obj.getInt("canId"));
        }
    }
}
