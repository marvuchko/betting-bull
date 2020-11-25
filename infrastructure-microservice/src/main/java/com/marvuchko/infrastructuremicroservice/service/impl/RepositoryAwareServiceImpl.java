package com.marvuchko.infrastructuremicroservice.service.impl;

import com.marvuchko.infrastructuremicroservice.data.entity.BaseEntity;
import com.marvuchko.infrastructuremicroservice.data.repository.BaseRepository;
import com.marvuchko.infrastructuremicroservice.exception.core.NotFoundException;
import com.marvuchko.infrastructuremicroservice.service.RepositoryAwareService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.Objects.isNull;

public abstract class RepositoryAwareServiceImpl<K extends Serializable, T extends BaseEntity<K>, R extends BaseRepository<K, T>>
        implements RepositoryAwareService<K, T, R> {

    protected static final ModelMapper MODEL_MAPPER;

    static {
        MODEL_MAPPER = new ModelMapper();
        MODEL_MAPPER.getConfiguration().setAmbiguityIgnored(true);
    }

    private final R repository;

    public RepositoryAwareServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(K id, T modifiedEntity) {
        T entity = getById(id);

        if (isNull(entity)) return null;

        K entityId = entity.getId();
        MODEL_MAPPER.map(modifiedEntity, entity);
        entity.setId(entityId);

        return repository.save(entity);
    }

    @Override
    public T getById(K id) {
        return repository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public T delete(K id) {
        T entity = getById(id);
        repository.delete(entity);
        return entity;
    }

    @Override
    public Page<T> getPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Set<T> findByIds(Set<K> ids) {
        return new LinkedHashSet<>(repository.findAllById(ids));
    }

    @Override
    public Set<T> deleteByIds(Set<K> ids) {
        Set<T> entities = findByIds(ids);
        repository.deleteAll(entities);
        return entities;
    }

}
