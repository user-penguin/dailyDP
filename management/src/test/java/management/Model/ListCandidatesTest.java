package management.Model;

import org.junit.Test;

public class ListCandidatesTest {

    @Test
    public void checkAddCandidateMustReturnTrue() {
        ListCandidates listCandidates = new ListCandidates();
        assertEquals(listCandidates.addCandidate(
                new Candidate(1,"fname","lname","sname", "phone", "email", "skills", 2, 2)
        ), true);

    }
}