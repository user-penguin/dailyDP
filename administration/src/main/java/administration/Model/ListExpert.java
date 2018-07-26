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
                        obj.getInt("expId"), obj.getInt("departId"),
                        obj.getString("departName"));
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
}
