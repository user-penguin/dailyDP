package management.Model;

import management.Model.Candidate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListCandidates {
    private ArrayList<Candidate> candidates;

    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    public ListCandidates() {candidates = new ArrayList<Candidate>();}

    public void fillList(JSONArray listCandidates)
    {
        if (listCandidates != null) {
            JSONObject obj;
            Candidate candidate;
            for (int i = 0; i < listCandidates.length(); i++) {
                obj = (JSONObject) listCandidates.get(i);

                candidate = new Candidate(obj.getInt("id"), obj.getString("firstName"), obj.getString("lastName"), obj.getString("secondName"), obj.getString("phone"),
                obj.getString("email"), obj.getString("skills"), obj.getInt("statusId"), obj.getInt("resumeId"));
                candidates.add(candidate);
            }
        }
    }

    public Candidate getCandidateById(int id)
    {
        for (Candidate var: candidates) {
            if (var.getCanId() == id)
                return var;
        }
        return null;
    }

    public boolean addCandidate(Candidate candidate)
    {
        for (Candidate var: candidates) {
            if (var.getCanId() == candidate.getCanId())
                return false;
        }
        candidates.add(candidate);
        return true;
    }

}
