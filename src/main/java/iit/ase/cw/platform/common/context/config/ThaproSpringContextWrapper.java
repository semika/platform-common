/*
 * ====================================================================
 * Copyright  (c) : 2021 by Kaleris. All rights reserved.
 * ====================================================================
 *
 * The copyright to the computer software herein is the property of Kaleris
 * The software may be used and/or copied only
 * with the written permission of Kaleris or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package iit.ase.cw.platform.common.context.config;

import iit.ase.cw.platform.common.context.constant.ThaproContextConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class ThaproSpringContextWrapper implements EnvironmentAware {

    private static ApplicationContext context;
    private static Environment environment;

    @Autowired
    public ThaproSpringContextWrapper(ApplicationContext context) {
        this.context = context;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public static String getEnvironmentValue(String key) {
        return environment.getProperty(key);
    }

    public static String getApplicationName() {
        return environment.getProperty(ThaproContextConstant.Application.APPLICATION_NAME_KEY);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
