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

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ThaproException extends RuntimeException {

    private String messageKey;
    private Object[] parameters = new Object[0];
    private List<ThaproError> errorList = new ArrayList<ThaproError>();

    public ThaproException() {

    }

    public ThaproException(String messageKey) {
        super(messageKey);
        this.messageKey = messageKey;
    }

    public ThaproException(String messageKey, Object... parameters) {
        super(messageKey);
        this.messageKey = messageKey;
        this.parameters = parameters;
    }

    public ThaproException(String messageKey, List<ThaproError> errorList,
                           Object... parameters) {
        super(messageKey);
        this.messageKey = messageKey;
        this.errorList = errorList;
        this.parameters = parameters;
    }

    public ThaproException(String messageKey, List<ThaproError> errorList) {
        super(messageKey);
        this.messageKey = messageKey;
        this.errorList = errorList;
    }

}
