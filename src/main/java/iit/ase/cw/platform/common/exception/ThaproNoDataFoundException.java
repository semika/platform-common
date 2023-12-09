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

package iit.ase.cw.platform.common.exception;

import iit.ase.cw.platform.common.util.toolkit.NumberToolkit;
import iit.ase.cw.platform.common.constant.CommonMessageKeyConstants;

import java.util.List;

public class ThaproNoDataFoundException extends ThaproGenericRuntimeException {

    public ThaproNoDataFoundException() {
        super(CommonMessageKeyConstants.NO_DATA_FOUND_ERROR);
    }

    public ThaproNoDataFoundException(String type, String id) {
        super(CommonMessageKeyConstants.NO_DATA_FOUND_ERROR, type, id);
    }

    public ThaproNoDataFoundException(String messageKey, List<ThaproError> errorList) {
        super(messageKey, errorList);
    }

    public static ThaproNoDataFoundException of(String type, String id) {
        return new ThaproNoDataFoundException(type, id);
    }

    public static ThaproNoDataFoundException of(String type, Number id) {
        return new ThaproNoDataFoundException(type, NumberToolkit.longToString(id.longValue()));
    }

    public static ThaproNoDataFoundException of(Class clazz, String id) {
        return new ThaproNoDataFoundException(clazz.getCanonicalName(), id);
    }

    public static ThaproNoDataFoundException of(Class clazz, Number id) {
        return new ThaproNoDataFoundException(clazz.getCanonicalName(), NumberToolkit.longToString(id.longValue()));
    }
}
