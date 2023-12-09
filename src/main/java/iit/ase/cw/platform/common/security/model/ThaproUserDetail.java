package iit.ase.cw.platform.common.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThaproUserDetail implements Serializable {

    private String firstName;
    private String lastName;
    private String emailId;
    private String faxNumber;
    private String telephone;
    private String department;
    private String aclCode;
    private String disabledFlag;
    private String isAdminFlag;
    private String fullName;
    private String logo;
}
