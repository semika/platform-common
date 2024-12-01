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

package iit.ase.cw.platform.common.service;

import iit.ase.cw.platform.common.context.model.ThaproSearchFilter;
import iit.ase.cw.platform.common.exception.ThaproNoDataFoundException;
import iit.ase.cw.platform.common.exception.ThaproNotSupportedException;
import iit.ase.cw.platform.common.exception.ThaproValidationException;
import iit.ase.cw.platform.common.type.ThaproModel;
import iit.ase.cw.platform.common.type.ThaproResponse;
import java.util.List;
import java.util.Map;

public interface BaseThaproAbstractService<DTO extends ThaproModel, I> {

    default ThaproResponse<DTO> findAll(ThaproSearchFilter filter) {
        throw ThaproNotSupportedException.of(String.format("findAll:%s", filter.getModule()));
    }

    default ThaproResponse<DTO> findById(I id, ThaproSearchFilter filter) {
        throw ThaproNotSupportedException.of(String.format("findById:%s", filter.getModule()));
    }

    default ThaproResponse<DTO> findByRequestFor(String requestFor, DTO dto, ThaproSearchFilter searchFilter) {
        throw ThaproNotSupportedException.of(String.format("findByRequestFor:%s", searchFilter.getModule()));
    }

    default ThaproResponse<DTO> findByResourceRequestFor(String requestFor, DTO dto,
                                                        ThaproSearchFilter searchFilter) {
        throw ThaproNotSupportedException.of(String.format("findByResourceRequestFor:%s", searchFilter.getModule()));
    }

    default ThaproResponse<DTO> advanceSearch(String searchParameters, ThaproSearchFilter searchFilter) {
        throw ThaproNotSupportedException.of(String.format("advanceSearch:%s", searchFilter.getModule()));
    }

    default ThaproResponse<DTO> resolveDefaultLookups(String requestFor, List<String> requestAttrs, String searchParam,
                                                     ThaproSearchFilter searchFilter) {
        throw ThaproNotSupportedException.of(String.format("resolveLookups:%s", searchFilter.getModule()));
    }

    default <X> ThaproResponse<X> resolveLookups(String requestFor, List<String> requestAttrs, String searchParam,
                                                  ThaproSearchFilter searchFilter) {
        throw ThaproNotSupportedException.of(String.format("resolveLookupsTwo:%s", searchFilter.getModule()));
    }

//    default ThaproResponse<KalerisLookup<I>> resolveCustomLookups(String requestFor, String params,
//                                                                   Map<String, String> postData,
//                                                                   ThaproSearchFilter searchFilter) {
//        throw ThaproNotSupportedException.of(String.format("resolveCustomLookups:%s", searchFilter.getModule()));
//    }
//
//    default ThaproResponse<KalerisGroupLookup<I>> resolveLookupGroups(String requestFor, String params,
//                                                                       Map<String, String> postData,
//                                                                       ThaproSearchFilter searchFilter) {
//        throw ThaproNotSupportedException.of(String.format("resolveLookupGroups:%s", searchFilter.getModule()));
//    }

    default ThaproResponse<DTO> customAdvanceSearch(String searchParameters, ThaproSearchFilter searchFilter) {
        throw ThaproNotSupportedException.of(String.format("customAdvanceSearch:%s", searchFilter.getModule()));
    }

    default ThaproResponse<DTO> save(DTO dto, ThaproSearchFilter searchFilter) throws ThaproValidationException {
        throw ThaproNotSupportedException.of(String.format("save:%s", searchFilter.getModule()));
    }

    default ThaproResponse<DTO> update(DTO dto, ThaproSearchFilter filter) throws ThaproNoDataFoundException,
        ThaproValidationException {
        throw ThaproNotSupportedException.of(String.format("update:%s", filter.getModule()));

    }

    default ThaproResponse<Void> patchUpdate(I id, Map<String, String> postData, ThaproSearchFilter filter) {
        throw ThaproNotSupportedException.of(String.format("patchUpdate:%s", filter.getModule()));
    }

    default ThaproResponse<Void> requestForPatchUpdate(I id, String requestFor, Map<String, String> postData,
                                                        ThaproSearchFilter filter) {
        throw ThaproNotSupportedException.of(String.format("requestForPatchUpdate:%s", filter.getModule()));

    }

    default ThaproResponse<DTO> delete(DTO dto, ThaproSearchFilter filter) throws ThaproNoDataFoundException {
        throw ThaproNotSupportedException.of(String.format("delete:%s", filter.getModule()));
    }

    default ThaproResponse<DTO> deleteByID(I id, ThaproSearchFilter filter) throws ThaproNoDataFoundException {
        throw ThaproNotSupportedException.of(String.format("deleteByID:%s", filter.getModule()));
    }
}
