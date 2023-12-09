package iit.ase.cw.platform.common.context.model;

import iit.ase.cw.platform.common.security.model.ThaproUser;

public final class ThaproApplicationContext {

    private static ThreadLocal<CurrentContext> applicationContext = new ThreadLocal<>();

    public static void configureContextUser(ThaproUser currentContextUser) {
        CurrentContext currentContext = applicationContext.get();
        if (currentContext != null) {
            currentContext.setUser(currentContextUser);
        } else {
            currentContext = CurrentContext.builder().user(currentContextUser).build();
            applicationContext.set(currentContext);
        }
    }

    public static CurrentContext getCurrentContext() {
        return applicationContext.get();
    }

    public static ThaproUser getContextUser() {
        ThaproUser contextUser = getCurrentContext().getUser();

        if (contextUser == null) {
            //return admin user
            contextUser =  ThaproUser.builder().userId("SYSTEM").build();
        }

        return contextUser;
    }

    public static void clearContext() {
        applicationContext.remove();
    }
}
