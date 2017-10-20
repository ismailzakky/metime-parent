package com.cus.metime.promosi.Assembler.core;

import java.util.List;

/**
 * Created by C-US on 9/26/2017.
 */
public interface ObjectAssembler<T,T1> {

    public T toDomain(T1 dtoObject);

    public T1 toDTO(T domainObject);

    public List<T> toDomainList(List<T1> dtoObjectList);

    public List<T1> toDTOList(List<T> domainObjectList);

}
