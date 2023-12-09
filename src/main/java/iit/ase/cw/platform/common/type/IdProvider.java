package iit.ase.cw.platform.common.type;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IdProvider<ID> {
    ID getId();

    void setId(ID t);

    @JsonIgnore
    default String getIdProperty() {
        return "id";
    }
}
