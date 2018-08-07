package administration.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListManager {
    private ArrayList<Manager> managers;

    public ListManager() {managers = new ArrayList<>();}

    public void fillList(JSONArray listManagers)
    {
        if (listManagers != null) {
            JSONObject obj;
            Manager manager;
            for (int i = 0; i < listManagers.length(); i++) {
                obj = (JSONObject) listManagers.get(i);

                manager = new Manager(obj.getInt("empId"),
                        obj.getString("firstName"), obj.getString("lastName"),
                        obj.getString("secondName"), obj.getInt("idTypeAccount"),
                        obj.getInt("manId"), obj.getInt("departId"));
                managers.add(manager);
            }
        }
    }

    public Manager getManagerById(int id)
    {
        for (Manager var : managers) {
            if (var.getManId() == id)
                return var;
        }
        return null;
    }

    public void addManager(Manager manager)
    {
        for(Manager var : managers)
        {
            if (manager.getManId() == var.getManId())
                return;
        }
        managers.add(manager);
    }

    public void deleteManagerByManId(int manId) {
        for (Manager var : managers) {
            if (var.getManId() == manId)
                managers.remove(var);
        }
    }

    public void deleteManagerByEmpId(int empId) {
        for (Manager var : managers) {
            if (var.getEmpId() == empId)
                managers.remove(var);
        }
    }
}
