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

public class ThaproJpaAllowedException extends ThaproException {

    public ThaproJpaAllowedException() {
        super(CommonMessageKeyConstants.JPA_ALLOWED_EXCEPTION);
    }
}
