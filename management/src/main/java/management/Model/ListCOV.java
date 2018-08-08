package management.Model;

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

    public void addCandidateToVacancy(int vacancyId, int candidateId) {
        for (CandidatesOnVacancy var: candidatesOnVacancies) {
            if (var.getVacancyId() == vacancyId) {
                var.addCandidate(candidateId);
            }
        }
    }
}
