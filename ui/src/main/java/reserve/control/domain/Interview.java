package reserve.control.domain;


import java.util.ArrayList;
import java.util.Date;

public class Interview {
    private int interviewId;
    private Candidate candidate;
    private ArrayList<Integer> experts;
    private int status;
    private String comment;
    private Date date;

    public Interview() {}

    public Interview(int interviewId, Candidate candidate, ArrayList<Integer> experts, int status, String comment) {
        this.interviewId = interviewId;
        this.candidate = candidate;
        this.experts = experts;
        this.status = status;
        this.comment = comment;
        this.date = new Date();
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public ArrayList<Integer> getExperts() {
        return experts;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void addExpert(int expId) {
        for (int i = 0; i < experts.size(); i++) {
            if (expId == experts.get(i))
                return;
        }
        experts.add(expId);
    }

    public void addExpert(ArrayList<Integer> experts) {
        for (int i = 0; i < experts.size(); i++) {
            for (int j = 0; j < this.experts.size(); j++) {
                if (experts.get(i) == this.experts.get(j)) ;
            }
            experts.add(experts.get(i));
        }
    }

    public void setDate(Date date){
        this.date = date;
    }
}
