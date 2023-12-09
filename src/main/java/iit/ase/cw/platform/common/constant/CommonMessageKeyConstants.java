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

package iit.ase.cw.platform.common.constant;

/**
 * Constants for all the common exceptions for all the micro-services.
 */
public interface CommonMessageKeyConstants {

    String SERVER_ERROR = "SERVER_ERROR";
    String CONTENT_TYPE_CONVERSION_ERROR = "CONTENT_TYPE_CONVERSION_ERROR";
    String UNSUPPORTED_METHOD_ERROR = "UNSUPPORTED_METHOD_ERROR";
    String UNSUPPORTED_FEATURE_ERROR = "UNSUPPORTED_FEATURE_ERROR";
    String ARGUMENT_NOT_VALID_ERROR = "ARGUMENT_NOT_VALID_ERROR";
    String INVALID_METHOD_ARGUMENT_ERROR = "INVALID_METHOD_ARGUMENT_ERROR";
    String DATA_ACCESS_ERROR = "DATA_ACCESS_ERROR";
    String NON_UNIQUE_ERROR = "NON_UNIQUE_ERROR";
    String NO_DATA_FOUND_ERROR = "NO_DATA_FOUND_ERROR";
    String NO_DATA_FOUND_WARNING = "NO_DATA_FOUND_WARNING";
    String OBJECT_CONVERSION_ERROR = "OBJECT_CONVERSION_ERROR";
    String VALIDATION_FAILED_ERROR = "VALIDATION_FAILED_ERROR";

    String GET_OPERATION_SUCCESS = "GET_OPERATION_SUCCESS";
    String GET_OPERATION_BY_ID_SUCCESS = "GET_OPERATION_BY_ID_SUCCESS";
    String POST_OPERATION_SUCCESS = "POST_OPERATION_SUCCESS";
    String PUT_OPERATION_SUCCESS = "PUT_OPERATION_SUCCESS";
    String DELETE_OPERATION_SUCCESS = "DELETE_OPERATION_SUCCESS";
    String MISSING_OR_INVALID_REQUEST_FIELDS = "MISSING_OR_INVALID_REQUEST_FIELDS";

    String JPA_ALLOWED_EXCEPTION = "JPA_ALLOWED_EXCEPTION";
}
