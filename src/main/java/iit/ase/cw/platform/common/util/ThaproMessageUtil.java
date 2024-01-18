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

import iit.ase.cw.platform.common.context.config.ThaproSpringContextWrapper;

import java.util.Locale;

public final class ThaproMessageUtil {

    private static final ThaproMessageLookup KALERIS_MESSAGE_LOOKUP;

    private ThaproMessageUtil() {
    }

    public static String resolveMessage(Locale locale, String messageKey, Object... params) {
        return KALERIS_MESSAGE_LOOKUP.getMessage(locale, messageKey, params);
    }

    public static String resolveMessage(String messageKey, Object... params) {
        return KALERIS_MESSAGE_LOOKUP.getMessage(messageKey, params);
    }

    public static String resolveMessage(String messageKey) {
        return KALERIS_MESSAGE_LOOKUP.getMessage(messageKey);
    }

    static {
        KALERIS_MESSAGE_LOOKUP = ThaproSpringContextWrapper.getBean(ThaproMessageLookup.class);
    }

}
