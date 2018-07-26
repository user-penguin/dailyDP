package management.Model;

import java.util.ArrayList;

public class ListOnVacancy {
    ArrayList<Candidate> candidates;
    Vacancy vacancy;

    public ListOnVacancy() {
    }

    public ListOnVacancy(Vacancy vacancy)
    {
        this.vacancy = vacancy;
    }

    public ListOnVacancy(ArrayList<Candidate> candidates, Vacancy vacancy)
    {
        this.candidates = candidates;
        this.vacancy = vacancy;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }


    public void addCandidate(Candidate candidate)
    {
        for (Candidate var: candidates) {
            if (var.getCanId() == candidate.getCanId())
                //такое кандидат уже есть в списке
                return;
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
}
