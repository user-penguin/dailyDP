package management.Model;

public class Candidate {
    private int canId;
    private String firstName;
    private String lastName;
    private String secondName;
    private String phone;
    private String email;
    private String skills;
    private int vacancyId;
    private int statusId;
    private int resumeId;

    public Candidate() {
    }

    public Candidate(int canId, String firstName, String lastName,
                     String secondName, String phone, String email,
                     String skills,  int vacancyId, int statusId,
                     int resumeId)
    {
        this.canId = canId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.phone = phone;
        this.email = email;
        this.skills = skills;
        this.vacancyId = vacancyId;
        this.statusId = statusId;
        this.resumeId = resumeId;
    }

    public int getCanId() {
        return canId;
    }

    public void setCanId(int canId) {
        this.canId = canId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public int getVacancyId() { return vacancyId; }

    public void setVacancyId(int vacancyId) { this.vacancyId = vacancyId; }
}
