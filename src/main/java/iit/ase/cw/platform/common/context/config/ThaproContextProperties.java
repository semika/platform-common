package iit.ase.cw.platform.common.context.config;

import iit.ase.cw.platform.common.context.constant.ThaproContextConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = ThaproContextConstant.Application.THAPRO_CONTEXT_PROPERTY_PREFIX)
public class ThaproContextProperties {

    private Page page = new Page();

    @Data
    public static class Page {
        private int pageSize = 20;
    }
}
