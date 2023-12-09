package iit.ase.cw.platform.common.context.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import iit.ase.cw.platform.common.security.model.ThaproUser;
import iit.ase.cw.platform.common.type.SortOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Locale;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ThaproSearchFilter {

    private static final long serialVersionUID = 5977026714753531665L;

    private int pageNo;
    private int startRow;
    private int endRow;
    private int pageSize;
    private String requestFor;
    private List<String> sortFields;
    @Builder.Default
    private SortOrder sortOrder = SortOrder.ASC;
    @Builder.Default
    private boolean pageable = true;
    @Builder.Default
    private boolean distinctQuery = true;

    private ThaproUser thaproUser;
    private String module;
    @Builder.Default
    private Locale locale = Locale.getDefault();

    @JsonIgnore
    public int getCalculatedPageSize() {
        return pageable ? pageSize : Integer.MAX_VALUE;
    }

    @JsonIgnore
    public int getCalculatedPageNo() {
        return pageable ? pageNo : 0;
    }

}
