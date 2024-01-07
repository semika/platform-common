package iit.ase.cw.platform.common.converter;

import iit.ase.cw.platform.common.context.model.ThaproSearchFilter;

@FunctionalInterface
public interface DtoToDomainConverter<DOMAIN, DTO> {
    DOMAIN toDomain(DTO dto, ThaproSearchFilter thaproSearchFilter);
}
