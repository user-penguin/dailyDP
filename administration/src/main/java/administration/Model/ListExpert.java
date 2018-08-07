package administration.Model;

import administration.Model.Expert;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListExpert {
    private ArrayList<Expert> experts;

    public ListExpert() {experts = new ArrayList<>();}

    public void fillList(JSONArray listExperts)
    {
        if (listExperts != null) {
            JSONObject obj;
            Expert expert;
            for (int i = 0; i < listExperts.length(); i++) {
                obj = (JSONObject) listExperts.get(i);

                expert = new Expert(obj.getInt("empId"),
                        obj.getString("firstName"), obj.getString("lastName"),
                        obj.getString("secondName"), obj.getInt("idTypeAccount"),
                        obj.getInt("expId"), obj.getInt("departId"));
                experts.add(expert);
            }
        }
    }

    public Expert getExpertById(int id)
    {
        for (Expert var : experts) {
            if (var.getExId() == id)
                return var;
        }
        return null;
    }

    public void addExpert(Expert expert)
    {
        for(Expert var : experts)
        {
            if (expert.getExId() == var.getExId())
                return;
        }
        experts.add(expert);
    }

    public void deleteExpertByExpId(int expId) {
        for (Expert var : experts) {
            if (var.getExId() == expId)
                experts.remove(var);
        }
    }

    public void deleteExpertByEmpId(int empId) {
        for (Expert var : experts) {
            if (var.getEmpId() == empId)
                experts.remove(var);
        }
    }
}
