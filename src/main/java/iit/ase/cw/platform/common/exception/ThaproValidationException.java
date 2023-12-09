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

import java.util.List;

public class ThaproValidationException extends ThaproGenericRuntimeException {

    public ThaproValidationException(String messageKey) {
        super(messageKey);
    }

    public ThaproValidationException(String messageKey, Object... parameters) {
        super(messageKey, parameters);
    }

    public ThaproValidationException(String messageKey, List<ThaproError> errorList) {
        super(messageKey, errorList);
    }

    public static ThaproValidationException of(String messageKey, Object... parameters) {
        return new ThaproValidationException(messageKey, parameters);
    }
}
