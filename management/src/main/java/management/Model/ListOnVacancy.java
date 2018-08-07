package management.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListOnVacancy {
    private ArrayList<Candidate> candidates;
    private int vacancyId;

    public ListOnVacancy() {
    }

    public ListOnVacancy(int vacancyId)
    {
        this.vacancyId = vacancyId;
    }

    public ListOnVacancy(ArrayList<Candidate> candidates, int vacancyId)
    {
        this.candidates = candidates;
        this.vacancyId = vacancyId;
    }

    public int getVacancy() {
        return vacancyId;
    }


    public void addCandidate(Candidate candidate)
    {
        for (Candidate var: candidates) {
            if (var.getCanId() == candidate.getCanId())
                //такое кандидат уже есть в списке
                candidates.remove(var);
        }
        candidates.add(candidate);
    }

    public Candidate getCandidateById(Long id)
    {
        for (Candidate var: candidates) {
            if (var.getCanId() == id)
                return var;
        }
        return null;
    }

    public void addCandidate(ArrayList<Candidate> candidates)
    {
        for (Candidate var: candidates) {
            for (Candidate reserve: this.candidates) {
                if (var.getCanId() == reserve.getCanId()) ;
            }
            candidates.add(var);
        }
    }

    public void fillList(JSONArray list) {
        JSONObject obj;
        Candidate candidate;
        for (int i = 0; i < list.length(); i++) {
            obj = (JSONObject) list.get(i);

            candidate = new Candidate(obj.getInt("id"), obj.getString("firstName"),
                    obj.getString("lastName"), obj.getString("secondName"),
                    obj.getString("phone"), obj.getString("email"),
                    obj.getString("skills"), obj.getInt("vacancyId"),
                    obj.getInt("statusId"), obj.getInt("resumeId"));
            candidates.add(candidate);
        }
    }
}
