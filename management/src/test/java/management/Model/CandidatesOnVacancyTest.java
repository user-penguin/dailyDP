package management.Model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CandidatesOnVacancyTest {

    @Test
    public void toStringJSONTest() {
        CandidatesOnVacancy cov = new CandidatesOnVacancy();
        cov.setVacancyId(12);
        ArrayList<Integer> cand = new ArrayList<>();
        cand.add(2);
        cand.add(3);
        cov.setCandidateIds(cand);

        String expected = cov.toString();
        String actual = "{\"vacId\":12,\"canNums\":[{\"canId\":2},{\"canId\":3}]}";
        assertArrayEquals(expected.toCharArray(), actual.toCharArray());
    }
}