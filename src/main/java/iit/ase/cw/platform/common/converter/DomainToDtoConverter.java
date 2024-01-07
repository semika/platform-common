package iit.ase.cw.platform.common.converter;

import iit.ase.cw.platform.common.context.model.ThaproSearchFilter;

@FunctionalInterface
public interface DomainToDtoConverter<DOMAIN, DTO> {
    DTO toDto(DOMAIN domain, ThaproSearchFilter thaproSearchFilter);
}
