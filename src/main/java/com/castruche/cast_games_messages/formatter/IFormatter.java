package com.castruche.cast_games_messages.formatter;

import java.util.ArrayList;
import java.util.List;

public interface IFormatter<ENTITY, DTO> {

    DTO entityToDto(ENTITY entity);
    ENTITY dtoToEntity(DTO dto);
    default List<DTO> entityToDto(List<ENTITY> entityList) {
        List<DTO> results= new ArrayList<>();
        if(entityList == null || entityList.isEmpty()){
            return results;
        }
        entityList.forEach(entity -> results.add(entityToDto(entity)));
        return results;
    }

}
