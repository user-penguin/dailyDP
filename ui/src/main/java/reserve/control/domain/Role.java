package reserve.control.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, EXPERT, MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
