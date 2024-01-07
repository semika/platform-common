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

package iit.ase.cw.platform.common.converter;

import iit.ase.cw.platform.common.context.model.ThaproSearchFilter;

import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
public interface ToCollectionConverter<FROM, TO> {

    List<TO> to(List<FROM> dto, ThaproSearchFilter filter);

    static <TO> ToCollectionConverter<TO, TO> of() {
        return (parameterList, filter) -> parameterList;
    }

    static <FROM, TO> ToCollectionConverter<FROM, TO> of(ToConverter<FROM, TO> converter) {
        return (dtos, filter) -> dtos.stream().map(dto -> converter.to(dto, filter)).collect(Collectors.toList());
    }
}
