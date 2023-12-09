package iit.ase.cw.platform.common.security.model;

import org.springframework.util.Assert;

import java.io.Serializable;

public class ThaproRole implements Serializable {

    private String role;

    public ThaproRole() {
    }

    public ThaproRole(String role) {
        Assert.notNull(role, "Thapro Role Should not be null. ");
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
