package iit.ase.cw.platform.common.converter;

import iit.ase.cw.platform.common.context.model.ThaproSearchFilter;

@FunctionalInterface
public interface ToDtoConverter <E, R> {
    R toDto(E domain, ThaproSearchFilter thaproSearchFilter);
}
