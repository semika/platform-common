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

import iit.ase.cw.platform.common.constant.CommonMessageKeyConstants;

import java.util.List;

public class ThaproNotSupportedException extends ThaproGenericRuntimeException {

    public ThaproNotSupportedException() {
        super(CommonMessageKeyConstants.UNSUPPORTED_FEATURE_ERROR, "Not Supported Feature");
    }

    public ThaproNotSupportedException(String parameter) {
        super(CommonMessageKeyConstants.UNSUPPORTED_FEATURE_ERROR, parameter);
    }

    public ThaproNotSupportedException(String messageKey, List<ThaproError> errorList) {
        super(messageKey, errorList);
    }

    public static ThaproNotSupportedException of(String parameter) {
        return new ThaproNotSupportedException(parameter);
    }
}
