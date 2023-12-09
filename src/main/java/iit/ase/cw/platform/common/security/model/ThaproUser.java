package iit.ase.cw.platform.common.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThaproUser implements Serializable {

    private String userId;
    private long organizationId;
    private String organiationName;
    @JsonIgnore
    private String password;
    private ThaproUserDetail userDetail;

    @Singular
    private List<ThaproRole> roles;

}
