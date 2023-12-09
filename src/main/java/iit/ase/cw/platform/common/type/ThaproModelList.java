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

package iit.ase.cw.platform.common.type;

import iit.ase.cw.platform.common.util.list.GenericThaproList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ThaproModelList<E> extends GenericThaproList<E> {

    private E entity;
    private long totalRecords;
    private int pageNumber;
    private int pageSize;

    @Override
    public String toString() {
        return new StringBuilder()
            .append(super.toString())
            .append("entity").append(null == entity ? "" : entity.toString())
            .append("totalRecords").append(totalRecords)
            .append("pageNumber").append(pageNumber)
            .append("pageSize").append(pageSize)
            .toString();
    }
}
