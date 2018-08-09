package management.Model;

import management.dbTools.DBRequests;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListCOV {
    private ArrayList<CandidatesOnVacancy> candidatesOnVacancies;

    public ListCOV(ArrayList<CandidatesOnVacancy> candidatesOnVacancies) {
        this.candidatesOnVacancies = candidatesOnVacancies;
    }

    public ListCOV() {
        this.candidatesOnVacancies = new ArrayList<>();
    }

    public ArrayList<CandidatesOnVacancy> getCandidatesOnVacancies() {
        return candidatesOnVacancies;
    }

    public void setCandidatesOnVacancies(ArrayList<CandidatesOnVacancy> candidatesOnVacancies) {
        this.candidatesOnVacancies = candidatesOnVacancies;
    }

    public boolean addCandidateToVacancy(int vacancyId, int candidateId) {
        for (CandidatesOnVacancy var: candidatesOnVacancies) {
            if (var.getVacancyId() == vacancyId) {
                return var.addCandidate(candidateId);
            }
        }
        return false;
    }

    public void fillList(ListVacancies listVacancies) {
        DBRequests dbRequests = new DBRequests();
        dbRequests.createDBConnect("root", "");

        ArrayList<Vacancy> vacancies = listVacancies.getVacancies();
        for(Vacancy vacancy : vacancies) {
            CandidatesOnVacancy cov = new CandidatesOnVacancy();
            cov.fillList(dbRequests.getCOV(vacancy.getVacancyId()));
            this.candidatesOnVacancies.add(cov);
        }
    }

    public CandidatesOnVacancy getCandidatesOnVacancy(int vacancyId) {
        for (CandidatesOnVacancy var : candidatesOnVacancies) {
            if (var.getVacancyId() == vacancyId)
                return var;
        }
        return null;
    }
}
