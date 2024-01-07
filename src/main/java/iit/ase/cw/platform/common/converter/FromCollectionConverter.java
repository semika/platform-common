package iit.ase.cw.platform.common.converter;

import iit.ase.cw.platform.common.context.model.ThaproSearchFilter;

import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
public interface FromCollectionConverter<FROM, TO> {

    List<FROM> from(List<TO> toList, ThaproSearchFilter thaproSearchFilter);

    // The following method is added to create an instance of this type.
    static <FROM, TO> FromCollectionConverter<FROM, TO> of(FromConverter<FROM, TO> fromConverter) {
       return (toList, filter) -> toList.stream().map((TO t) -> fromConverter.from(t, filter)).collect(Collectors.toList());
    }
}
