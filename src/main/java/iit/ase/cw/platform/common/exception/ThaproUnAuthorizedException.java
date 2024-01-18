/*
 * ====================================================================
 * Copyright  (c) : 2021 by Thapro. All rights reserved.
 * ====================================================================
 *
 * The copyright to the computer software herein is the property of Thapro
 * The software may be used and/or copied only
 * with the written permission of Thapro or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package iit.ase.cw.platform.common.exception;

import iit.ase.cw.platform.common.constant.ThaproGlobalConstant;
import iit.ase.cw.platform.common.security.constant.ThaproSecurityConstant;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ThaproUnAuthorizedException extends RuntimeException {

    private String messageKey;
    private String module;
    private String user;
    private String message;
    private HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    public ThaproUnAuthorizedException(String module, String message, String user) {
        super(ThaproSecurityConstant.MessageKey.UN_AUTHORIZED_USER);
        this.messageKey = ThaproSecurityConstant.MessageKey.UN_AUTHORIZED_USER;
        this.module = module;
        this.user = user;
        this.message = message;
    }

    public static ThaproUnAuthorizedException of(String module, String message, String user) {
        return new ThaproUnAuthorizedException(module, message, user);
    }

    public static ThaproUnAuthorizedException of(String module, String message) {
        return new ThaproUnAuthorizedException(module, message, ThaproGlobalConstant.Symbol.EMPTY_STR);
    }

    public static ThaproUnAuthorizedException of(String message) {
        return new ThaproUnAuthorizedException(ThaproGlobalConstant.Symbol.EMPTY_STR, message,
                ThaproGlobalConstant.Symbol.EMPTY_STR);
    }

    public static ThaproUnAuthorizedException of() {
        return new ThaproUnAuthorizedException(ThaproGlobalConstant.Symbol.EMPTY_STR,
            ThaproGlobalConstant.Symbol.EMPTY_STR, ThaproGlobalConstant.Symbol.EMPTY_STR);
    }
}
