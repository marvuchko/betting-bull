package com.marvuchko.infrastructuremicroservice.service;

import com.marvuchko.infrastructuremicroservice.data.entity.BaseEntity;
import com.marvuchko.infrastructuremicroservice.data.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Set;

public interface RepositoryAwareService<K extends Serializable, T extends BaseEntity<K>, R extends BaseRepository<K, T>> {

    T create(T entity);

    T update(K id, T modifiedEntity);

    T getById(K id);

    T delete(K id);

    Page<T> getPage(Pageable pageable);

    Set<T> findByIds(Set<K> ids);

    Set<T> deleteByIds(Set<K> ids);

}
