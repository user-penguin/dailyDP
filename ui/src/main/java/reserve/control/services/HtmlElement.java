package reserve.control.services;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HtmlElement {
    private String name;
    private String classType;
    private String link;
    private boolean active;

    public void transformToMain() {
        this.name = "Main";
        this.classType = "fa fa-home";
        this.link = "/index";
        this.active = false;
    }

    public void transformToAdministrate() {
        this.name = "Administrate";
        this.classType = "fa fa-university";
        this.link = "/employees";
        this.active = false;
    }

    public void transformToManagment() {
        this.name = "Managment";
        this.classType = "fa fa-child";
        this.link = "/candidates";
        this.active = false;
    }
}

