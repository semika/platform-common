package iit.ase.cw.platform.common.context.model;

import iit.ase.cw.platform.common.security.model.ThaproUser;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.util.Locale;

@Data
@Builder
public class CurrentContext implements Serializable {

    private ThaproUser user;

    @Setter(AccessLevel.PROTECTED)
    private Locale locale;

    protected void setUser(ThaproUser thaproUser) {
        this.user = thaproUser;
    }

}
