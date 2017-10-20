package com.cus.metime.promosi.Assembler;

import com.cus.metime.promosi.Assembler.core.ObjectAssembler;
import com.cus.metime.promosi.domain.embeddable.CreationalDate;
import com.cus.metime.promosi.domain.embeddable.builder.CreationalDateBuilder;
import com.cus.metime.promosi.dto.CreationalDateDTO;
import com.cus.metime.promosi.dto.builder.CreationalDateDTOBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C-US on 9/26/2017.
 */
@Service
public class CreationalDateAssembler implements ObjectAssembler<CreationalDate,CreationalDateDTO> {
    @Override
    public CreationalDate toDomain(CreationalDateDTO dtoObject) {

        return new CreationalDateBuilder()
            .setCreatedAt(dtoObject.getCreatedAt())
            .setCreatedBy(dtoObject.getCreatedBy())
            .setModifiedAt(dtoObject.getModifiedAt())
            .setModifiedBy(dtoObject.getModifiedBy())
            .createCreationalDate();
    }

    @Override
    public CreationalDateDTO toDTO(CreationalDate domainObject) {

        return new CreationalDateDTOBuilder()
            .setCreatedAt(domainObject.getCreatedAt())
            .setCreatedBy(domainObject.getCreatedBy())
            .setModifiedAt(domainObject.getModifiedAt())
            .setModifiedBy(domainObject.getModifiedBy())
            .createCreationalDateDTO();
    }

    @Override
    public List<CreationalDate> toDomainList(List<CreationalDateDTO> dtoObjectList) {

        ArrayList<CreationalDate> creationalDateList = new ArrayList<>();
        CreationalDate creationalDate;
        for(CreationalDateDTO creationalDateDTO : dtoObjectList){
            creationalDate = toDomain(creationalDateDTO);
            creationalDateList.add(creationalDate);
        }

        return creationalDateList;
    }

    @Override
    public List<CreationalDateDTO> toDTOList(List<CreationalDate> domainObjectList) {

        List<CreationalDateDTO> creationalDateDTOList = new ArrayList<>();
        CreationalDateDTO creationalDateDTO;
        for(CreationalDate creationalDate : domainObjectList){
            creationalDateDTO = toDTO(creationalDate);
            creationalDateDTOList.add(creationalDateDTO);
        }
        return creationalDateDTOList;
    }
}
