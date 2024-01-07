package iit.ase.cw.platform.common.converter;

public interface BiCollectionConverterService <FROM, TO>
        extends FromCollectionConverter<FROM, TO>, ToCollectionConverter<FROM,TO> {
}
