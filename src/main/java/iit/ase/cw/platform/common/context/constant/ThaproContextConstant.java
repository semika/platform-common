package iit.ase.cw.platform.common.context.constant;

public final class ThaproContextConstant {

    private ThaproContextConstant() {
    }

    public static final class Profile {

        public static final String DEV = "dev";
        public static final String DB_DEV = "db_dev";
        public static final String QA = "qa";
        public static final String LOCAL = "local";
        public static final String QA2 = "qa2";
        public static final String STAGING = "stg";
        public static final String PROD = "prod";
        public static final String PREP = "prep";
    }

    public static final class WebConstant {

        public static final String START_ROW = "startRow";
        public static final String PAGE_SIZE = "pageSize";
        public static final String PAGE_NO = "pageNumber";
        public static final String PAGEABLE = "pageable";
        public static final String SORT_BY = "sortBy";
        public static final String SORT_ORDER = "sortOrder";
        public static final String DISTINCT_QUERY = "distinctQuery";
        public static final String MODULE = "module";
        public static final String REQUEST_FOR = "requestFor";
        public static final String AUTHENTICATED_REQUEST_HEADER = "SX-Authorized-Header";

    }

    public static final class Application {

        public static final String SYSTEM_ADMIN_USER = "SYSTEM";
        public static final Long SYSTEM_ADMIN_ORG_ID = 100000L;
        public static final String SYSTEM_ADMIN_ROAD = "ADM_ROAD";
        public static final String APPLICATION_NAME_KEY = "spring.application.name";
        public static final String THAPRO_CONTEXT_PROPERTY_PREFIX = "thapro.platform.context";
    }

}
