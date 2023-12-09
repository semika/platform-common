package iit.ase.cw.platform.common.context.config;

import iit.ase.cw.platform.common.util.toolkit.NumberToolkit;
import iit.ase.cw.platform.common.util.toolkit.StringToolkit;
import iit.ase.cw.platform.common.context.model.SearchFilter;
import iit.ase.cw.platform.common.context.constant.ThaproContextConstant;
import iit.ase.cw.platform.common.type.SortOrder;
import iit.ase.cw.platform.common.context.model.ThaproSearchFilter;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Locale;

public class ThaproSearchFilterBinder implements HandlerMethodArgumentResolver {

    private ThaproContextProperties thaproContextProperties;

    private String moduleName;

    public ThaproSearchFilterBinder(ThaproContextProperties thaproContextProperties, String moduleName) {
        this.thaproContextProperties = thaproContextProperties;
        this.moduleName = moduleName;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(SearchFilter.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String startRowStr = webRequest.getParameter(ThaproContextConstant.WebConstant.START_ROW);
        String pageSizeStr = webRequest.getParameter(ThaproContextConstant.WebConstant.PAGE_SIZE);
        String pageNoStr = webRequest.getParameter(ThaproContextConstant.WebConstant.PAGE_NO);
        String module = webRequest.getParameter(ThaproContextConstant.WebConstant.MODULE);
        String requestFor = webRequest.getParameter(ThaproContextConstant.WebConstant.REQUEST_FOR);
        String pageableParamValue = webRequest.getParameter(ThaproContextConstant.WebConstant.PAGEABLE);
        String sortBy = webRequest.getParameter(ThaproContextConstant.WebConstant.SORT_BY);
        String sortOrder = webRequest.getParameter(ThaproContextConstant.WebConstant.SORT_ORDER);
        Locale locale = webRequest.getLocale();

        int startRow = NumberToolkit.parseInt(startRowStr);
        int pageSize = NumberToolkit.parseInt(pageSizeStr, thaproContextProperties.getPage().getPageSize());
        pageSize = Math.min(pageSize, thaproContextProperties.getPage().getPageSize());
        int pageNo = NumberToolkit.parseInt(pageNoStr, -1);
        pageNo = pageNo < 0 ? startRow / pageSize : pageNo;

        return ThaproSearchFilter.builder()
            .pageNo(pageNo)
            //.pageable(pageable)
            //.sortFields(CollectionToolkit.convertToList(sortBy))
            .sortOrder(SortOrder.DESC.name().equals(sortOrder) ? SortOrder.DESC : SortOrder.ASC)
            .startRow(startRow)
            .pageSize(pageSize)
            .module(StringToolkit.nvl(module, moduleName))
            .requestFor(requestFor)
            //.kalerisUser(kalerisUser)
            //.distinctQuery(queryIsDistinct)
            .locale(locale)
            .build();
    }
}
