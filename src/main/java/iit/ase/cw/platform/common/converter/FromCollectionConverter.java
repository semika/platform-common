package iit.ase.cw.platform.common.converter;

import iit.ase.cw.platform.common.context.model.ThaproSearchFilter;

import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
public interface FromCollectionConverter<F, T> {

    List<F> from(List<T> toList, ThaproSearchFilter thaproSearchFilter);

    // The following method is added to create an instance of this type.
    static <F, T> FromCollectionConverter<F, T> of(FromConverter<F, T> fromConverter) {
       return (toList, filter) -> toList.stream().map((T t) -> fromConverter.from(t, filter)).collect(Collectors.toList());
    }
}
