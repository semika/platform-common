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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ThaproErrorBuilder {
    private ArrayList<ThaproError> errorList = new ArrayList<>();

    public List<ThaproError> getThaproErrorList() {
        return errorList;
    }

    public static ThaproErrorBuilder builder() {
        return new ThaproErrorBuilder();
    }

    public <T> ThaproErrorBuilder addError(T type, Function<T, ThaproError> errorConverter) {
        errorList.add(errorConverter.apply(type));
        return this;
    }

    public <T> ThaproErrorBuilder addError(List<T> typeList, Function<T, ThaproError> errorConverter) {
        typeList.forEach(type -> addError(type, errorConverter));
        return this;
    }

    public ThaproErrorBuilder addError(String error) {
        addError(error, errorParam -> ThaproError.builder()
            .errorMessage(errorParam)
            .build());
        return this;
    }

    public ThaproErrorBuilder addError(String errorCode, String errorMsg) {
        ThaproError error = ThaproError.builder()
            .errorCode(errorCode)
            .errorMessage(errorMsg)
            .build();
        errorList.add(error);
        return this;
    }

    public ThaproErrorBuilder addError(Map<String, String> errorMessages) {
        if (null != errorMessages) {
            errorMessages.forEach(this::addError);
        }
        return this;
    }

//    public KalerisErrorBuilder addError(ValidationError validationError) {
//        addError(validationError, error -> KalerisError.builder()
//            .errorCode(validationError.getFieldName())
//            .errorMessage(validationError.getErrorDescription())
//            .build());
//        return this;
//    }
//
//    public KalerisErrorBuilder addError(List<ValidationError> validationErrors) {
//        if (CollectionToolkit.isListNotEmpty(validationErrors)) {
//            validationErrors.forEach(this::addError);
//        }
//        return this;
//    }
//
//    public KalerisErrorBuilder addErrors(List<String> errors) {
//        ConditionResolver.of(CollectionToolkit.isListNotEmpty(errors))
//            .ifTrue(() -> errors.forEach(this::addError))
//            .execute();
//        return this;
//    }
}
