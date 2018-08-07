package management.Model;

public class Vacancy {
    private int vacancyId;
    private int manId;
    private String title;
    private String description;
    private String requirements;
    private int statusId;

    public Vacancy() { }

    public Vacancy(int vacancyId, int manId, String title, String description, String requirements, int statusId) {
        this.vacancyId = vacancyId;
        this.manId = manId;
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.statusId = statusId;
    }

    public Vacancy(int manId, String title, String description, String requirements, int statusId) {
        this.manId = manId;
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.statusId = statusId;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    public int getManager() {
        return manId;
    }

    public void setManager(int manId) {
        this.manId = manId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
