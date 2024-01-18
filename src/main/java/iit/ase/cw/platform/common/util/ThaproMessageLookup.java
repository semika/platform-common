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

package iit.ase.cw.platform.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ThaproMessageLookup {

    private static final Object[] EMPTY_ARRAY = {""};

    private final ReloadableResourceBundleMessageSource resourceBundle;

    @Autowired
    public ThaproMessageLookup(ReloadableResourceBundleMessageSource resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public String getMessage(Locale locale, String messageKey, Object... params) {
        return resourceBundle.getMessage(messageKey, params, locale);
    }

    public String getMessage(String messageKey, Object... params) {
        return getMessage(Locale.getDefault(), messageKey, params);
    }

    public String getMessage(Locale locale, String messageKey) {
        return resourceBundle.getMessage(messageKey, EMPTY_ARRAY, locale);
    }

    public String getMessage(String messageKey) {
        return getMessage(Locale.getDefault(), messageKey, EMPTY_ARRAY);
    }
}
