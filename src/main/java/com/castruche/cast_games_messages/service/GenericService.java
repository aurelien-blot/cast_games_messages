package com.castruche.cast_games_messages.service;

import com.castruche.cast_games_messages.formatter.IFormatter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class GenericService<ENTITY, DTO> {

    private final JpaRepository<ENTITY, Long> repository;
    private final IFormatter<ENTITY, DTO> formatter;

    public GenericService(JpaRepository<ENTITY, Long> repository, IFormatter<ENTITY, DTO> formatter) {
        this.repository = repository;
        this.formatter = formatter;
    }

    public ENTITY selectById(Long id) {
        if(id == null){
            return null;
        }
        return repository.findById(id).orElse(null);
    }
    public DTO selectDtoById(Long id) {
        ENTITY entity = this.selectById(id);
        return formatter.entityToDto(entity);
    }

    public List<ENTITY> getAll() {
        return repository.findAll();
    }

    public List<DTO> getAllDto() {
        List<ENTITY> entities = this.getAll();
        return formatter.entityToDto(entities);
    }

    public DTO create(DTO dto) {
        ENTITY entity = formatter.dtoToEntity(dto);
        entity = repository.save(entity);
        return formatter.entityToDto(entity);
    }
}
