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

                candidate = new Candidate(obj.getInt("id"), obj.getString("firstName"),
                        obj.getString("lastName"), obj.getString("secondName"),
                        obj.getString("phone"), obj.getString("email"),
                        obj.getString("skills"), obj.getInt("managerId"),
                        obj.getInt("statusId"), obj.getInt("resumeId"));
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

    public void addCandidate(Candidate candidate)
    {
        for (Candidate var: candidates) {
            if (var.getCanId() == candidate.getCanId())
                candidates.remove(var);
        }
        candidates.add(candidate);
    }

    public String toString() {
        JSONArray array = new JSONArray();
        for (Candidate candidate : candidates){
            JSONObject object = new JSONObject();
            object.put("canId", candidate.getCanId());
            object.put("firstName", candidate.getFirstName());
            object.put("lastName", candidate.getLastName());
            object.put("secondName", candidate.getSecondName());
            object.put("phone", candidate.getPhone());
            object.put("email", candidate.getEmail());
            object.put("skills", candidate.getSkills());
            object.put("managerId", candidate.getManagerId());
            object.put("statusId", candidate.getStatusId());
            object.put("resumeId", candidate.getResumeId());
            array.put(object);
        }
        return array.toString();
    }
}
