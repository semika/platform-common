package iit.ase.cw.platform.common.security.constant;

public final class ThaproSecurityConstant {

    public static final class Security {
        public static final String SECURITY_CLIENT_ENABLED = "security.client.enable";
        public static final String SECURITY_SERVER_ENABLED  = "security.server.enable";
        public static final String SECURITY_CLIENT_BASIC_AUTH_ENABLED = "security.client.basicAuth.enable";
        public static final String SECURITY_CLIENT_JWT_AUTH_ENABLED = "security.client.jwtAuth.enable";
        public static final String THAPRO_USER_CLAIM = "thapro-user";
        public static final String THAPRO_CLIENT_JWT = "thapro-client-jwt";
    }

    public static final class Header {
        public static final String AUTHORIZATION_HEADER = "Authorization";
        public static final String BEARER_TOKEN = "Bearer";
        public static final String BASIC_HEADER = "Basic";
        public static final String THAPRO_AUTHENTICATED_HEADER = "Thapro-Authenticated-Header";
        public static final String KALERIS_AUTHORIZED_HEADER = "KLS-Authorized-Header";
        public static final String COOKIE_HEADER = "Cookie";
        public static final String SSO_ID_HEADER = "KLS-SSO-Id";
    }
}
