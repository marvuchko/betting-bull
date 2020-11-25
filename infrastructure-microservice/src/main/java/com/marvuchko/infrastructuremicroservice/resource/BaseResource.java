package com.marvuchko.infrastructuremicroservice.resource;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public abstract class BaseResource {

    /**
     * URL Constants
     */
    protected static final String ID_PATH = "/{id}";

    /**
     * Query Param Constants
     */
    protected static final String IDS_QUERY_PARAM = "ids";

    /**
     * Resource Constants
     */
    protected static final String ID_FIELD = "id";

    private static final ModelMapper MODEL_MAPPER;

    static {
        MODEL_MAPPER = new ModelMapper();
        MODEL_MAPPER.getConfiguration().setAmbiguityIgnored(true);
    }

    protected static <S, D> D map(S source, Class<D> type) {
        return MODEL_MAPPER.map(source, type);
    }

    protected static <S, D> Set<D> mapSet(Set<S> sources, Class<D> type) {
        return sources
                .stream()
                .map(item -> map(item, type))
                .collect(toSet());
    }

    protected static <S, D> Page<D> mapPage(Page<S> source, Class<D> type) {
        return source.map(item -> map(item, type));
    }

}
