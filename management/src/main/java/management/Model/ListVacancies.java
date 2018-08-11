package management.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListVacancies {
    ArrayList<Vacancy> vacancies;

    public ListVacancies() { vacancies = new ArrayList<>(); }

    public void fillList(JSONArray list) {
        JSONObject obj;
        Vacancy vacancy;
        for (int i = 0; i < list.length(); i++) {
            obj = (JSONObject) list.get(i);

            vacancy = new Vacancy(obj.getInt("id"), obj.getInt("manId"), obj.getString("title"),
                    obj.getString("description"), obj.getString("requirements"),
                    obj.getInt("statusId"));
            vacancies.add(vacancy);
        }
    }

    public void addVacancy(Vacancy vacancy) {
        for (Vacancy var : vacancies)
            if (var.getVacancyId() == vacancy.getVacancyId())
                vacancies.remove(var);
        vacancies.add(vacancy);
    }

    public ArrayList<Vacancy> getVacancies() {
        return vacancies;
    }

    public String toString() {
        JSONArray array = new JSONArray();
        for (Vacancy vacancy : vacancies){
            JSONObject object = new JSONObject();
            object.put("vacancyId", vacancy.getVacancyId());  //integer
            object.put("manId", vacancy.getManager());
            object.put("title", vacancy.getTitle());
            object.put("description", vacancy.getDescription());
            object.put("requirements", vacancy.getRequirements());
            object.put("statusId", vacancy.getStatusId());
            array.put(object);
        }
        return array.toString();
    }
}
