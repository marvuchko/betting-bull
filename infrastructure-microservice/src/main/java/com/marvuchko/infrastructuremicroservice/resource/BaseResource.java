package com.marvuchko.infrastructuremicroservice.resource;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.stream.Collectors.toCollection;

public abstract class BaseResource {

    /**
     * Resource documentation constants
     */
    public static final String TAG_NAME_FIELD = "TAG_NAME";
    public static final String TAG_DESCRIPTION_FIELD = "TAG_DESCRIPTION";

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

    protected static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * Model mapper for mapping differences between objects
     */
    private static final ModelMapper MODEL_MAPPER;

    static {
        MODEL_MAPPER = new ModelMapper();
        var configuration = MODEL_MAPPER.getConfiguration();

        configuration
                .setAmbiguityIgnored(true)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
    }

    protected static <S, D> D map(S source, Class<D> type) {
        return MODEL_MAPPER.map(source, type);
    }

    protected static <S, D> Set<D> mapSet(Set<S> sources, Class<D> type) {
        return sources
                .stream()
                .map(item -> map(item, type))
                .collect(toCollection(LinkedHashSet::new));
    }

    protected static <S, D> Page<D> mapPage(Page<S> source, Class<D> type) {
        return source.map(item -> map(item, type));
    }

}
