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

package iit.ase.cw.platform.common.exception.handler;

import iit.ase.cw.platform.common.constant.CommonMessageKeyConstants;
import iit.ase.cw.platform.common.context.config.ThaproSpringContextWrapper;
import iit.ase.cw.platform.common.exception.*;
import iit.ase.cw.platform.common.security.constant.ThaproSecurityConstant;
import iit.ase.cw.platform.common.util.ThaproMessageLookup;
import iit.ase.cw.platform.common.util.ThaproMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.Date;

@ControllerAdvice
@Slf4j
public class ThaproExceptionHandler {

    @Value("${Thapro.platform.security.method.enable:false}")
    private boolean methodSecurityEnabled;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ThaproErrorResponse> handleAllException(Exception exception, WebRequest webRequest) {
        log.error("ThaproExceptionHandler:handleAllException", exception);
        if (methodSecurityEnabled && exception instanceof ThaproUnAuthorizedException) {
            ThaproUnAuthorizedException unAuthorizedException = (ThaproUnAuthorizedException) exception;
            log.error("ThaproUnAuthorizedException:unAuthorizedException", exception);
            ThaproMessageLookup messageLookup = ThaproSpringContextWrapper.getBean(ThaproMessageLookup.class);
            String resolvedMessage = messageLookup.getMessage(ThaproSecurityConstant.MessageKey.UN_AUTHORIZED_USER);
            String message = String.format("%s - Module [%s] - For User - [%s]",
                resolvedMessage, unAuthorizedException.getModule(), unAuthorizedException.getUser());
            ThaproErrorResponse thaproErrorResponse = ThaproErrorResponse.builder()
                .time(new Date())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .message(message)
                .build();
            return new ResponseEntity<>(thaproErrorResponse, HttpStatus.UNAUTHORIZED);

        } else {
            ThaproErrorResponse thaproErrorResponse = ThaproErrorResponse.builder()
                .time(new Date())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(webRequest.getDescription(false))
                .build();
            return new ResponseEntity<>(thaproErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ThaproErrorResponse> handleInvalidMethodArgumentException(
        MethodArgumentTypeMismatchException exception, WebRequest webRequest) {
        log.error("ThaproExceptionHandler:handleInvalidMethodArgumentException", exception);
        String message = ThaproMessageUtil.resolveMessage(
            webRequest.getLocale(), CommonMessageKeyConstants.ARGUMENT_NOT_VALID_ERROR,
            webRequest.getDescription(false));
        ThaproErrorResponse thaproErrorResponse = ThaproErrorResponse.builder()
            .time(new Date())
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .message(message)
            .build();
        return new ResponseEntity<>(thaproErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ThaproErrorResponse> handleNotValidArgumentException(
        MethodArgumentNotValidException exception, WebRequest webRequest) {
        log.error("ThaproExceptionHandler:handleNotValidArgumentException", exception);
        String message = ThaproMessageUtil.resolveMessage(
            webRequest.getLocale(), CommonMessageKeyConstants.INVALID_METHOD_ARGUMENT_ERROR,
            webRequest.getDescription(false));
        ThaproErrorResponse thaproErrorResponse = ThaproErrorResponse.builder()
            .time(new Date())
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .message(message)
            .build();
        return new ResponseEntity<>(thaproErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
        ThaproException.class,
        ThaproGenericException.class
    })
    public final <E extends ThaproException> ResponseEntity<ThaproErrorResponse> handleValidationException(
        E exception, WebRequest webRequest) {
        log.error("ThaproExceptionHandler", exception);
        //String message = ThaproMessageUtil.resolveMessage(webRequest.getLocale(),
        //    exception.getMessageKey(), exception.getParameters());

        String message = "test";
        ThaproErrorResponse thaproErrorResponse = ThaproErrorResponse.builder()
            .time(new Date())
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(message == null ? exception.getMessageKey() : message)
            .errors(exception.getErrorList())
            .build();

        return new ResponseEntity<>(thaproErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
            ThaproRuntimeException.class
    })
    public final <E extends ThaproRuntimeException> ResponseEntity<ThaproErrorResponse> handleBadRequests(
        E exception, WebRequest webRequest) {
        log.error("handleBadRequests", exception);
//        String message = ThaproMessageUtil.resolveMessage(webRequest.getLocale(),
//            exception.getMessageKey(), exception.getParameters());

        String message = "test";
        ThaproErrorResponse thaproErrorResponse = ThaproErrorResponse.builder()
            .time(new Date())
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .message(message == null ? exception.getMessageKey() : message)
            .errors(exception.getErrorList())
            .build();

        return new ResponseEntity<>(thaproErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
        ThaproNoDataFoundException.class
    })
    public final <E extends ThaproRuntimeException> ResponseEntity<ThaproErrorResponse> handleNoDataFoundErrors(
        E exception, WebRequest webRequest) {
        log.error("handleNoDataFoundErrors", exception);
//        String message = ThaproMessageUtil.resolveMessage(webRequest.getLocale(),
//            exception.getMessageKey(), exception.getParameters());
        String message = "test";
        ThaproErrorResponse thaproErrorResponse = ThaproErrorResponse.builder()
            .time(new Date())
            .statusCode(HttpStatus.NOT_FOUND.value())
            .message(message == null ? exception.getMessageKey() : message)
            .errors(exception.getErrorList())
            .build();

        return new ResponseEntity<>(thaproErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
        ThaproJpaAllowedException.class
    })
    public final <E extends ThaproRuntimeException> ResponseEntity<ThaproErrorResponse> handleExpectedErrors(
        E exception, WebRequest webRequest) {
        log.error("handleExpectedErrors", exception);
        String message = ThaproMessageUtil.resolveMessage(webRequest.getLocale(),
            exception.getMessageKey(), exception.getParameters());

        ThaproErrorResponse thaproErrorResponse = ThaproErrorResponse.builder()
            .time(new Date())
            .statusCode(HttpStatus.EXPECTATION_FAILED.value())
            .message(message == null ? exception.getMessageKey() : message)
            .errors(exception.getErrorList())
            .build();

        return new ResponseEntity<>(thaproErrorResponse, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler({
        ThaproNotSupportedException.class,
        ThaproNotSupportedFeatureException.class,
    })
    public final <E extends ThaproRuntimeException> ResponseEntity<ThaproErrorResponse> handleNotImplementedErrors(
        E exception, WebRequest webRequest) {
        log.error("handleNotImplementedErrors", exception);
        String message = ThaproMessageUtil.resolveMessage(webRequest.getLocale(),
            exception.getMessageKey(), exception.getParameters());

        ThaproErrorResponse thaproErrorResponse = ThaproErrorResponse.builder()
            .time(new Date())
            .statusCode(HttpStatus.NOT_IMPLEMENTED.value())
            .message(message == null ? exception.getMessageKey() : message)
            .errors(exception.getErrorList())
            .build();

        return new ResponseEntity<>(thaproErrorResponse, HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler({
        ThaproGenericRuntimeException.class,
        ThaproValidationException.class
    })
    public final <E extends ThaproRuntimeException> ResponseEntity<ThaproErrorResponse> handleBadRequestErrors(
        E exception, WebRequest webRequest) {
        log.error("ThaproRuntimeExceptionHandler", exception);
        String message = ThaproMessageUtil.resolveMessage(webRequest.getLocale(),
            exception.getMessageKey(), exception.getParameters());

        ThaproErrorResponse thaproErrorResponse = ThaproErrorResponse.builder()
            .time(new Date())
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .message(message == null ? exception.getMessageKey() : message)
            .errors(exception.getErrorList())
            .build();

        return new ResponseEntity<>(thaproErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UndeclaredThrowableException.class)
    public final ResponseEntity<ThaproErrorResponse> handleUndeclaredThrowableException(
        UndeclaredThrowableException exception, WebRequest webRequest) {
        if (exception.getUndeclaredThrowable() instanceof ThaproGenericException) {
            return handleValidationException((ThaproGenericException) exception.getUndeclaredThrowable(), webRequest);
        } else {
            return handleAllException((Exception) exception.getUndeclaredThrowable(), webRequest);
        }
    }
}
