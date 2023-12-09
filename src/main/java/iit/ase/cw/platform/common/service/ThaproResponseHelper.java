package iit.ase.cw.platform.common.service;

import iit.ase.cw.platform.common.constant.ThaproGlobalConstant;
import iit.ase.cw.platform.common.context.model.ThaproSearchFilter;
import iit.ase.cw.platform.common.converter.ToCollectionConverter;
import iit.ase.cw.platform.common.converter.ToConverter;
import iit.ase.cw.platform.common.type.ThaproModelList;
import iit.ase.cw.platform.common.type.ThaproPage;
import iit.ase.cw.platform.common.type.ThaproResponse;
import iit.ase.cw.platform.common.type.ThaproResponseBuilder;
import iit.ase.cw.platform.common.util.toolkit.CollectionToolkit;
import iit.ase.cw.platform.common.util.toolkit.StringToolkit;
import java.util.List;
import java.util.Optional;

public final class ThaproResponseHelper {

    public ThaproResponseHelper() { }

    public static <E> ThaproPage createThaproPage(ThaproModelList<E> modelList) {

        return ThaproPage.builder()
            .pageNumber(modelList.getPageNumber())
            .pageSize(modelList.getPageSize())
            .totalRecords(modelList.getTotalRecords())
            .build();
    }

    public static <E> ThaproResponse<E> createResponse(List<E> list) {
        return ThaproResponseBuilder.<E>create().withoutMessage().withData(list).withoutPageInfo();
    }

    public static <E> ThaproResponse<E> createResponseWithDefaultFetchMessage(List<E> list) {
        return ThaproResponseBuilder.<E>create().withMessage("Date fetch operation successfull").withData(list).withoutPageInfo();
    }

    public static <R> ThaproResponse<R> createResourceResponse(R response) {
        return createResourceResponse(response, ThaproGlobalConstant.Symbol.EMPTY_STR);
    }

    public static <R> ThaproResponse<R> createResourceResponse(R response, String message) {
        return ThaproResponseBuilder.<R>create()
            .withMessage(StringToolkit.nvl(message))
            .withData(response)
            .withoutPageInfo();
    }

    public static <E, R, I> ThaproResponse<R> createResponse(E entity, I id, ToConverter<E, R> converter,
                                                             ThaproSearchFilter filter) {
        if (Optional.ofNullable(entity).isPresent()) {
            String message = "Fetch operation successfull " + id;
            return ThaproResponseBuilder.<R>create()
                .withMessage(message)
                .withData(converter.to(entity, filter))
                .withoutPageInfo();
        }
        return ThaproResponseBuilder.<R>create()
            .withMessage("No data found")
            .withoutData()
            .withoutPageInfo();

    }

    public static <E, R> ThaproResponse<R> createResponse(List<E> list, ToCollectionConverter<E, R> converter,
                                                          ThaproSearchFilter filter) {
        ThaproResponseBuilder.PagePhrase<R> pagePhrase;

        if (!list.isEmpty()) {
            String message = "Get operation successsfull";
            pagePhrase = ThaproResponseBuilder.<R>create().withMessage(message)
                .withData(converter.to(list, filter));
        } else {
            pagePhrase = ThaproResponseBuilder.<R>create()
                .withMessage("No data foound")
                .withoutData();
        }
        return pagePhrase.withoutPageInfo();
    }
    public static <E, R> ThaproResponse<R> createResponse(List<E> list, ThaproPage page,
                                                           ToConverter<E, R> converter, ThaproSearchFilter filter) {
        if (!CollectionToolkit.isListEmpty(list)) {
            ToCollectionConverter<E, R> toCollectionConverter = ToCollectionConverter.of(converter);
            return ThaproResponseBuilder.<R>create()
                .withMessage("Geo operation success")
                .withData(toCollectionConverter.to(list, filter))
                .withPageInfo(page);
        }
        return ThaproResponseBuilder.<R>create()
            .withMessage("No data found")
            .withoutData()
            .withoutPageInfo();
    }

    public static <E, R> ThaproResponse<R> createResponse(ThaproModelList<E> list, ToConverter<E, R> converter,
                                                          ThaproSearchFilter filter) {
        return createResponse(list.getList(), createThaproPage(list), converter, filter);
    }

    public static <R> ThaproResponse<R> createResponseByResource(ThaproModelList<R> list,
                                                                 ThaproSearchFilter filter) {
        return ThaproResponseBuilder.<R>create()
            .withMessage("GET operation successfull")
            .withData(list.getList())
            .withPageInfo(createThaproPage(list));
    }

    public static <R> ThaproResponse<R> createResponseByResource(List<R> list, ThaproSearchFilter filter) {
        ThaproModelList<R> modelList = ThaproModelList.<R>builder()
            .list(list)
            .pageSize(list == null ? 0 : list.size())
            .build();
        return createResponseByResource(modelList, filter);
    }

    public static <R> ThaproResponse<R> createResponseByResourceList(List<R> list) {
        return ThaproResponseBuilder.<R>create().withoutMessage().withData(list).withoutPageInfo();
    }

    public static <R> ThaproResponse<R> createResponse(Optional<R> resourceOptional, ThaproSearchFilter searchFilter) {
        if (resourceOptional.isPresent()) {
            return ThaproResponseBuilder.<R>create()
                .withMessage("Data fetched successfull")
                .withData(resourceOptional.get())
                .withoutPageInfo();
        } else {
            return ThaproResponseBuilder.<R>create()
                .withMessage("No data found")
                .withoutData()
                .withoutPageInfo();
        }
    }
}
