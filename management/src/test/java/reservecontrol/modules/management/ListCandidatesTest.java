package reservecontrol.modules.management;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListCandidatesTest {

    @Test
    public void checkAddCandidateMustReturnTrue() {
        ListCandidates listCandidates = new ListCandidates();
        assertEquals(listCandidates.addCandidate(
                new Candidate(1,"fname","lname","sname", "phone", "email", "skills", 2, 2)
        ), true);

    }
}