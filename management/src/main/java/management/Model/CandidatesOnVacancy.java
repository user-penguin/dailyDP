package management.Model;

import lombok.Data;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

@Data
@Setter
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
        JSONObject COV = new JSONObject();
        COV.put("vacId", vacancyId);

        JSONObject canNumbers = new JSONObject();
        JSONArray numbers = new JSONArray();
        for(int i = 0; i < candidateIds.size(); i++) {
            JSONObject number = new JSONObject();
            number.put("canId", candidateIds.get(i));
            numbers.put(number);
        }
        COV.put("canNums", numbers);
        return COV.toString();
    }

    public boolean addCandidate(int canId) {
        for(int i = 0; i < candidateIds.size(); i++) {
            if (candidateIds.get(i) == canId)
                return false;
        }
        candidateIds.add(canId);
        return true;
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
