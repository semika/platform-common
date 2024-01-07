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

@FunctionalInterface
public interface ToConverter<FROM, TO> {

    TO to(FROM from, ThaproSearchFilter searchFilter);

    static <TO> ToConverter<TO, TO> toConverter() {
        return (from, searchFilter) -> from;
    }
}
